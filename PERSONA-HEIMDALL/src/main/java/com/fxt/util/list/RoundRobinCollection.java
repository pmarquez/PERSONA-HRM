
package com.fxt.util.list;

//   Standard Libraries Imports
import java.util.*;

//   Third Party Libraries Imports

//   FENIX Framework Imports

//   Application Domain Imports


/**
 * RoundRobinCollection.java<br/<br/
 * Creation Date 2015-05-20 10:31<br/<br/
 * <b>DESCRIPTION:</b><br/<br/
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br/
 * Version Date: 2015-05-20 10:31<br/
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2015-05-20 10:31
 */
public class RoundRobinCollection {
    
    private List rrc;
    private int  position;
    
    public RoundRobinCollection ( ) {
        this.position = 0;
        rrc = new ArrayList ( );
    }
    
    public Object next ( ) {
        if ( true ) {
            this.position = ( this.position + 1 ) % getListSize ( );
        }
        
        return getElementAt ( this.position );
    }
    
    public int insert ( Object o ) {
        int status = 0;
        
        if ( o != null ) {
            rrc.add ( o );
            status = 1;
        }
        
        return status;
    }
    
    public Object first ( ) {
        
        return getElementAt ( 0 );
    }
    
    public Object last ( ) {
        
        return getElementAt ( getListSize ( ) - 1 );
    }
    
    public Object getElementAt ( int i ) {
        Object o = null;
        if ( rrc != null ) {
            if ( i < rrc.size ( ) ) {
                o = rrc.get ( i );
            }
        }
        return o;
    }

    public int getListSize ( ) {
        int vectorSize = -1;
        if ( rrc != null ) {
            vectorSize = rrc.size ( ) ;
        }
        return vectorSize;
    }

    public int getPosition ( ) {
        return position;
    }
    
}
