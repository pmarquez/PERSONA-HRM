package org.pmh.hrm.persona.id;

//   Standard Libraries Imports

//   Third Party Libraries Imports

//   FENIX Framework Imports

//   Application Domain Imports


/**
 * IdTypeRec.java<br/><br/>
 * Creation Date 2015-02-09 19:05<br/><br/>
 * <b>DESCRIPTION:</b><br/><br/>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br/>
 * Version Date: 2015-02-09 19:05<br/>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2015-02-09 19:05
 */
public class IdTypeRec {
    private int     idTypeCode;
    private String  idType;
    private boolean active;

    public IdTypeRec ( ) {
        this.idTypeCode = 0;
        this.idType     = "";
        this.active     = false;
    }

    /**
     * @return the idTypeCode
     */
    public int getIdTypeCode() {
        return idTypeCode;
    }

    /**
     * @param idTypeCode the idTypeCode to set
     */
    public void setIdTypeCode(int idTypeCode) {
        this.idTypeCode = idTypeCode;
    }

    /**
     * @return the idType
     */
    public String getIdType() {
        return idType;
    }

    /**
     * @param idType the idType to set
     */
    public void setIdType(String idType) {
        this.idType = idType;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }
    
    
}
