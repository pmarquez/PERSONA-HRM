
package org.pmh.heimdall.util;

//   Standard Libraries Imports


//   Third Party Libraries Imports


//   FENIX Framework Imports


//   Domain Imports


/**
 * MappingRule.java<br><br>
 * Creation Date 2016-03-15 18:52<br><br>
 * <b>DESCRIPTION:</b><br><br>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br>
 * Version Date: 2016-03-15 18:52<br>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2016-03-15 18:52
 */
public class MappingRule {
    private String  incomingName;
    private String  localName;
    private String  keyType;
    private String  relatedTable;
    private String  relatedField;
    private boolean required;

    public MappingRule ( ) {
        this.incomingName = "";
        this.localName    = "";
        this.keyType      = "";
        this.relatedTable = "";
        this.relatedField = "";
        this.required     = false;
    }

    /**
     * @return the incomingName
     */
    public String getIncomingName() {
        return incomingName;
    }

    /**
     * @param incomingName the incomingName to set
     */
    public void setIncomingName(String incomingName) {
        this.incomingName = incomingName;
    }

    /**
     * @return the localName
     */
    public String getLocalName() {
        return localName;
    }

    /**
     * @param localName the localName to set
     */
    public void setLocalName(String localName) {
        this.localName = localName;
    }

    /**
     * @return the required
     */
    public boolean isRequired() {
        return required;
    }

    /**
     * @param required the required to set
     */
    public void setRequired(boolean required) {
        this.required = required;
    }

    /**
     * @return the keyType
     */
    public String getKeyType() {
        return keyType;
    }

    /**
     * @param keyType the keyType to set
     */
    public void setKeyType ( String keyType ) {
        this.keyType = keyType;
    }

    /**
     * @return the relatedTable
     */
    public String getRelatedTable ( ) {
        return relatedTable;
    }

    /**
     * @param relatedTable the relatedTable to set
     */
    public void setRelatedTable ( String relatedTable ) {
        this.relatedTable = relatedTable;
    }

    /**
     * @return the relatedField
     */
    public String getRelatedField() {
        return relatedField;
    }

    /**
     * @param relatedField the relatedField to set
     */
    public void setRelatedField(String relatedField) {
        this.relatedField = relatedField;
    }

}
