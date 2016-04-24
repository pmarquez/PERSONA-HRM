
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
 * AuthModel.java<br/><br/>
 * Created on 2014-07-27 15:33<br/><br/>
 * <b>DESCRIPTION:</b><br/><br/>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br/>
 * 2014-07-27 15:33<br/>
 *Paulo Márquez </td>
 *<td width="80%"><p>Creación</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2014-07-27 15:33
 */
public class AuthModel {

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

    /**
     * Determines the validity of a login attempt.
     * @param username
     * @param ds
     * @return LoginRec, empty or otherwise.
     */
    public static LoginRec authorizeUser ( String username, DataSource ds ) {
        
        String SQLQuery =   "SELECT nf_userentity.userCode, "                                                               +
                                   "IFNULL(nf_userentity.firstName,'') AS FIRST_NAME, "                                     +
                                   "IFNULL(nf_userentity.lastName,'') AS LAST_NAME, "                                       +
                                   "IFNULL(nf_userentity.email,'') AS EMAIL, "                                              +
                                   "IFNULL(nf_userentity.passwd,'') AS PASSWD, "                                            +
                                   "IFNULL(nf_userentity.roleCode,0) AS ROLE_CODE, "                                        +
                                   "IFNULL(fxt_roleentity.roleName,'') AS ROLE_NAME, "                                      +
                                   "nf_userentity.creationDate, "                                                           +
                                   "nf_userentity.active "                                                                  +

                            "FROM nf_userentity "                                                                           +

                            "LEFT OUTER JOIN fxt_roleentity ON fxt_roleentity.roleCode = nf_userentity.roleCode "           +

                            "WHERE EMAIL = '" + username + "'";

        JdbcTemplate jdbcTemplate = new JdbcTemplate ( ds );

        LoginRec lr = new LoginRec ( );
        
        System.out.println ( SQLQuery );
        
        try {
            lr = jdbcTemplate.queryForObject ( SQLQuery,
                                               new RowMapper<LoginRec> ( ) {
                                               @Override
                                               public LoginRec mapRow ( ResultSet rs, int rowNum ) throws SQLException {
                                                   LoginRec lr = new LoginRec ( );
                                  
                                                   lr.setUserCode    ( rs.getInt    ( "userCode"     ) );
                                                   lr.setFirstName   ( rs.getString ( "FIRST_NAME"   ) );
                                                   lr.setLastName    ( rs.getString ( "LAST_NAME"    ) );
                                                   lr.setEmail       ( rs.getString ( "EMAIL"        ) );
                                                   lr.setPasswd      ( rs.getString ( "PASSWD"       ) );
                                                   lr.setRoleCode    ( rs.getInt    ( "ROLE_CODE"    ) );
                                                   lr.setRole        ( rs.getString ( "ROLE_NAME"    ) );
                                                   lr.setActive      ( rs.getInt ( "active" ) == 1 );

                                                   return lr;
                                               }
                                           } );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ AuthModel.authorizeUser: " + ex.getMessage ( ) );
        }

        return lr;
    }
   
    /**
     * Determines the validity of a login attempt.
     * @param username
     * @param passwd
     * @param ds
     * @return LoginRec, empty or otherwise.
     */
    public static LoginRec authorizeUser ( String username, String passwd, DataSource ds ) {
        
        String SQLQuery =   "SELECT nf_userentity.userCode, "                                                               +
                                   "IFNULL(nf_userentity.firstName,'') AS FIRST_NAME, "                                     +
                                   "IFNULL(nf_userentity.lastName,'') AS LAST_NAME, "                                       +
                                   "IFNULL(nf_userentity.email,'') AS EMAIL, "                                              +
                                   "IFNULL(nf_userentity.passwd,'') AS PASSWD, "                                            +
                                   "IFNULL(nf_userentity.roleCode,0) AS ROLE_CODE, "                                        +
                                   "IFNULL(fxt_roleentity.roleName,'') AS ROLE_NAME, "                                      +
                                   "nf_userentity.creationDate, "                                                           +
                                   "nf_userentity.active "                                                                  +

                            "FROM nf_userentity "                                                                           +

                            "LEFT OUTER JOIN fxt_roleentity ON fxt_roleentity.roleCode = nf_userentity.roleCode "           +

                            "WHERE EMAIL = '" + username + "' AND "                                                         + 
                                  "PASSWD = '" + passwd + "'";

        JdbcTemplate jdbcTemplate = new JdbcTemplate ( ds );

        LoginRec lr = new LoginRec ( );
        
        System.out.println ( SQLQuery );
        
        try {
            lr = jdbcTemplate.queryForObject ( SQLQuery,
                                               new RowMapper<LoginRec> ( ) {
                                               @Override
                                               public LoginRec mapRow ( ResultSet rs, int rowNum ) throws SQLException {
                                                   LoginRec lr = new LoginRec ( );
                                  
                                                   lr.setUserCode    ( rs.getInt    ( "userCode"     ) );
                                                   lr.setFirstName   ( rs.getString ( "FIRST_NAME"   ) );
                                                   lr.setLastName    ( rs.getString ( "LAST_NAME"    ) );
                                                   lr.setEmail       ( rs.getString ( "EMAIL"        ) );
                                                   lr.setPasswd      ( rs.getString ( "PASSWD"       ) );
                                                   lr.setRoleCode    ( rs.getInt    ( "ROLE_CODE"    ) );
                                                   lr.setRole        ( rs.getString ( "ROLE_NAME"    ) );
                                                   lr.setActive      ( rs.getInt ( "active" ) == 1 );

                                                   return lr;
                                               }
                                           } );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ AuthModel.authorizeUser: " + ex.getMessage ( ) );
        }

        return lr;
    }
   
    /**
     * Picks asingle challenge word from storage.
     * @param ds
     * @return ShuttleRec with an instance of ChallengeWordRec
     */
    public static ChallengeWordRec pickChallengeWord ( DataSource ds ) {

        String SQLQuery =   "SELECT ads_15letterwords.wordCode, "                                                  +
                                   "IFNULL(ads_15letterwords.word,'SUPERCALIFRAGILISTICEXPIALIDOCIOUS') AS WORD, " +
                                   "IFNULL(ads_15letterwords.timesSelected,0) AS TIMES_SELECTED "                  +
                            "FROM ads_15letterwords "                                                              +
                            "ORDER BY RAND() "                                                                     +
                            "LIMIT 1";  
                
        JdbcTemplate jdbcTemplate = new JdbcTemplate ( ds );

        ChallengeWordRec r = new ChallengeWordRec ( );

        try {
            r = jdbcTemplate.queryForObject ( SQLQuery,
                                               new RowMapper<ChallengeWordRec> ( ) {
                                               @Override
                                               public ChallengeWordRec mapRow(ResultSet rs, int rowNum) throws SQLException {
                                                   ChallengeWordRec r = new ChallengeWordRec ( );

                                                   r.setWordCode      ( rs.getInt    ( "wordCode"       ) );
                                                   r.setWord          ( rs.getString ( "WORD"           ) );
                                                   r.setTimesSelected ( rs.getInt    ( "TIMES_SELECTED" ) );

                                                   return r;
                                              }
                                          } );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ AuthModel.pickChallengeWord: " + ex.getMessage ( ) );
        }

        return r;

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
   
    /**
     * Retrieves a PwdRecoveryRec from storage.
     * @param recStr
     * @param ds
     * @return ShuttleRec with an instance of ChallengeWordRec
     */
    public static PwdRecoveryRec retrieveRecoveryInfo ( String recStr, DataSource ds ) {

        String SQLQuery =   "SELECT fxt_passwordrecoveryentity.lineCode, "                            +
                                   "fxt_passwordrecoveryentity.email, "                               +
                                   "fxt_passwordrecoveryentity.userCode, "                            +
                                   "fxt_passwordrecoveryentity.uuid, "                                +
                                   "DATE_FORMAT(dateRequested,'%Y-%m-%d %H:%i:%s') AS REQUEST_DATE, " +
                                   "fxt_passwordrecoveryentity.expiresIn, "                           +
                                   "DATE_FORMAT(dateRecovered,'%Y-%m-%d %H:%i:%s') AS REGEN_DATE "    +

                            "FROM fxt_passwordrecoveryentity "                                        + 

                            "WHERE fxt_passwordrecoveryentity.uuid = '" + recStr + "'";  

        JdbcTemplate jdbcTemplate = new JdbcTemplate ( ds );

        PwdRecoveryRec r = new PwdRecoveryRec ( );

        try {
            r = jdbcTemplate.queryForObject ( SQLQuery,
                                               new RowMapper<PwdRecoveryRec> ( ) {
                                               @Override
                                               public PwdRecoveryRec mapRow(ResultSet rs, int rowNum) throws SQLException {
                                                   PwdRecoveryRec r = new PwdRecoveryRec ( );

                                                   r.setEmail         ( rs.getString ( "email"          ) );
                                                   r.setUserCode      ( rs.getInt    ( "userCode"       ) );
                                                   r.setUuid          ( rs.getString ( "uuid"           ) );
                                                   r.setDateRequested ( rs.getString ( "REQUEST_DATE"   ) );
                                                   r.setExpiresIn     ( rs.getInt    ( "expiresIn"      ) );
                                                   
                                                   if ( rs.getString ( "REGEN_DATE" ) != null ) {
                                                       r.setDateRegen ( rs.getString ( "REGEN_DATE"     ) );
                                                   }

                                                   return r;

                                              }
                                          } );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ AuthModel.retrieveRecoveryInfo: " + ex.getMessage ( ) );
        }

        return r;

    }
}
