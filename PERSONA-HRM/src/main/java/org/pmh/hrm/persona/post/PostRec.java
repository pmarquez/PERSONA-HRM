package org.pmh.hrm.persona.post;

//   Standard Libraries Imports

//   Third Party Libraries Imports

//   FENIX Framework Imports

//   Application Domain Imports


/**
 * PostRec.java<br/><br/>
 * Creation Date 2015-02-28 10:48<br/><br/>
 * <b>DESCRIPTION:</b><br/><br/>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br/>
 * Version Date: 2015-02-28 10:48<br/>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez Herrero 
 * @version 1.0 - 2015-02-28 10:48
 */
public class PostRec {
    private int     postCode;
    private String  postName;
    private boolean active;

    public PostRec ( ) {
        this.postCode = 0;
        this.postName = "";
        this.active = false;
    }

    /**
     * @return the postCode
     */
    public int getPostCode ( ) {
        return postCode;
    }

    /**
     * @param postCode the postCode to set
     */
    public void setPostCode ( int postCode ) {
        this.postCode = postCode;
    }

    /**
     * @return the postName
     */
    public String getPostName ( ) {
        return postName;
    }

    /**
     * @param postName the postName to set
     */
    public void setPostName ( String postName ) {
        this.postName = postName;
    }

    /**
     * @return the active
     */
    public boolean isActive ( ) {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive ( boolean active ) {
        this.active = active;
    }
}
