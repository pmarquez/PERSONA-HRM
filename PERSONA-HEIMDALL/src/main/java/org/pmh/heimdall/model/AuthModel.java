
package org.pmh.heimdall.model;


//   Standard Libraries Imports
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

//   Third Party Libraries Imports
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

//   FENIX Framework Imports

//   Domain Imports

/**
 * AuthModel.java<br><br>
 * Created on 2014-07-27 15:33<br><br>
 * <b>DESCRIPTION:</b><br><br>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br>
 * 2014-07-27 15:33<br>
 *Paulo Márquez </td>
 *<td width="80%"><p>Creación</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2014-07-27 15:33
 */
public class AuthModel {

    /**
     * 
     * @param email
     * @param ds
     * @return 
     */
    public static boolean emailExists ( String email, DataSource ds ) {
        
        String SQLQuery = "SELECT COUNT(nf_userentity.email) AS OCCURRENCES " +
                          "FROM nf_userentity "                               +
                          "WHERE nf_userentity.email = '" + email + "'";
        
//        System.out.println ( "emailExists.SQLQuery: " + SQLQuery );

        JdbcTemplate jdbcTemplate = new JdbcTemplate ( ds );
        
        int numOccurrences = 0;
        
        try {
            numOccurrences = jdbcTemplate.queryForObject ( SQLQuery,
                                                           new RowMapper<Integer> ( ) {
                                                           @Override
                                                               public Integer mapRow ( ResultSet rs, int rowNum ) throws SQLException {
                                                                   Integer i = rs.getInt ( "OCCURRENCES" );
                                                                   return i;
                                                               }
                                                           } );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ AuthModel.emailExists: " + ex.getMessage ( ) );
        }

        return ( numOccurrences > 0 );

    }

}
