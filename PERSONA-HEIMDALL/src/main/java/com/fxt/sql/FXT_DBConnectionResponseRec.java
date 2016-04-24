/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fxt.sql;

//   imports de librerías de terceros


//   imports de librerías de FrameWork FENIX


//   imports de librerías del dominio de la aplicación


/**
 * FXT_DBConnectionResponseRec.java<br/><br/>
 * Creada el Jul 24, 2009 6:52:36 PM<br/><br/>
 * <b>DESCRIPCION:</b><br/><br/>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   Historia   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br/>
 * Jul 24, 2009 6:52:36 PM<br/>
 *Pmarquez </td>
 *<td width="80%"><p>Creación</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Pmarquez
 * @version 1.0 -  Jul 24, 2009 6:52:36 PM
 */
public class FXT_DBConnectionResponseRec {
    private int    errorCode;
    private String SQLState;

    public FXT_DBConnectionResponseRec ( ) {
        this.errorCode       = 0;
        this.SQLState        = "";

    }

    /**
     * @return the errorCode
     */
    public int getErrorCode ( ) {
        return errorCode;
    }

    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode ( int errorCode ) {
        this.errorCode = errorCode;
    }

    /**
     * @return the SQLState
     */
    public String getSQLState ( ) {
        return SQLState;
    }

    /**
     * @param SQLState the SQLState to set
     */
    public void setSQLState ( String SQLState ) {
        this.SQLState = SQLState;
    }

}
