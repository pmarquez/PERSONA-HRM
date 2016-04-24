/*
 * OM Soluciones Empresariales, C.A.
 *
 */

package com.fxt.sql;

//   imports de librerías estándar
import javax.naming.*;
import javax.sql.*;
import java.sql.*;

//   imports de librerías de terceros


//   imports de librerías de OM Soluciones Empresariales, C.A.


//   imports de librerías del dominio de la aplicación


/**
 * OMS_DSConnection.java<br/><br/>
 *
 * <b>DESCRIPCION:</b><br/><br/>
 * <p>Clase que implementa acceso a bases de datos vía JDBC y provee una capa 
 *de abstracción para facilitar este propósito.</p>
 *
 * Creada el 23 de Marzo de 2003, 3:36 PM<br/><br/>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   Historia   </th></tr>
 *<tr>
 *<td width="20%">Version 0.7<br>
 *15 de diciembre de 2006<br/>
 *Paulo Márquez </td>
 *<td width="80%"><p>Se mejoró el cierre y liberación de recursos de los pool de conexiones.</p></td>
 *</tr>
 *<tr>
 *<td width="20%">Version 0.6b<br/>
 *05 de abril de 2005<br/>
 *Paulo Márquez </td>
 *<td width="80%"><p>Se agregó la capacidad (BETA) de agrupar queries en transacciones ACID (MySQL).</p></td>
 *</tr>
 *<tr>
 *<td width="20%">Version 0.5b<br/>
 *30 de junio de 2003<br/>
 *Paulo Márquez </td>
 *<td width="80%"><p>Se modificó la clase para que trabaje con Apache Tomcat 4 Datasources.</p></td>
 *</tr>
 *<tr>
 *<td width="20%">Version 0.1b<br/>
 *23 de marzo de 2003<br/>
 *Paulo Márquez </td>
 *<td width="80%"><p>Creación para trabajar con Orion Server.</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 0.7 - 15 de diciembre de 2006
 */

public class FXT_DSConnection {
    private boolean autoCommitSavedState           = true;
    private int     transactionIsolationSavedState = 0;

    private DataSource ds    = null;
    private Connection conn  = null;
    private Statement  stmt  = null;
    private ResultSet  rs    = null;
    
    private int        lii   = -1;

    /** Creates new FXT_DSConnection 
     * @deprecated
     */
    public FXT_DSConnection ( ) {

        try {
            Context ctx = new InitialContext ( );
            ds = ( DataSource ) ctx.lookup ( "java:comp/env/jdbc/sistraport" );     //   TOMCAT / Glassfish
            conn = ds.getConnection ( );

        } catch ( java.sql.SQLException sex ) {
            System.err.println ( "FXT_DSConnection - SQLException @ FXT_DSConnection.FXT_DSConnection ( ): " + sex.getMessage ( ) );
//            sex.printStackTrace ( );
        } catch ( javax.naming.NamingException nex ) {
            System.err.println ( "FXT_DSConnection - NamingException @ FXT_DSConnection.FXT_DSConnection ( ): " + nex.getMessage ( ) );
//            nex.printStackTrace ( );
        }
    }
    
    /** Creates new FXT_DSConnection */
    public FXT_DSConnection ( String dsn ) {

        try {
            Context ctx = new InitialContext ( );
            ds = ( DataSource ) ctx.lookup ( dsn );     //   TOMCAT / Glassfish
            conn = ds.getConnection ( );

        } catch ( java.sql.SQLException sex ) {
            System.err.println ( "FXT_DSConnection - SQLException @ FXT_DSConnection.FXT_DSConnection ( ): " + sex.getMessage ( ) );
//            sex.printStackTrace ( );
        } catch ( javax.naming.NamingException nex ) {
            System.err.println ( "FXT_DSConnection - NamingException @ FXT_DSConnection.FXT_DSConnection ( ): " + nex.getMessage ( ) );
//            nex.printStackTrace ( );
        }
    }
    
    /** Creates new FXT_DSConnection
     * @param dscn DataSource Connection String
     */
//    public FXT_DSConnection ( String dscn ) {
//        try {
//          Context ctx = new InitialContext ( );
//          ds = ( DataSource ) ctx.lookup ( dscn );
//        } catch ( javax.naming.NamingException nex ) {
//            System.err.println ( "FXT_DSConnection - NamingException @ FXT_DSConnection.FXT_DSConnection ( ): " + nex.getMessage ( ) );
////            nex.printStackTrace ( );
//        }
//    }
    
    public String getVersion ( ) {
//        return "Version 0.7 - 2006-12-15";
        return "Version 1.1 - 2012-11-18";

    }
    
    public Connection returnConnection ( ) {        
        return this.conn;
    }
    
    public FXT_ExecuteQueryResponseRec executeQuery ( String SQLQuery ) {

        FXT_ExecuteQueryResponseRec r = new FXT_ExecuteQueryResponseRec ( );

        try {
            if ( conn != null )  {
                stmt = conn.createStatement ( );
                r.setResultSet ( stmt.executeQuery ( SQLQuery ) );
            }
        } catch ( SQLException ex ) {
            r.setErrorCode ( ex.getErrorCode ( ) );
            r.setSQLState  ( ex.getSQLState  ( ) );

            System.out.println ( "executeQuery - Error Code: " + ex.getErrorCode ( ) );
            System.out.println ( "executeQuery - SQLState  : " + ex.getSQLState  ( ) );

            System.err.println ( "SQLException @ FXT_DSConnection.executeQuery: " + ex.getMessage ( ) );
            System.out.println ( "SQLQuery: " + SQLQuery );
        }
        return r;
    }

    public FXT_ExecuteUpdateResponseRec executeUpdate ( String SQLQuery ) {

        FXT_ExecuteUpdateResponseRec r = new FXT_ExecuteUpdateResponseRec ( );

        try {
            stmt = conn.createStatement ( );
            int nra = stmt.executeUpdate ( SQLQuery );
            System.out.println ( "%%%%%%%%%% FXT_DSConnection.executeUpdate - NUM ROWS AFFECTED: " + nra );
            r.setNumRowsAffected ( nra );
            
            try {
                rs = stmt.executeQuery ( "SELECT LAST_INSERT_ID() AS LAST_INSERTED_ID" );
                
                if ( rs != null ) {
                    while ( rs.next ( ) ) {
                        int auxLii = rs.getInt ( "LAST_INSERTED_ID" );
                        System.out.println ( "%%%%%%%%%% FXT_DSConnection.executeUpdate - LAST_INSERTED_ID: " + auxLii );

                        this.lii = auxLii;   //TODO - VUELATE LA VARIABLE this.lii 
                        r.setLastInsertedId ( rs.getInt ( "LAST_INSERTED_ID" ) );
                        this.lii = r.getLastInsertedId ( );

                    }
                }
            } catch ( SQLException ex ) {
                r.setErrorCode ( ex.getErrorCode ( ) );
                r.setSQLState  ( ex.getSQLState  ( ) );

                System.out.println ( "%%%%%%%%%% FXT_DSConnection.executeUpdate - (Getting Last Inserted ID) Error Code (1): " + ex.getErrorCode ( ) );
                System.out.println ( "%%%%%%%%%% FXT_DSConnection.executeUpdate - (Getting Last Inserted ID) SQLState   (1): " + ex.getSQLState  ( ) );
                System.err.println ( "%%%%%%%%%% FXT_DSConnection.executeUpdate - SQLException @ FXT_DSConnection.executeUpdate (Getting Last Inserted ID) (1): " + ex.getMessage ( ) );

                System.out.println ( "SQLQuery: " + SQLQuery );
            }

            
            
        } catch (SQLException ex) {
            r.setErrorCode ( ex.getErrorCode ( ) );
            r.setSQLState  ( ex.getSQLState  ( ) );

            System.out.println ( "%%%%%%%%%% FXT_DSConnection.executeUpdate - Error Code (2): " + ex.getErrorCode ( ) );
            System.out.println ( "%%%%%%%%%% FXT_DSConnection.executeUpdate - SQLState   (2): " + ex.getSQLState  ( ) );
            System.err.println ( "%%%%%%%%%% FXT_DSConnection.executeUpdate - SQLException @ FXT_DSConnection.executeUpdate (2): " + ex.getMessage ( ) );
            System.out.println ( "SQLQuery: " + SQLQuery );
        }
        
        return r;
    }

    public void beginTransaction ( ) {
        try {
            conn = ds.getConnection ( );
            autoCommitSavedState = conn.getAutoCommit ( );
            transactionIsolationSavedState = conn.getTransactionIsolation ( );
            
            conn.setAutoCommit ( false );
            conn.setTransactionIsolation ( conn.TRANSACTION_REPEATABLE_READ );
            stmt = conn.createStatement ( );
        } catch ( SQLException ex ) {
            try {
                conn.setAutoCommit ( autoCommitSavedState );
                conn.setTransactionIsolation ( transactionIsolationSavedState );
            } catch ( SQLException ex2 ) {
                System.err.println ( "SQLException @ FXT_DSConnection.beginTransaction ( ex2 ): " + ex2.getMessage ( ) );
            }
            System.err.println ( "SQLException @ FXT_DSConnection.beginTransaction ( ex ): " + ex.getMessage ( ) );
        }
    }

    public boolean execute ( String SQLQuery ) {
        boolean success = true;
        
        try {
            stmt = conn.createStatement ( );
            stmt.execute ( SQLQuery );
        } catch ( SQLException ex ) {
            success = false;
            System.err.println ( "SQLException @ FXT_DSConnection.execute ( ): " + ex.getMessage ( ) );
            System.err.println ( SQLQuery );
        }
        return success;
    }
    
    /**
     * @return int
     * @deprecated 
     */
    public int getLastGeneratedKeys ( ) {
        return this.lii;
    }

    public void endTransaction ( boolean success ) {
        try {
            if ( success ) {
                conn.commit ( );
                System.err.println ( "TRANSACTION COMMITED" );
            } else {
                conn.rollback ( );
                System.err.println ( "TRANSACTION ROLLED BACK" );
            }
            conn.setAutoCommit ( autoCommitSavedState );
            conn.setTransactionIsolation ( transactionIsolationSavedState );
        } catch ( SQLException ex ) {
            System.err.println ( "SQLException @ FXT_DSConnection.endTransaction: " + ex.getMessage ( ) );
        }
    }

    
    public void closeConnection ( ) {
        try {            
            stmt.close ( );
            stmt = null;
        
        } catch ( SQLException ex ) {
            System.err.println ( "SQLException @ FXT_DSConnection.closeConnection [ stmt.close ( ) ] : " + ex.getMessage ( ) );
            System.err.println ( "***** SQLException @ FXT_DSConnection.closeConnection [ stmt.close ( ) ] *********************************************" );
            ex.printStackTrace ( );
            System.err.println ( "***** SQLException @ FXT_DSConnection.closeConnection [ stmt.close ( ) ] *********************************************" );

        } finally {
            if ( stmt != null ) {
                try { 
                    stmt.close ( ); 
                } catch ( SQLException e ) { 
                    System.err.println ( "SQLException @ FXT_DSConnection.closeConnection ( ) [ stmt.close ( ) ] [ finally ] : " + e.getMessage ( ) );
                }
                stmt = null;

            }

        }

        try {            
            conn.close ( ); // Return to connection pool
            conn = null;    // Make sure we don't close it twice

        } catch ( SQLException ex ) {
            System.err.println ( "SQLException @ FXT_DSConnection.closeConnection [ conn.close ( ) ] : " + ex.getMessage ( ) );
            System.err.println ( "***** SQLException @ FXT_DSConnection.closeConnection [ conn.close ( ) ] *********************************************" );
            ex.printStackTrace ( );
            System.err.println ( "***** SQLException @ FXT_DSConnection.closeConnection [ conn.close ( ) ] *********************************************" );

        } finally {
            if ( conn != null ) {
                try { 
                    conn.close ( );
                } catch ( SQLException e ) { 
                    System.err.println ( "SQLException @ FXT_DSConnection.closeConnection ( ) [ conn.close ( ) ] [ finally ] : " + e.getMessage ( ) );
                }
                conn = null;
            }
        }        
    }
}
