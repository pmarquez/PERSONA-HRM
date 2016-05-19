
package com.fxt.util.fydt;

//   Standard Libraries Imports
import java.util.List;
import java.util.ArrayList;

//   Third Party Libraries Imports


//   FENIX Framework Imports


//   Domain Imports


/**
 * ListWrapper.java<br><br>
 * Creation Date 2015-06-17 16:53<br><br>
 * <b>DESCRIPTION:</b><br><br>
 * <p>fydt = F*ck you DataTables.</p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br>
 * Version Date: 2015-06-17 16:53<br>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2015-06-17 16:53
 */
//TODO Get rid of this class!
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
