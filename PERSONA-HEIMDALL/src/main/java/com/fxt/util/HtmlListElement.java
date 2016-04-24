package com.fxt.util;

//   standard libraries imports


//   third party libraries imports


//   FENIX Framework imports


//   application domain imports


/**
 * HTMLListElement.java<br><br>
 * Created on 2014-09-27 04:55<br><br>
 * <b>DESCRIPCION:</b><br><br>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br>
 * 2014-09-27 04:55<br>
 *Paulo Márquez </td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2014-09-27 04:55
 */
public class HtmlListElement {
    protected String  key;
    protected String  value;
    protected boolean isDefault;

    public HtmlListElement ( ) {
        this.key       = "";
        this.value     = "";
        this.isDefault = false;

    }

    /**
     * @return the key
     */
    public String getKey ( ) {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey ( String key ) {
        this.key = key;
    }

    /**
     * @return the value
     */
    public String getValue ( ) {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue ( String value ) {
        this.value = value;
    }

    /**
     * @return the isDefault
     */
    public boolean isDefaultValue ( ) {
        return isDefault;
    }

    /**
     * @param isDefault the isDefault to set
     */
    public void setDefaultValue ( boolean isDefault ) {
        this.isDefault = isDefault;
    }

}

