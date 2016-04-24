package com.fxt.util;

//   standard libraries imports


//   third party libraries imports


//   FENIX Framework imports


//   application domain imports


/**
 * KeyValuePairRec.java<br><br>
 * Created on 2016-03-15 07:19<br><br>
 * <b>DESCRIPCION:</b><br><br>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br>
 * 2016-03-15 07:19<br>
 *Paulo Márquez </td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2016-03-15 07:19
 */
public class KeyValuePairRec {
    protected String  key;
    protected String  value;

    public KeyValuePairRec ( ) {
        this.key       = "";
        this.value     = "";

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

}

