package org.pmh.heimdall.controller;

//   Standard Libraries Imports
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.UUID;

//   Third Party Libraries Imports
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

//   FENIX Framework Imports
import com.fxt.process.ResponseRec;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jettison.json.JSONObject;

//   Domain Imports
import org.pmh.heimdall.model.EventsModel;
import org.pmh.heimdall.external.person.PersonRec;
import org.pmh.heimdall.model.SensorsModel;
import org.pmh.heimdall.process.DashboardElement;
import org.pmh.heimdall.process.DashboardUsageDataRec;
import org.pmh.heimdall.process.EventShortRec;
import org.pmh.heimdall.process.TokenConfirmationDataRec;
import org.pmh.heimdall.sensor.SensorRec;
import org.pmh.heimdall.websocket.DashboardDataJSONEncoder;
import org.pmh.heimdall.websocket.WebsocketClientEndpoint;

/**
 * HeimdallRestController.java<br><br>
 * Creation Date 2016-04-18 08:14<br><br>
 * <b>DESCRIPTION:</b><br><br>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br>
 * Version Date: 2016-04-18 08:14<br>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2016-04-18 08:14
 */
@RestController
public class HeimdallRestController {

    private WebsocketClientEndpoint client;
    private final String webSocketAddress = "ws://localhost:8080/Heimdall/dashboardUpdates";
//    private final String webSocketAddress = "ws://13.79.175.6:8080/Heimdall/dashboardUpdates";
    
    //TODO - JACK SPARROW WAS HERE - Get rid of this ASAP - BEGIN
    //http://localhost:8084/Cerberus/AuthenticationAPI/1.0/login/pepe/pepe
    String serverBaseURI            = "http://13.79.175.6:8080";
//    String serverBaseURI            = "http://localhost:8084";
    String cerberusBaseURI          = "/Cerberus-1/AuthorizationAPI/1.0/";
    String tokenValidationAPIMethod = "confirmToken";
    //TODO - JACK SPARROW WAS HERE - Get rid of this ASAP - END

    
    private static final int    HOURS_TO_MONITOR = 24;

    private static final String GLOBAL_SENSOR_USAGE = "GLOBAL_SENSOR_USAGE";
    private static final String HOURLY_SENSOR_USAGE = "HOURLY_SENSOR_USAGE";

    private static final String EVENT_REGISTERED_SUCCESSFULLY_CODE    = "EV-001";
    private static final String EVENT_REGISTERED_SUCCESSFULLY_MESSAGE = "Event registered successfully.";

    private static final String AUTHORIZATION_TOKEN_NOT_VALID_CODE    = "EV-002";
    private static final String AUTHORIZATION_TOKEN_NOT_VALID_MESSAGE = "AUTHENTICATION TOKEN NOT VALID.";

    private static final String SENSOR_CODE_NOT_VALID_CODE            = "EV-003";
    private static final String SENSOR_CODE_NOT_VALID_MESSAGE         = "SENSOR CODE NOT VALID.";

//    private static final String WEB_SOCKET_ADDRESS                    = "ws://localhost:8084/CinemaMonitor/cinemaSocket";
    private static final String WEB_SOCKET_ADDRESS                    = "ws://localhost:8080/Heimdall/dashboardUpdates";

    @Autowired
    private DataSource ds;

    private void initializeWebSocket ( ) throws URISyntaxException {
        //ws://localhost:8084/Heimdall/dashboardUpdates
        System.out.println ( "REST service: open websocket client at " + webSocketAddress );
        client = new WebsocketClientEndpoint ( new URI ( webSocketAddress ) );
    }

    private void sendMessageOverSocket ( String message ) {
        
        if ( client == null ) {
            try {
                initializeWebSocket ( );
            
            } catch ( URISyntaxException e ) {
                e.printStackTrace ( );
                
            }
        }
        
        client.sendMessage ( message );

    }

    /**
     * 
     * @param event
     * @param request
     * @param authToken
     * @return 
     */
    @RequestMapping ( value = "/heimdallAPI/1.0/events/events", method = RequestMethod.POST, consumes="application/json" )
    public @ResponseBody ResponseRec<EventShortRec> postEvent ( @RequestBody EventShortRec event, HttpServletRequest request ) {
    //public @ResponseBody ResponseRec<EventShortRec> postEvent ( @RequestBody EventShortRec event, HttpServletRequest request, @RequestHeader(value="authorization-token") String authToken ) {

        ResponseRec<EventShortRec> response = new ResponseRec<> ( );

//   VALIDATE THE EVENT - BEGIN

        //request.getHeader ( "authorization-token" );

        String authToken = event.getAuthToken ( );   //   JACK SPARROW - This must be an HttpHeader.
        
        System.out.println ( "authToken: " + authToken );
        
        if ( this.isValidToken ( authToken ) ) {

            event.setAuthToken  ( authToken );
            response.setPayload ( event     );
            
            if ( this.isValidTag   ( event.getSensorTagCode ( ) ) ) {
                
                EventsModel.persistEvent ( event, ds );
                response.setResultCode    ( HeimdallRestController.EVENT_REGISTERED_SUCCESSFULLY_CODE    );
                response.setResultMessage ( HeimdallRestController.EVENT_REGISTERED_SUCCESSFULLY_MESSAGE );

                List<DashboardElement> data = new ArrayList<> ( ); 

                //   GLOBAL_SENSOR_USAGE
                DashboardElement globalData = new DashboardElement ( );
                globalData.setItem ( HeimdallRestController.GLOBAL_SENSOR_USAGE );
                globalData.setData ( EventsModel.retrieveGlobalSensorUsage ( HeimdallRestController.HOURS_TO_MONITOR, ds ) );
                data.add ( globalData );

                //   HOURLY_SENSOR_USAGE
                
                //   Get the sensors active in the last <HOURS_TO_MONITOR> hours
                List<String> sensorsInvolved = EventsModel.retrieveSensorsUsedInTimeRange ( HOURS_TO_MONITOR, ds );

                DashboardElement hourlyData = new DashboardElement ( );

                hourlyData.setItem ( HeimdallRestController.HOURLY_SENSOR_USAGE );

                //   Prime the DataSet with all sensors for each of the hours
                hourlyData.setData ( this.primeSensorData ( HOURS_TO_MONITOR, sensorsInvolved ) );
                
                this.fillCronologicalSensorUsage ( hourlyData.getData ( ), EventsModel.retrieveHourlySensorUsage ( HeimdallRestController.HOURS_TO_MONITOR, ds ) );
                
                data.add ( hourlyData );

                System.out.println ( "Data: " + data );

                String JSONData = DashboardDataJSONEncoder.encode ( data );
                
//   LLAMAR LA ACTUALIZACIÓN DESDE AQUI - BEGIN
                
                System.out.println ( "received event:" + JSONData );
                sendMessageOverSocket ( JSONData );
                //return "event received " + json;

//   LLAMAR LA ACTUALIZACIÓN DESDE AQUI - END

            } else {
                response.setResultCode    ( HeimdallRestController.SENSOR_CODE_NOT_VALID_CODE    );
                response.setResultMessage ( HeimdallRestController.SENSOR_CODE_NOT_VALID_MESSAGE );
            }
        } else {
            response.setResultCode    ( HeimdallRestController.AUTHORIZATION_TOKEN_NOT_VALID_CODE    );
            response.setResultMessage ( HeimdallRestController.AUTHORIZATION_TOKEN_NOT_VALID_MESSAGE );
        }

        response.setPayload ( event );

//   VALIDATE THE EVENT - END        
        
        return response;

    }

    public PersonRec retrievePerson ( int personCode ) {

        return new PersonRec ( );

    }

    private String createUUID ( ) {
        return UUID.randomUUID ( ).toString ( );
    }

    /**
     * Validates that the token received is valid.
     * @param authToken
     * @return 
     */
    private boolean isValidToken ( String authToken ) {
        
        System.out.println ( "ENTERING - isValidToken: " + authToken );
        
        RestTemplate restTemplate = new RestTemplate ( );
        HttpHeaders  headers      = new HttpHeaders  ( );
        
        String tokenValidation  = serverBaseURI + cerberusBaseURI  + tokenValidationAPIMethod;

        headers.setAccept ( Arrays.asList ( MediaType.APPLICATION_JSON ) );
        headers.set ( "user-token", authToken );
        headers.set ( "service-id", "heimdall"  );
        
        HttpEntity<String> entity = new HttpEntity<> ( "parameters", headers );

        TokenConfirmationDataRec tcdr = new TokenConfirmationDataRec  ( );
        
        try {
            ResponseEntity<TokenConfirmationDataRec> response = restTemplate.exchange ( tokenValidation, HttpMethod.GET, entity, TokenConfirmationDataRec.class );
            tcdr = response.getBody ( );
            System.out.println("##### tcdr: " + tcdr);
        } catch ( RestClientException ex ) {
            System.err.print ( ex.getMessage ( ) );
        
        }
        
        return ( tcdr.getMessage ( ).equals ( "Token valid" ) );
        
    }

    private boolean isValidTag ( String sensorTagCode ) {
        
        SensorRec sensor = SensorsModel.retrieveSensorData ( sensorTagCode, ds );
        
        return ( sensor.getSensorCode ( ) > 0 );
        
    }

    /**
     * Prime the sensor data to display in monitor
     * @param HOURS_TO_MONITOR
     * @param sensorsInvolved
     * @return List<DashboardUsageDataRec>
     */
    private List<DashboardUsageDataRec> primeSensorData ( int HOURS_TO_MONITOR, List<String> sensorsInvolved ) {
        
        LocalTime currentTime = LocalTime.now ( );
        
        List<DashboardUsageDataRec> l = new ArrayList<> ( ); 
        
        for ( String sensorsName : sensorsInvolved ) {

            LocalTime lt = currentTime.minusHours ( HOURS_TO_MONITOR - 1 );

            for ( int i = 0; i < HOURS_TO_MONITOR; i++ ) {
                
                DashboardUsageDataRec r = new DashboardUsageDataRec ( );
                r.setHour ( String.valueOf ( lt.getHour ( ) ) );
                r.setSensorName( sensorsName );
             
                lt = lt.plusHours ( 1 );
                
                l.add ( r );

            }
        }

        return l;  
        
    }

    /**
     * Fills the actual usage data into the timeline for all hours and all sensors
     * @param data
     * @param hourlySensorUsage 
     */
    private void fillCronologicalSensorUsage ( List<DashboardUsageDataRec> data, List<DashboardUsageDataRec> hourlySensorUsage ) {

        for ( DashboardUsageDataRec sensorUsage : hourlySensorUsage ) {

            for ( DashboardUsageDataRec dashboardEntry : data ) {

                if ( ( dashboardEntry.getSensorName ( ).equals ( sensorUsage.getSensorName ( ) ) ) && ( Integer.parseInt ( dashboardEntry.getHour ( ) ) == Integer.parseInt ( sensorUsage.getHour ( ) ) ) ) {
                    dashboardEntry.setUseCount ( sensorUsage.getUseCount ( ) );
                }

            }

        }

    }

}
