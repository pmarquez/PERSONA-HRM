
package org.pmh.heimdall.process;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pmarquez - 2016-05-23 16:42
 */
public class DashboardElement {
    
    private String item;
    private List<DashboardUsageDataRec> data;

    public DashboardElement ( ) {
        this.item = "";
        this.data = new ArrayList<> ( );
    }

    /**
     * @return the item
     */
    public String getItem ( ) {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem ( String item ) {
        this.item = item;
    }

    /**
     * @return the data
     */
    public List<DashboardUsageDataRec> getData ( ) {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData ( List<DashboardUsageDataRec> data ) {
        this.data = data;
    }
    
    
}
