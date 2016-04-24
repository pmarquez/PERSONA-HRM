
package org.pmh.heimdall.process;

//   Standard Libraries Imports
import java.util.ArrayList;
import java.util.List;

//   Third Party Libraries Imports


//   FENIX Framework Imports
import com.fxt.util.KeyValuePairRec;

//   Domain Imports


/**
 * DataRec.java<br><br>
 * Creation Date 2016-03-15 07:47<br><br>
 * <b>DESCRIPTION:</b><br><br>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br>
 * Version Date: 2016-03-15 07:47<br>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2016-03-15 07:47
 */
public class DataRec {
    private String payloadType;
    private List<KeyValuePairRec> payload;

    public DataRec ( ) {
        this.payloadType = "";
        this.payload     = new ArrayList<> ( );
    }

    /**
     * @return the payloadType
     */
    public String getPayloadType() {
        return payloadType;
    }

    /**
     * @param payloadType the payloadType to set
     */
    public void setPayloadType(String payloadType) {
        this.payloadType = payloadType;
    }

    /**
     * @return the payload
     */
    public List<KeyValuePairRec> getPayload() {
        return payload;
    }

    /**
     * @param payload the payload to set
     */
    public void setPayload(List<KeyValuePairRec> payload) {
        this.payload = payload;
    }

    /**
     * Utility method that dumps the contents of a MessageRec for trace and debugging purposes.
     */
    public void spillYourGuts ( ) {
        System.out.println ( "************************************************************************" );
        System.out.println ( "**** DataRec ***********************************************************" );
        System.out.println ( "************************************************************************" );

        System.out.println ( "**** Payload Type: " + this.getPayloadType ( ) );

        System.out.println ( "**** Payload:" );
        for ( int i = 0; i < payload.size ( ); i++ ) {
            System.out.println ( "****     " + payload.get ( i ).getKey ( ) + ": " + payload.get ( i ).getValue ( ) );
        }

        System.out.println ( "\n" );
        
        System.out.println ( "************************************************************************" );
        System.out.println ( "************************************************************************" );
        System.out.println ( "************************************************************************" );
    }
    
}
