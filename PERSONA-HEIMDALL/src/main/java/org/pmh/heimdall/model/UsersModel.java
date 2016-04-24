
package org.pmh.heimdall.model;


//   Standard Libraries Imports
import com.fxt.util.HtmlListElement;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//   Third Party Libraries Imports
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

//   FENIX Framework Imports


//   Domain Imports
import com.fxt.user.UserRec;

/**
 * UsersModel.java<br/><br/>
 * Created on 2015-06-18 09:28<br/><br/>
 * <b>DESCRIPTION:</b><br/><br/>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br/>
 * 2015-06-18 09:28<br/>
 *Paulo Márquez </td>
 *<td width="80%"><p>Creación</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2015-06-18 09:28
 */
public class UsersModel {

    /**
     * Retrieves a list of users (UserRec) from storage.
     * @param ds
     * @return ShuttleRec with an instance of List<UserRec>
     */
    public static List<UserRec> retrieveUsers ( DataSource ds ) {

        String SQLQuery = "SELECT ads_userentity.userCode , "                                                              +
                                 "IFNULL(ads_userentity.lastName,'') AS LAST_NAME, "                                       +
                                 "IFNULL(ads_userentity.firstName,'') AS FIRST_NAME, "                                     +
                                 "IFNULL(ads_userentity.email,'') AS EMAIL, "                                              +
                                 "IFNULL(ads_userentity.roleCode,0) AS ROLE_CODE, "                                        +
                                 "IFNULL(fxt_roleentity.roleName,'') AS ROLE_NAME, "                                       +
                                 "DATE_FORMAT(ads_userentity.creationDate,'%Y-%m-%d %H:%i:%s') AS CREATION_DATE, "         +
                                 "ads_userentity.active "                                                                  +

                          "FROM ads_userentity "                                                                           +

                          "LEFT OUTER JOIN fxt_roleentity ON fxt_roleentity.roleCode = ads_userentity.roleCode "           +

                          "ORDER BY ads_userentity.lastName, ads_userentity.firstName";
        
//        System.out.println ( "retrieveUsers.SQLQuery: " + SQLQuery );

        JdbcTemplate jdbcTemplate = new JdbcTemplate ( ds );
        
        List<UserRec> l = new ArrayList<> ( );

        try {
            l = jdbcTemplate.query ( SQLQuery,
                                       new RowMapper<UserRec> ( ) {
                                           @Override
                                           public UserRec mapRow ( ResultSet rs, int rowNum ) throws SQLException {
                                               UserRec r = new UserRec ( );

                                               r.setUserCode          ( rs.getInt     ( "userCode"      ) );
                                               r.setFirstName         ( rs.getString  ( "FIRST_NAME"    ) );
                                               r.setLastName          ( rs.getString  ( "LAST_NAME"     ) );
                                               r.setEmail             ( rs.getString  ( "EMAIL"         ) );
                                               r.setRoleCode          ( rs.getInt     ( "ROLE_CODE"     ) );
                                               r.setRole              ( rs.getString  ( "ROLE_NAME"     ) );
                                               r.setCreationTimestamp ( rs.getString  ( "CREATION_DATE" ) );                    
                                               r.setActive            ( rs.getBoolean ( "active"        ) );                    

                                              return r;
                                           }
                                       } );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ UsersModel.retrieveUsers: " + ex.getMessage ( ) );
        }

        return l;
    }
    
    /**
     * Retrieves a single user (UserRec) identified by "userCode" from storage.
     * @param ds
     * @param userCode The unique code that identifies a user in storage
     * @return an instance of UserRec
     */
    public static UserRec retrieveUser ( int userCode, DataSource ds ) {
        
        String SQLQuery = "SELECT ads_userentity.userCode, "                                                       +
                                 "IFNULL(ads_userentity.lastName,'') AS LAST_NAME, "                               +
                                 "IFNULL(ads_userentity.firstName,'') AS FIRST_NAME, "                             +
                                 "IFNULL(ads_userentity.email,'') AS EMAIL, "                                      +
                                 "IFNULL(ads_userentity.roleCode,0) AS ROLE_CODE, "                                +
                                 "IFNULL(fxt_roleentity.roleName,'') AS ROLE_NAME, "                               +
                                 "DATE_FORMAT(ads_userentity.creationDate,'%Y-%m-%d %H:%i:%s') AS CREATION_DATE, " +
                                 "ads_userentity.active "                                                          +

                          "FROM ads_userentity "                                                                   +

                          "LEFT OUTER JOIN fxt_roleentity ON fxt_roleentity.roleCode = ads_userentity.roleCode "   +

                          "WHERE ads_userentity.userCode = " + userCode;
        
//        System.out.println ( "retrieveUser.SQLQuery: " + SQLQuery );

        JdbcTemplate jdbcTemplate = new JdbcTemplate ( ds );
        
        UserRec r = new UserRec ( );
        
        try {
            r = jdbcTemplate.queryForObject ( SQLQuery,
                                       new RowMapper<UserRec> ( ) {
                                           @Override
                                           public UserRec mapRow ( ResultSet rs, int rowNum ) throws SQLException {
                                               UserRec r = new UserRec ( );

                                               r.setUserCode          ( rs.getInt     ( "userCode"      ) );
                                               r.setFirstName         ( rs.getString  ( "FIRST_NAME"    ) );
                                               r.setLastName          ( rs.getString  ( "LAST_NAME"     ) );
                                               r.setEmail             ( rs.getString  ( "EMAIL"         ) );
                                               r.setRoleCode          ( rs.getInt     ( "ROLE_CODE"     ) );
                                               r.setRole              ( rs.getString  ( "ROLE_NAME"     ) );
                                               r.setCreationTimestamp ( rs.getString  ( "CREATION_DATE" ) );                    
                                               r.setActive            ( rs.getBoolean ( "active"        ) );                    

                                              return r;
                                           }
                                       } );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ UsersModel.retrieveUser: " + ex.getMessage ( ) );
        }

        return r;
    }

    static String SQLUserInsertQuery = "INSERT INTO ads_userentity ( firstName, "                       +
                                                                    "lastName, "                        +
                                                                    "email, "                           +
                                                                    "roleCode, "                        +
                                                                    "passwd, "                          +
                                                                    "active ) VALUES ( :firstName, "    +
                                                                                      ":lastName, "     +
                                                                                      ":email, "        +
                                                                                      ":roleCode, "     +
                                                                                      ":passwd, "       +
                                                                                      ":active )";
    
    static String SQLUserUpdateQuery = "UPDATE ads_userentity SET firstName = :firstName, "       +
                                                                 "lastName = :lastName, "         +
                                                                 "email = :email, "               +
                                                                 "roleCode = :roleCode, "         +
                                                                 "active = :active "              +

                                       "WHERE userCode = :userCode";

    public static void persistUser ( UserRec r, DataSource ds ) {

        Map<String,Object> bind = new HashMap<>( );        
                           bind.put ( "userCode",  r.getUserCode   ( ) );
                           bind.put ( "firstName", r.getFirstName  ( ) );
                           bind.put ( "lastName",  r.getLastName   ( ) );
                           bind.put ( "email",     r.getEmail      ( ) );
                           bind.put ( "roleCode",  r.getRoleCode   ( ) );
                           bind.put ( "active",    r.isActive      ( ) );

        String SQLQuery;
        
        if ( r.getUserCode ( ) == 0 ) {   //   Does not exist in persistent storage
            bind.put ( "passwd", r.getPasswd ( ) );
            SQLQuery = SQLUserInsertQuery;
        } else {
            SQLQuery = SQLUserUpdateQuery;
        }

        System.out.println ( SQLQuery );
        
        SqlParameterSource paramSource = new MapSqlParameterSource ( bind );

        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate ( ds );

        try {
            jdbcTemplate.update ( SQLQuery, paramSource );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ UsersModel.persistUser: " + ex.getMessage ( ) );
        }

    }
    
   /**
     * Retrieves a list of all the Users Roles (HtmlListElement) from storage.
     * @param ds The Data Source to use
     * @return ShuttleRec with an instance of List<HtmlListElement>
     */
    public static List<HtmlListElement> retrieveUserRolesSelect ( DataSource ds ) {

        String SQLQuery =   "SELECT fxt_roleentity.roleCode AS _KEY, "              +
                                   "IFNULL(fxt_roleentity.roleName,'') AS _VALUE "  +

                            "FROM fxt_roleentity "                                  +

                            "WHERE fxt_roleentity.active = 1 "                      +

                            "ORDER BY _VALUE";
        
        JdbcTemplate jdbcTemplate = new JdbcTemplate ( ds );
        
        List<HtmlListElement> l = new ArrayList<> ( );
        try {
            l = jdbcTemplate.query ( SQLQuery,
                                       new RowMapper<HtmlListElement> ( ) {
                                           @Override
                                           public HtmlListElement mapRow ( ResultSet rs, int rowNum ) throws SQLException {
                                               HtmlListElement le = new HtmlListElement ( );

                                               le.setKey   ( rs.getString ( "_KEY"   ) );
                                               le.setValue ( rs.getString ( "_VALUE" ) );

                                              return le;
                                           }
                                       } );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ UsersModel.retrieveUserRolesSelect: " + ex.getMessage ( ) );
        }

        return l;

    }

   /**
     * Checks whether an user email already exists in storage.
     * @param email The email to check
     * @param ds The Data Source to use
     * @return boolean with the appropriate response
     */
    public static boolean checkForUserEmailExistence ( String email, DataSource ds ) {

        String SQLQuery =   "SELECT fi_userentity.email "           +

                            "FROM fi_userentity "                   +

                            "WHERE fi_userentity.email = '" + email + "'";
        
        JdbcTemplate jdbcTemplate = new JdbcTemplate ( ds );
        
        String theEmail = "";
        
        try {
            theEmail = jdbcTemplate.queryForObject ( SQLQuery,
                                    new RowMapper<String> ( ) {
                                           @Override
                                           public String mapRow ( ResultSet rs, int rowNum ) throws SQLException {
                                               String email = "";
                                               email = rs.getString ( "email"   );

                                               return email;
                                           }
                                        } );

        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ UsersModel.checkForUserEmailExistence: " + ex.getMessage ( ) );
        }

        return ( !theEmail.equals ( "" ) );

    }

}
