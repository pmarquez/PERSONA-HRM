
package org.pmh.util;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author pmarquez
 */
public class ListWrapper {
    private List aaData;

    public ListWrapper ( ) {
        this.aaData = new ArrayList ( );
    }

    /**
     * @return the aaData
     */
    public List getAaData ( ) {
        return aaData;
    }

    /**
     * @param aaData the data to set
     */
    public void setAaData ( List aaData ) {
        this.aaData = aaData;
    }

}
