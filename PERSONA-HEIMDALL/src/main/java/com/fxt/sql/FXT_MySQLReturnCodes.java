package com.fxt.sql;

import java.util.HashMap;


//   standard libraries imports


//   third party libraries imports


//   FENIX Framework imports


//   application domain imports


/**
 * FXT_MySQLReturnCodes.java<br/><br/>
 * Creada el Aug 1, 2010 3:18:50 PM<br/><br/>
 * <b>DESCRIPCION:</b><br/><br/>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   Historia   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br/>
 * Aug 1, 2010 3:18:50 PM<br/>
 *Paulo Márquez </td>
 *<td width="80%"><p>Creación</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 -  Aug 1, 2010 3:18:50 PM
 */
public class FXT_MySQLReturnCodes {

    Object [ ] [ ] dataBaseReturnCodes = { {    0, "", "No Error" },
                                           { 1146, "42S02", "Table 'table_name' doesn't exist / Base table or view not found" },
                                           { 1054, "42S22", "Unknown column ‘column_name’ in ‘where clause’ / Base table or view not found" },
                                           { 1146, "HY000", "General Error (Used for all unmapped codes)" },
                                           { 1366, "HY000", "Incorrect integer value: '<STR_VALUE>' for column 'IntColumn" },
                                           { 1142, "42000", "INSERT command denied to user '<USER>' for table '<TABLE>'" },
                                           { 1467, "HY000", "Failed to read auto-increment value from storage engine" } };
    
    public FXT_MySQLReturnCodes ( ) {

    }

}

