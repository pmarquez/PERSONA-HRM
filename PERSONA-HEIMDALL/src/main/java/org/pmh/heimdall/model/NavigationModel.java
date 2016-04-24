
package org.pmh.heimdall.model;


//   Standard Libraries Imports
import com.fxt.navigation.NavCommandRec;
import com.fxt.navigation.NavSectionRec;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//   Third Party Libraries Imports
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

//   FENIX Framework Imports


//   Domain Imports


/**
 * NavigationModel.java<br/><br/>
 * Created on 2015-06-18 11:54<br/><br/>
 * <b>DESCRIPTION:</b><br/><br/>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br/>
 * 2015-06-18 11:54<br/>
 *Paulo Márquez </td>
 *<td width="80%"><p>Creación</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2015-06-18 11:54
 */
public class NavigationModel {

    /**
     * Retrieves the navigation allowed for a specific role type.
     * @param roleCode the Code of the Role to retreve the navigation for.
     * @param ds
     * @return An instance of List<NavSectionRec>
     */
    public static List<NavSectionRec> retrieveNavigation ( int roleCode, DataSource ds ) {
        
        String SQLQuery = "SELECT fxt_sectionentity.sectionCode, "                                        +
                                 "IFNULL(fxt_sectionentity.sectionClass,'') AS SECTION_CLASS, "           +
                                 "IFNULL(fxt_sectionentity.sectionName,'') AS SECTION_NAME, "             +
                                 "fxt_sectionentity.tabTypeCode, "                                        +
                                 "IFNULL(fxt_sectionentity.subMenuLabel,'') AS SUB_MENU_LABEL, "          +
                                 "fxt_sectionentity.cardinality, "                                        +
                                 "IFNULL(fxt_sectionentity.controllerCommand,'') AS CONTROLLER_COMMAND, " +
                                 "fxt_sectionentity.isDefault, "                                          +
                                 "fxt_sectionentity.active "                                              +

                          "FROM fxt_sectionentity "                                                       +

                          "WHERE fxt_sectionentity.active = 1 AND "                                                                                                      +
                                "fxt_sectionentity.sectionCode IN ( SELECT DISTINCT(fxt_commandentity.sectionCode) "                                                     + 
                                                                   "FROM fxt_commandsxrole "                                                                             +
                                                                   "LEFT OUTER JOIN fxt_commandentity ON fxt_commandentity.commandCode = fxt_commandsxrole.commandCode " +
                                                                   "WHERE roleCode = " + roleCode + " ) "                                                                +

                          "ORDER BY fxt_sectionentity.cardinality";
        
        System.out.println ( "retrieveNavigation.SQLQuery: " + SQLQuery );

        JdbcTemplate jdbcTemplate = new JdbcTemplate ( ds );
        
        List<NavSectionRec> l = new ArrayList<> ( );

        try {
            l = jdbcTemplate.query ( SQLQuery,
                                       new RowMapper<NavSectionRec> ( ) {
                                           @Override
                                           public NavSectionRec mapRow ( ResultSet rs, int rowNum ) throws SQLException {
                                               NavSectionRec r = new NavSectionRec ( );

                                               r.setSectionCode       ( rs.getInt     ( "sectionCode"        ) );
                                               r.setSectionClass      ( rs.getString  ( "SECTION_CLASS"      ) );
                                               r.setSectionName       ( rs.getString  ( "SECTION_NAME"       ) );
                                               r.setTabTypeCode       ( rs.getInt     ( "tabTypeCode"        ) );
                                               r.setCardinality       ( rs.getInt     ( "cardinality"        ) );                    
                                               r.setControllerCommand ( rs.getString  ( "CONTROLLER_COMMAND" ) );                    
                                               r.setIsDefault         ( rs.getBoolean ( "isDefault"          ) );                    
                                               r.setActive            ( rs.getBoolean ( "active"             ) );                    

                                               r.setCommands ( NavigationModel.retrieveCommands ( roleCode, r.getSectionCode ( ), ds ) );
                                               
                                               return r;
                                           }
                                       } );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ NavigationModel.retrieveNavigation: " + ex.getMessage ( ) );
        }

        return l;
    }

    /**
     * Retrieves all commands for a given section identified by "sectionCode" from storage.
     * @param sectionCode The unique code that identifies a section in storage
     * @param roleCode The Code of the role for which we are retrieving the commands for a given section
     * @param ds
     * @return a list of CommandRec List<NavCommandRec>
     */
    public static List<NavCommandRec> retrieveCommands ( int roleCode, int sectionCode, DataSource ds ) {

        String SQLQuery = "SELECT fxt_commandentity.commandCode, "                                                          +
                                 "fxt_commandentity.sectionCode, "                                                          +
                                 "IFNULL(fxt_commandentity.commandClass,'') AS COMMAND_CLASS, "                             +
                                 "IFNULL(fxt_commandentity.commandName,'') AS COMMAND_NAME, "                               +
                                 "fxt_commandentity.commandTypeCode, "                                                      +
                                 "fxt_commandentity.cardinality, "                                                          +
                                 "IFNULL(fxt_commandentity.controllerCommand,'') AS CONTROLLER_COMMAND, "                   +
                                 "fxt_commandentity.isDefault, "                                                            +
                                 "fxt_commandentity.active "                                                                +

                          "FROM fxt_commandentity "                                                                         +

                          "WHERE fxt_commandentity.sectioncode = " + sectionCode + " AND "                                  +
                                "fxt_commandentity.active = 1 AND "                                                         +
                                "fxt_commandentity.commandCode IN ( SELECT fxt_commandsxrole.commandCode "                  +
                                                                   "FROM fxt_commandsxrole "                                +
                                                                   "WHERE fxt_commandsxrole.roleCode = " + roleCode + " ) " +

                          "ORDER BY fxt_commandentity.cardinality";

        System.out.println ( "retrieveCommands.SQLQuery: " + SQLQuery );

        JdbcTemplate jdbcTemplate = new JdbcTemplate ( ds );

        List<NavCommandRec> l = new ArrayList<> ( );

        try {
            l = jdbcTemplate.query ( SQLQuery,
                                       new RowMapper<NavCommandRec> ( ) {
                                           @Override
                                           public NavCommandRec mapRow ( ResultSet rs, int rowNum ) throws SQLException {
                                               NavCommandRec r = new NavCommandRec ( );

                                               r.setCommandCode       ( rs.getInt     ( "commandCode"        ) );
                                               r.setSectionCode       ( rs.getInt     ( "sectionCode"        ) );
                                               r.setCommandClass      ( rs.getString  ( "COMMAND_CLASS"      ) );
                                               r.setCommandName       ( rs.getString  ( "COMMAND_NAME"       ) );
                                               r.setCommandTypeCode   ( rs.getInt     ( "commandTypeCode"    ) );
                                               r.setCardinality       ( rs.getInt     ( "cardinality"        ) );                    
                                               r.setControllerCommand ( rs.getString  ( "CONTROLLER_COMMAND" ) );                    
                                               r.setIsDefault         ( rs.getBoolean ( "isDefault"          ) );                    
                                               r.setActive            ( rs.getBoolean ( "active"             ) );                    

                                              return r;
                                           }
                                       } );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ NavigationModel.retrieveCommands: " + ex.getMessage ( ) );
        }

        return l;
    }

    /**
     * Retrieves the navigation of the webapp from storage.
     * @param allCommands Tells the method whether it should return all sections of the navigation or only the active ones.
     * @param ds
     * @return An instance of List<NavSectionRec>
     */
    public static List<NavSectionRec> retrieveNavigation ( boolean allCommands, DataSource ds ) {
        
        String SQLQuery = "SELECT fxt_sectionentity.sectionCode, "                                        +
                                 "IFNULL(fxt_sectionentity.sectionClass,'') AS SECTION_CLASS, "           +
                                 "IFNULL(fxt_sectionentity.sectionName,'') AS SECTION_NAME, "             +
                                 "fxt_sectionentity.tabTypeCode, "                                        +
                                 "IFNULL(fxt_sectionentity.subMenuLabel,'') AS SUB_MENU_LABEL, "          +
                                 "fxt_sectionentity.cardinality, "                                        +
                                 "IFNULL(fxt_sectionentity.controllerCommand,'') AS CONTROLLER_COMMAND, " +
                                 "fxt_sectionentity.isDefault, "                                          +
                                 "fxt_sectionentity.active "                                              +

                          "FROM fxt_sectionentity "                                                       +

                          ( ( allCommands ) ? " " : "WHERE fxt_sectionentity.active = 1 " )               +

                          "ORDER BY fxt_sectionentity.cardinality";
        
//        System.out.println ( "retrieveNavigation.SQLQuery: " + SQLQuery );

        JdbcTemplate jdbcTemplate = new JdbcTemplate ( ds );
        
        List<NavSectionRec> l = new ArrayList<> ( );

        try {
            l = jdbcTemplate.query ( SQLQuery,
                                       new RowMapper<NavSectionRec> ( ) {
                                           @Override
                                           public NavSectionRec mapRow ( ResultSet rs, int rowNum ) throws SQLException {
                                               NavSectionRec r = new NavSectionRec ( );

                                               r.setSectionCode       ( rs.getInt     ( "sectionCode"        ) );
                                               r.setSectionClass      ( rs.getString  ( "SECTION_CLASS"      ) );
                                               r.setSectionName       ( rs.getString  ( "SECTION_NAME"       ) );
                                               r.setTabTypeCode       ( rs.getInt     ( "tabTypeCode"        ) );
                                               r.setCardinality       ( rs.getInt     ( "cardinality"        ) );                    
                                               r.setControllerCommand ( rs.getString  ( "CONTROLLER_COMMAND" ) );                    
                                               r.setIsDefault         ( rs.getBoolean ( "isDefault"          ) );                    
                                               r.setActive            ( rs.getBoolean ( "active"             ) );                    

                                               r.setCommands ( NavigationModel.retrieveCommands ( allCommands, r.getSectionCode ( ), ds ) );
                                               
                                               return r;
                                           }
                                       } );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ NavigationModel.retrieveNavigation: " + ex.getMessage ( ) );
        }

        return l;
    }
    
    /**
     * Retrieves all commands for a given section identified by "sectionCode" from storage.
     * @param allCommands
     * @param sectionCode The unique code that identifies a section in storage
     * @param ds
     * @return a list of CommandRec List<NavCommandRec>
     */
    public static List<NavCommandRec> retrieveCommands ( boolean allCommands, int sectionCode, DataSource ds ) {
        
        String SQLQuery = "SELECT fxt_commandentity.commandCode, "                                        +
                                 "fxt_commandentity.sectionCode, "                                        +
                                 "IFNULL(fxt_commandentity.commandClass,'') AS COMMAND_CLASS, "           +
                                 "IFNULL(fxt_commandentity.commandName,'') AS COMMAND_NAME, "             +
                                 "fxt_commandentity.commandTypeCode, "                                    +
                                 "fxt_commandentity.cardinality, "                                        +
                                 "IFNULL(fxt_commandentity.controllerCommand,'') AS CONTROLLER_COMMAND, " +
                                 "fxt_commandentity.isDefault, "                                          +
                                 "fxt_commandentity.active "                                              +

                          "FROM fxt_commandentity "                                                       +

                          "WHERE fxt_commandentity.sectioncode = " + sectionCode + " "                    +
                          ( ( allCommands ) ? " " : "AND fxt_commandentity.active = 1 " )                 +

                          "ORDER BY fxt_commandentity.cardinality";

//        System.out.println ( "retrieveCommands.SQLQuery: " + SQLQuery );

        JdbcTemplate jdbcTemplate = new JdbcTemplate ( ds );
        
        List<NavCommandRec> l = new ArrayList<> ( );
        
        try {
            l = jdbcTemplate.query ( SQLQuery,
                                       new RowMapper<NavCommandRec> ( ) {
                                           @Override
                                           public NavCommandRec mapRow ( ResultSet rs, int rowNum ) throws SQLException {
                                               NavCommandRec r = new NavCommandRec ( );

                                               r.setCommandCode       ( rs.getInt     ( "commandCode"        ) );
                                               r.setSectionCode       ( rs.getInt     ( "sectionCode"        ) );
                                               r.setCommandClass      ( rs.getString  ( "COMMAND_CLASS"      ) );
                                               r.setCommandName       ( rs.getString  ( "COMMAND_NAME"       ) );
                                               r.setCommandTypeCode   ( rs.getInt     ( "commandTypeCode"    ) );
                                               r.setCardinality       ( rs.getInt     ( "cardinality"        ) );                    
                                               r.setControllerCommand ( rs.getString  ( "CONTROLLER_COMMAND" ) );                    
                                               r.setIsDefault         ( rs.getBoolean ( "isDefault"          ) );                    
                                               r.setActive            ( rs.getBoolean ( "active"             ) );                    

                                              return r;
                                           }
                                       } );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ NavigationModel.retrieveCommands: " + ex.getMessage ( ) );
        }

        return l;
    }

}