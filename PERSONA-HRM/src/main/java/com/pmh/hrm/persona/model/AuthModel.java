
package com.pmh.hrm.persona.model;

import com.pmh.hrm.persona.auth.LoginRec;
import com.pmh.hrm.persona.user.UserRec;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

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

    /**
     * Determines the validity of a login attempt.
     * @param username
     * @param passwd
     * @param pernoctaDataSource
     * @return ShuttleRec with an instance of LoginRec
     */
    public static LoginRec authorizeUser ( String username, String passwd, DataSource pernoctaDataSource ) {
        
        String SQLQuery =   "SELECT pnc_userentity.userCode, "                                                               +
                                   "IFNULL(pnc_userentity.firstName,'') AS FIRST_NAME, "                                     +
                                   "IFNULL(pnc_userentity.lastName,'') AS LAST_NAME, "                                       +
                                   "IFNULL(pnc_userentity.email,'') AS EMAIL, "                                              +
                                   "IFNULL(pnc_userentity.passcode,'') AS PASSWD, "                                          +
                                   "IFNULL(pnc_roleentity.roleCode,0) AS ROLE_CODE, "                                        +
                                   "IFNULL(pnc_roleentity.roleName,'') AS ROLE_NAME, "                                       +
//                                   "pnc_userentity.creationDate, "                                                           +
                                   "pnc_userentity.active "                                                                  +

                            "FROM pnc_userentity "                                                                           +

                            "LEFT OUTER JOIN pnc_roleentity ON pnc_roleentity.roleCode = pnc_userentity.roleCode "           +

                            "WHERE pnc_userentity.email = '" + username + "' AND "                                           + 
                                  "pnc_userentity.passcode = '" + passwd + "'";
        
        System.out.println ( "SQLQuery: " + SQLQuery );
        
        JdbcTemplate jdbcTemplate = new JdbcTemplate ( pernoctaDataSource );

        LoginRec lr = new LoginRec ( );
        
        try {
            lr = jdbcTemplate.queryForObject ( SQLQuery,
                                               new RowMapper<LoginRec> ( ) {
                                               @Override
                                               public LoginRec mapRow(ResultSet rs, int rowNum) throws SQLException {
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
     * Retrieves a single user (UserRec) from storage.
     * @param accountCode
     * @param pernoctaDataSource
     * @param userCode
     * @return ShuttleRec with an instance of UserRec
     */
    public static UserRec authorizeUser ( int userCode, DataSource pernoctaDataSource, int accountCode ) {

        String SQLQuery =   "SELECT spidy_userentity.userCode, "                                                               +
                                   "IFNULL(pnc_userentity.firstName,'') AS FIRST_NAME, "                                     +
                                   "IFNULL(pnc_userentity.lastName,'') AS LAST_NAME, "                                       +
                                   "IFNULL(pnc_userentity.email,'') AS EMAIL, "                                              +
                                   "IFNULL(pnc_roleentity.roleCode,0) AS ROLE_CODE, "                                        +
                                   "IFNULL(pnc_roleentity.roleName,'') AS ROLE_NAME, "                                       +
                                   "pnc_userentity.creationDate, "                                                           +
                                   "pnc_userentity.active "                                                                  +

                            "FROM pnc_userentity "                                                                           +

                            "LEFT OUTER JOIN pnc_roleentity ON pnc_roleentity.roleCode = pnc_userentity.roleCode "    +

                            "WHERE pnc_userentity.userCode = " + userCode; 
        
        JdbcTemplate jdbcTemplate = new JdbcTemplate ( pernoctaDataSource );

        UserRec ur = new UserRec ( );
        
        try {
            ur = jdbcTemplate.queryForObject ( SQLQuery,
                                               new RowMapper<UserRec> ( ) {
                                               @Override
                                               public UserRec mapRow(ResultSet rs, int rowNum) throws SQLException {
                                                   UserRec ur = new UserRec ( );
                                  
                                                   ur.setUserCode   ( rs.getInt    ( "userCode"   ) );
                                                   ur.setFirstName  ( rs.getString ( "FIRST_NAME" ) );
                                                   ur.setLastName   ( rs.getString ( "LAST_NAME"  ) );
                                                   ur.setEmail      ( rs.getString ( "EMAIL"      ) );
                                                   ur.setRoleCode   ( rs.getInt    ( "ROLE_CODE"  ) );
                                                   ur.setRole       ( rs.getString ( "ROLE_NAME"  ) );
                                                   ur.setActive     ( rs.getInt ( "active" ) == 1 );

                                                  return ur;
                                               }
                                           } );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ AccountsModel.authorizeUser: " + ex.getMessage ( ) );
        }

        return ur;
    }

}