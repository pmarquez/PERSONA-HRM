/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fxt.sql;

import java.sql.ResultSet;


//   imports de librerías estándar


//   imports de librerías de terceros


//   imports de librerías de FrameWork FENIX


//   imports de librerías del dominio de la aplicación


/**
 * FXT_ExecuteQueryResponseRec.java<br/><br/>
 * Creada el Jul 24, 2009 6:56:47 PM<br/><br/>
 * <b>DESCRIPCION:</b><br/><br/>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   Historia   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br/>
 * Jul 24, 2009 6:56:47 PM<br/>
 *Pmarquez </td>
 *<td width="80%"><p>Creación</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Pmarquez
 * @version 1.0 -  Jul 24, 2009 6:56:47 PM
 */
public class FXT_ExecuteQueryResponseRec extends FXT_DBConnectionResponseRec {
    private ResultSet rs;

    public FXT_ExecuteQueryResponseRec ( ) {
        this.rs              = null;

    }

    /**
     * @return the rs
     */
    public ResultSet getResultSet ( ) {
        return rs;
    }

    /**
     * @param rs the rs to set
     */
    public void setResultSet ( ResultSet rs ) {
        this.rs = rs;
    }

}
