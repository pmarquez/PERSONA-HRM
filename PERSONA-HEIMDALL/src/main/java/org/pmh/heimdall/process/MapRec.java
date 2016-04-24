package org.pmh.heimdall.process;


//   Standard Libraries Imports
import java.util.List;
import java.util.ArrayList;

//   Third Party Libraries Imports


//   FENIX Framework Imports


//   Domain Imports
import org.pmh.heimdall.util.MappingRule;

/**
 * MapRec.java<br><br>
 * Creation Date 2016-03-15 18:53<br><br>
 * <b>DESCRIPTION:</b><br><br>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br>
 * Version Date: 2016-03-15 18:53<br>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2016-03-15 18:53
 */
public class MapRec {
    private String            payloadType;
    private String            tableName;
    private List<MappingRule> mapRules;

    public MapRec ( ) {
        this.payloadType = "";
        this.tableName   = "";
        this.mapRules    = new ArrayList<> ( );
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
     * @return the tableName
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * @param tableName the tableName to set
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * @return the mapRules
     */
    public List<MappingRule> getMapRules() {
        return mapRules;
    }

    /**
     * @param mapRules the mapRules to set
     */
    public void setMapRules(List<MappingRule> mapRules) {
        this.mapRules = mapRules;
    }
    
    /**
     * Utility method that dumps the contents of a MessageRec for trace and debugging purposes.
     */
    public void spillYourGuts ( ) {
        System.out.println ( "************************************************************************" );
        System.out.println ( "**** MapRec ************************************************************" );
        System.out.println ( "************************************************************************" );
        System.out.println ( "**** Payload Type: " + this.getPayloadType ( ) );
        System.out.println ( "**** Table Name  : " + this.getTableName   ( ) );
        System.out.println ( "**** Mapping Rules: " );

        for ( int i = 0; i < mapRules.size ( ); i++ ) {
            System.out.println ( "****     " + mapRules.get ( i ).getIncomingName ( ) );
            System.out.println ( "****     " + mapRules.get ( i ).getLocalName    ( ) );
            System.out.println ( "****     " + mapRules.get ( i ).getKeyType      ( ) );
            System.out.println ( "****     " + mapRules.get ( i ).getRelatedTable ( ) );
            System.out.println ( "****     " + mapRules.get ( i ).isRequired      ( ) );
            System.out.println ( "****     *****************************************" );
        }
        
        System.out.println ( "************************************************************************" );
        System.out.println ( "************************************************************************" );
        System.out.println ( "************************************************************************" );
    }


}
