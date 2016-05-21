
package org.pmh.heimdall.websocket;

import java.net.URI;
import java.util.List;
import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import org.pmh.heimdall.process.DashboardUsageDataRec;

// based on http://stackoverflow.com/questions/26452903/javax-websocket-client-simple-example
/**
 *
 * @author pmarquez
 */
@ClientEndpoint
public class WebsocketClientEndpoint {

    public WebsocketClientEndpoint ( ) {

    }

    public void initClientEndpoint ( List<List<DashboardUsageDataRec>> data, URI endpointURI ) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer ( );
            container.connectToServer ( this, endpointURI );
        } catch ( Exception e ) {
            throw new RuntimeException ( e );
        }
    }

    Session userSession = null;
    private MessageHandler messageHandler;

    @OnOpen
    public void onOpen ( Session userSession ) {
        System.out.println ( "client: opening websocket " );
        this.userSession = userSession;
    }

    /**
     * Callback hook for Connection close events.
     *
     * @param userSession the userSession which is getting closed.
     * @param reason the reason for connection close
     */
    @OnClose
    public void onClose ( Session userSession, CloseReason reason ) {
        System.out.println ( "client: closing websocket" );
        this.userSession = null;
    }

    /**
     * Callback hook for Message Events. This method will be invoked when a client send a message.
     *
     * @param message The text message
     */
    @OnMessage
    public void onMessage(String message) {
        System.out.println("client: received message "+message);
        if (this.messageHandler != null) {
            this.messageHandler.handleMessage(message);
        }
    }

    /**
     * register message handler
     *
     * @param message
     */
    public void addMessageHandler(MessageHandler msgHandler) {
        this.messageHandler = msgHandler;
    }

    /**
     * Send a message.
     *
     * @param user
     * @param message
     */
    public void sendMessage(String message) {
        this.userSession.getAsyncRemote().sendText(message);
    }

    public static interface MessageHandler {

        public void handleMessage(String message);
    }
}
