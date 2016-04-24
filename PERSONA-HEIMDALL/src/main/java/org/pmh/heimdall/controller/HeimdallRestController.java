package org.pmh.heimdall.controller;

//   Standard Libraries Imports
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Arrays;
import java.util.UUID;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

//   Third Party Libraries Imports
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

//   FENIX Framework Imports


//   Domain Imports
import org.pmh.heimdall.model.EventsModel;
import org.pmh.heimdall.document.DocumentRec;
import com.fxt.process.ResponseRec;
import org.pmh.heimdall.external.person.PersonRec;
import org.pmh.heimdall.process.EventRec;
import org.pmh.heimdall.process.EventShortRec;
import org.pmh.heimdall.process.TokenConfirmationDataRec;
//import com.ng.lk.brucke.process.TokenDataRec;

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

    private static final String EVENT_REGISTERED_SUCCESSFULLY_CODE    = "EV-001";
    private static final String EVENT_REGISTERED_SUCCESSFULLY_MESSAGE = "Event registered successfully.";

    private static final String PERSON_REGISTERED_SUCCESSFULLY_CODE    = "EV-002";
    private static final String PERSON_REGISTERED_SUCCESSFULLY_MESSAGE = "Event registered successfully.";

    private static final String CONTRACT_REGISTERED_SUCCESSFULLY_CODE    = "EV-003";
    private static final String CONTRACT_REGISTERED_SUCCESSFULLY_MESSAGE = "Event registered successfully.";
            
    @Autowired
    private DataSource ds;

    @RequestMapping ( value = "/heimdallAPI/1.0/events/events", method = RequestMethod.POST, consumes="application/json" )
    public @ResponseBody ResponseRec<EventRec> postEvent ( @RequestBody EventRec event, HttpServletRequest request ) {

        ResponseRec<EventRec> response = new ResponseRec<> ( );
        
        event.spillYourGuts ( );

//   PERSISTS THE EVENT
        
        EventsModel.persistEvent ( event, ds );

        response.setResultCode    ( HeimdallRestController.EVENT_REGISTERED_SUCCESSFULLY_CODE    );
        response.setResultMessage ( HeimdallRestController.EVENT_REGISTERED_SUCCESSFULLY_MESSAGE );

        response.setPayload ( event );
        
        return response;

    }

    public PersonRec retrievePerson ( int personCode ) {

        return new PersonRec ( );

    }

    private String createUUID ( ) {
        return UUID.randomUUID ( ).toString ( );
    }

    private boolean isValidToken ( String authToken ) {
        
        RestTemplate restTemplate = new RestTemplate ( );
        HttpHeaders  headers      = new HttpHeaders  ( );
        
        String authAPIUri    = "http://13.79.175.6:8080/Cerberus-1/AuthorizationAPI/1.0/confirmToken";

        headers.setAccept ( Arrays.asList ( MediaType.APPLICATION_JSON ) );
        headers.set ( "user-token", authToken );
        headers.set ( "service-id", "brucke"  );
        
        HttpEntity<String> entity = new HttpEntity<> ( "parameters", headers );

        TokenConfirmationDataRec tcdr = new TokenConfirmationDataRec  ( );
        
        try {
            ResponseEntity<TokenConfirmationDataRec> response = restTemplate.exchange ( authAPIUri, HttpMethod.GET, entity, TokenConfirmationDataRec.class );
            //exchange ( authAPIUri, HttpMethod.GET, entity, TokenDataRec.class );
            //tdr = restTemplate.getForObject ( authAPIUri, TokenDataRec.class );
            tcdr = response.getBody ( );
        } catch ( RestClientException ex ) {
            System.err.print ( ex.getMessage ( ) );
        
        }
        
        return ( tcdr.getMessage ( ).equals ( "Token valid" ) );
        
    }

}
