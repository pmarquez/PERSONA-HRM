
package org.pmh.heimdall.websocket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.pmh.heimdall.model.EventsModel;
import org.pmh.heimdall.process.DashboardUsageDataRec;

/**
 * 
 * @author pmarquez - 2016-05-13 21:35
 */
public class DashBoardMonitorSessionHandler {
    
    private final List<DashboardUpdateListener> listeners;

    /**
     * 
     */
    public DashBoardMonitorSessionHandler ( ) {
        this.listeners = new ArrayList <> ( );
    }
    
    /**
     * Method that registers a new listener
     * @param listener
     */
    public void addDashboardMonitorListener ( DashboardUpdateListener listener ) {
        this.listeners.add ( listener );
    }

    /**
     * 
     * @param data
     */
    //TODO HOWTO GET HERE FROM HeimdallRestController
    public void doUpdate ( List<List<DashboardUsageDataRec>> data ) {
        this.notifyListeners ( data );
    }    
    
    /**
     * Broadcast the new data to registered listeners
     */ 
    private void notifyListeners ( List<List<DashboardUsageDataRec>> data ) {
        for ( DashboardUpdateListener l : this.listeners ) {
            l.handleDashboardUpdate ( data.toString ( ) );
        }       
    }

}
