
package org.pmh.heimdall.websocket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * DashboardMonitorEndpoint
 */
@ServerEndpoint ( value = "/dashboardUpdates" )
public class DashboardMonitorEndpoint {

private static Set<Session> peers = Collections.synchronizedSet ( new HashSet ( ) );

//   WebSocket Lifecycle methods - BEGIN

    @OnOpen
    public void onOpen(Session session, @PathParam ( "client-id" ) String clientId ) {
        
        System.out.println ( "mediator: opened websocket channel for client " + clientId );
        peers.add ( session );

        try {
            session.getBasicRemote ( ).sendText ( "{ \"message\": \"good to be in touch\" }" );
            
        } catch ( IOException e ) {
        }
    }

    @OnMessage
    public String onMessage(String message, Session session, @PathParam ( "client-id" ) String clientId) {
        try {
            JSONObject jObj = new JSONObject ( message );
            System.out.println ( "received message from client " + clientId );

            for ( Session s : peers ) {
                try {
                    s.getBasicRemote ( ).sendText ( message );
                    System.out.println ( "send message to peer " );

                } catch ( IOException e ) {
                    e.printStackTrace ( );

                }

            }
        } catch ( JSONException e ) {
            e.printStackTrace ( );

        }

        return "message was received by socket mediator and processed: " + message;
    }
  
    @OnClose
    public void onClose ( CloseReason reason, Session session, @PathParam ( "client-id" ) String clientId ) {

        System.out.println ( "mediator: closed websocket channel for client " + clientId );

        peers.remove ( session );
    }
    
    @OnError
    public void handleError ( Throwable t ) {
        System.out.println ( "Error: " + t.getMessage ( ) );
    }
    
//   WebSocket Lifecycle methods - END

}
