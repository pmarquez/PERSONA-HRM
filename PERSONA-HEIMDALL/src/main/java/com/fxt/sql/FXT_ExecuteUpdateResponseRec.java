/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fxt.sql;


//   imports de librerías estándar


//   imports de librerías de terceros


//   imports de librerías de FrameWork FENIX


//   imports de librerías del dominio de la aplicación


/**
 * FXT_ExecuteUpdateResponseRec.java<br/><br/>
 * Creada el Jul 24, 2009 7:00:04 PM<br/><br/>
 * <b>DESCRIPCION:</b><br/><br/>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   Historia   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br/>
 * Jul 24, 2009 7:00:04 PM<br/>
 *Paulo Márquez </td>
 *<td width="80%"><p>Creación</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 -  Jul 24, 2009 7:00:04 PM
 */
public class FXT_ExecuteUpdateResponseRec extends FXT_DBConnectionResponseRec {
    private int lastInsertedId;
    private int numRowsAffected;

    public FXT_ExecuteUpdateResponseRec ( ) {
        super ( );
        
        this.lastInsertedId  = -1;
        this.numRowsAffected = -1;

    }

    /**
     * @return the lastInsertedId
     */
    public int getLastInsertedId ( ) {
        return lastInsertedId;
    }

    /**
     * @param lastInsertedId the lastInsertedId to set
     */
    public void setLastInsertedId ( int lastInsertedId ) {
        this.lastInsertedId = lastInsertedId;
    }

    /**
     * @return the numRowsAffected
     */
    public int getNumRowsAffected ( ) {
        return numRowsAffected;
    }

    /**
     * @param numRowsAffected the numRowsAffected to set
     */
    public void setNumRowsAffected ( int numRowsAffected ) {
        this.numRowsAffected = numRowsAffected;
    }

}
