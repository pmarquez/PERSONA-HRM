
package org.pmh.heimdall.websocket;

import java.io.IOException;
import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * 
 */
//TODO ENCODER
//TODO DataSource
@ServerEndpoint ( value    = "/dashboardUpdates" )
//@ServerEndpoint ( value    = "/parkingLotUpdates", encoders = { MonitorUpdateEncoder.class } )
public class DashboardMonitorEndpoint implements DashboardUpdateListener {
//public class DashboardMonitorEndpoint implements SensorEventListener {
//public class DashboardMonitorEndpoint implements ParkingLotsDataSourceListener {
    private Session               session;
//    private WSListenerTracker      wsListenerTracker;   //   ParkingLotsDataSource

//   WebSocket Lifecycle methods - BEGIN

    @OnOpen
    public void openConnection ( Session session, EndpointConfig config ) {
        this.session = session;
    }
    
    @OnMessage
    public void handleIncomingMessage ( String command ) {

        switch ( command ) {
            case "startMonitor":
//                this.dataSource = new ParkingLotsDataSource ( );
//                this.dataSource.addParkingLotDataSourceListener ( this );
//                this.dataSource.start ( );
                break;

            case "stopMonitor":
//                this.dataSource.stop ( );
                break;

            case "closeConnection":
                try {
                    session.close ( );
                } catch ( IOException ioe ) {
                    System.out.println ( "Error closing session " + ioe.getMessage ( ) );
                }
                break;

            default:
                System.out.println ( "Command received: " + command );
                System.out.println ( "[This should never happen, unless it just happened, in which case, disregard the imposibility of this ever happening. :-)]" );
        }
    }
        
    @OnError
    public void handleError ( Throwable t ) {
        System.out.println ( "Error: " + t.getMessage ( ) );
    }
    
    @OnClose
    public void closeConnection ( CloseReason reason ) {
        System.out.println ( reason.getReasonPhrase ( ) );
        try {
//            dataSource.stop ( );
            session.close   ( );
        } catch ( IOException ioe ) {
            System.out.println ( "Error closing session " + ioe.getMessage ( ) );
        }
    }
//   WebSocket Lifecycle methods - END

    
    //   Pushes the new data to all connected clients
    @Override
    public void handleDashboardUpdate(String update) {
        try {
            session.getBasicRemote ( ).sendObject ( update );
        } catch ( IOException | EncodeException ioe ) {
            this.handleError ( ioe );
        }
    }
}
