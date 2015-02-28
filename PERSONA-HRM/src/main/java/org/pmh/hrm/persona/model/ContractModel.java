
package org.pmh.hrm.persona.model;


//   Standard Libraries Imports
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import java.util.List;

//   Third Party Libraries Imports
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

//   FENIX Framework Imports

//   PERSONA HRM Domain Imports
import org.pmh.hrm.persona.contract.ContractBaseRec;

/**
 * ContractModel.java<br/><br/>
 * Creation Date 2015-02-28 11:21<br/><br/>
 * <b>DESCRIPTION:</b><br/><br/>
 * <p>Manage all the interactions with persistent Contract data.</p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br/>
 * Version Date: 2015-02-28 11:21<br/>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2015-02-28 11:21
 */
public class ContractModel {
    
    static public List retrieveBaseContracts ( DataSource ds ) {
        
        String SQLQuery = "SELECT per_contractentity.contractCode, "                       +
                                 "per_contractentity.personCode, "                         +
                                 "IFNULL(per_personentity.firstName,'') AS FIRST_NAME, "   +
                                 "IFNULL(per_personentity.middleName,'') AS MIDDLE_NAME, " + 
                                 "IFNULL(per_personentity.lastName,'') AS LAST_NAME, "     +
//                                 "per_personentity.idTypeCode, "                           +
                                 "per_idtypeentity.idTypeName, "                           +
                                 "per_personentity.idNumber, "                             +
                                 "per_contractentity.postCode, "                           +
                                 "per_postentity.postName, "                               +
                                 "IFNULL(per_personentity.birthDate,'') AS BIRTH_DATE, "   +
                                 "per_contractentity.active "                              +
        
                          "FROM per_contractentity "                                       +

                          "LEFT OUTER JOIN per_personentity ON per_personentity.personCode = per_contractentity.personCode " +
                          "LEFT OUTER JOIN per_postentity ON per_postentity.postCode = per_contractentity.postCode "         +
                          "LEFT OUTER JOIN per_idtypeentity ON per_idtypeentity.idTypeCode = per_personentity.idTypeCode "   +

                          "WHERE per_contractentity.active = 1 "                                                             +

                          "ORDER BY LAST_NAME, FIRST_NAME";
        
        JdbcTemplate jdbcTemplate = new JdbcTemplate ( ds );

        List<ContractBaseRec> cbrl = new ArrayList<> ( );

        try {
            cbrl = jdbcTemplate.query ( SQLQuery,
                                        new RowMapper<ContractBaseRec> ( ) {
                                            @Override
                                            public ContractBaseRec mapRow ( ResultSet rs, int rowNum ) throws SQLException {

                                                ContractBaseRec cbr = new ContractBaseRec ( );

                                                   cbr.setContractCode      ( rs.getInt     ( "contractCode"    ) );

                                                   cbr.setPersonCode        ( rs.getInt     ( "personCode"      ) );
                                                   cbr.setPersonFirstName   ( rs.getString  ( "FIRST_NAME"      ) );
                                                   cbr.setPersonMiddleName  ( rs.getString  ( "MIDDLE_NAME"     ) );
                                                   cbr.setPersonLastName    ( rs.getString  ( "LAST_NAME"       ) );
                                                   
                                                   cbr.setPersonIdType      ( rs.getString  ( "idTypeName"      ) );
                                                   cbr.setPersonIdNumber    ( rs.getString  ( "idNumber"        ) );
                                                   
                                                   cbr.setPostCode          ( rs.getInt     ( "postCode"        ) );
                                                   cbr.setPostName          ( rs.getString  ( "postName"        ) );
 
                                                   cbr.setActive            ( rs.getBoolean ( "active"          ) );

                                                return cbr;
                                            }
                                        } );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ ContractsModel.retrieveBaseContracts: " + ex.getMessage ( ) );
        }

        return cbrl;
    }

    public static ContractBaseRec retrieveBaseContract ( int contractCode, DataSource ds ) {
        
        String SQLQuery = "SELECT per_contractentity.contractCode, "                       +
                                 "per_contractentity.personCode, "                         +
                                 "IFNULL(per_personentity.firstName,'') AS FIRST_NAME, "   +
                                 "IFNULL(per_personentity.middleName,'') AS MIDDLE_NAME, " + 
                                 "IFNULL(per_personentity.lastName,'') AS LAST_NAME, "     +
//                                 "per_personentity.idTypeCode, "                           +
                                 "per_idtypeentity.idTypeName, "                           +
                                 "per_personentity.idNumber, "                             +
                                 "per_contractentity.postCode, "                           +
                                 "per_postentity.postName, "                               +
                                 "IFNULL(per_personentity.birthDate,'') AS BIRTH_DATE, "   +
                                 "per_contractentity.active "                              +
        
                          "FROM per_contractentity "                                       +

                          "LEFT OUTER JOIN per_personentity ON per_personentity.personCode = per_contractentity.personCode " +
                          "LEFT OUTER JOIN per_postentity ON per_postentity.postCode = per_contractentity.postCode "         +
                          "LEFT OUTER JOIN per_idtypeentity ON per_idtypeentity.idTypeCode = per_personentity.idTypeCode "   +

                          "WHERE per_contractentity.active = 1 AND "                                                         +
                                "per_contractentity.contractCode = " + contractCode;
        
        JdbcTemplate jdbcTemplate = new JdbcTemplate ( ds );

        ContractBaseRec cbr = new ContractBaseRec ( );

        try {
            cbr  = jdbcTemplate.queryForObject ( SQLQuery,
                                                 new RowMapper<ContractBaseRec> ( ) {
                                                    @Override
                                                    public ContractBaseRec mapRow ( ResultSet rs, int rowNum ) throws SQLException {

                                                        ContractBaseRec cbr = new ContractBaseRec ( );

                                                            cbr.setContractCode      ( rs.getInt     ( "contractCode"    ) );

                                                            cbr.setPersonCode        ( rs.getInt     ( "personCode"      ) );
                                                            cbr.setPersonFirstName   ( rs.getString  ( "FIRST_NAME"      ) );
                                                            cbr.setPersonMiddleName  ( rs.getString  ( "MIDDLE_NAME"     ) );
                                                            cbr.setPersonLastName    ( rs.getString  ( "LAST_NAME"       ) );

                                                            cbr.setPersonIdType      ( rs.getString  ( "idTypeName"      ) );
                                                            cbr.setPersonIdNumber    ( rs.getString  ( "idNumber"        ) );

                                                            cbr.setPostCode          ( rs.getInt     ( "postCode"        ) );
                                                            cbr.setPostName          ( rs.getString  ( "postName"        ) );

                                                            cbr.setActive            ( rs.getBoolean ( "active"          ) );

                                                        return cbr;
                                                    }
                                                } );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ ContractsModel.retrieveBaseContract: " + ex.getMessage ( ) );
        }

        return cbr;
    }

}
