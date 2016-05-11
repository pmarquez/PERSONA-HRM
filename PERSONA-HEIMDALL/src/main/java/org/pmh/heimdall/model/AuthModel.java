
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
import com.fxt.auth.EmailBelongsToRec;
import com.fxt.auth.PwdRecoveryRec;

//   Domain Imports
import com.fxt.auth.LoginRec;
import com.fxt.auth.ChallengeWordRec;

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
    
    static String SQLRecoveryInsertQuery = "INSERT into fxt_passwordrecoveryentity ( email, "                               +
                                                                                    "userCode, "                            +
                                                                                    "uuid, "                                +
                                                                                    "dateRequested, "                       +
                                                                                    "expiresIn ) VALUES ( :email, "         +
                                                                                                         ":userCode, "      +
                                                                                                         ":uuid, "          +
                                                                                                         ":dateRequested, " +
                                                                                                         ":expiresIn )";
    
    static String SQLPersonUpdateQuery = "UPDATE fxt_passwordrecoveryentity SET dateRegen = now() " +
                                         "WHERE personCode = :personCode";
    
    /**
     * Persists a PasswordRecoveryRec to storage
     * @param ds The Data Source to use
     * @param r The RecoveryRec to persist 
     */
    public static void persistRecovery ( PwdRecoveryRec r, DataSource ds ) {

        String SQLQuery = SQLRecoveryInsertQuery;

        System.out.println ( "AuthModel.persistRecovery.SQLQuery: " + SQLQuery );
        
        Map < String, Object > bind = new HashMap<> ( );

                               bind.put ( "email",         r.getEmail            ( ) );
                               bind.put ( "userCode",      r.getUserCode         ( ) );
                               bind.put ( "uuid",          r.getUuid             ( ) );
                               bind.put ( "dateRequested", r.getDateRequestedTS  ( ) );
                               bind.put ( "expiresIn",     r.getExpiresIn        ( ) );

        SqlParameterSource paramSource = new MapSqlParameterSource ( bind );

        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate ( ds );

        KeyHolder venueKeyHolder = new GeneratedKeyHolder ( );
        
        try {
            jdbcTemplate.update ( SQLQuery, paramSource, venueKeyHolder );
            r.setLineCode ( venueKeyHolder.getKey ( ).intValue ( ) );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ PersonsModel.persistPerson: " + ex.getMessage ( ) );
        }
        
    }
    
    public static EmailBelongsToRec emailBelongsTo ( String email, DataSource ds ) {
        
        String SQLQuery = "SELECT nf_userentity.userCode, "                 +
                                 "IFNULL(nf_userentity.email,'') AS EMAIL " +

                          "FROM nf_userentity "                             +

                          "WHERE nf_userentity.email = '" + email + "'";

        JdbcTemplate jdbcTemplate = new JdbcTemplate ( ds );
        
        EmailBelongsToRec r = new EmailBelongsToRec ( );
        
        try {
            r = jdbcTemplate.queryForObject ( SQLQuery,
                                              new RowMapper<EmailBelongsToRec> ( ) {
                                                @Override
                                                public EmailBelongsToRec mapRow ( ResultSet rs, int rowNum ) throws SQLException {
                                                    EmailBelongsToRec r = new EmailBelongsToRec ( );
                                                    
                                                    r.setUserCode ( rs.getInt    ( "userCode" ) );
                                                    r.setEmail    ( rs.getString ( "EMAIL"    ) );

                                                    return r;
                                                }
                                              } );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ AuthModel.emailBelongsTo: " + ex.getMessage ( ) );
        }

        return r;

    }

}
