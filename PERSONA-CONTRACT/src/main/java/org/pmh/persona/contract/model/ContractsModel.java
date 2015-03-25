package org.pmh.persona.contract.model;

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

//   Application Domain Imports
import org.pmh.persona.contract.contract.ContractBaseRec;
import org.pmh.persona.contract.contract.ContractRec;

/**
 * ContractsModel.java<br/><br/>
 * Creation Date 2015-03-25 19:18<br/><br/>
 * <b>DESCRIPTION:</b><br/><br/>
 * <p>Manage all the interactions with persistent Items data.</p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br/>
 * Version Date: 2015-03-25 19:18<br/>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez Herrero
 * @version 1.0 - 2015-03-25 19:18
 */
public class ContractsModel {
    
    static public List<ContractBaseRec> retrieveBaseContracts ( DataSource ds ) {
        
        String SQLQuery = "SELECT per_contractentity.contractCode, "                                     +
                                 "IFNULL(per_contractentity.contractTypeCode,0) AS CONTRACT_TYPE_CODE, " +
                                 "IFNULL(per_contracttypeentity.contractType,'') AS CONTRACT_TYPE, "     +
                                 "IFNULL(per_contractentity.personCode,0) AS PERSON_CODE, "              +
                                 "IFNULL(per_contractentity.companyCode,0) AS COMPANY_CODE, "            +
                                 "IFNULL(per_contractentity.creationDate,'') AS CREATION_DATE, "         +
                                 "IFNULL(per_contractentity.activationDate,'') AS ACTIVATION_DATE, "     +
                                 "IFNULL(per_contractentity.terminationDate,'') AS TERMINATION_DATE, "   +
                                 "per_contractentity.active "                                            +

                          "FROM per_contractentity "                                                     +

                          "LEFT OUTER JOIN per_contracttypeentity ON per_contracttypeentity.contractTypeCode = per_contractentity.contractTypeCode";
        
        JdbcTemplate jdbcTemplate = new JdbcTemplate ( ds );

        List<ContractBaseRec> l = new ArrayList<> ( );

        try {
            l = jdbcTemplate.query ( SQLQuery,
                                        new RowMapper<ContractBaseRec> ( ) {
                                            @Override
                                            public ContractBaseRec mapRow ( ResultSet rs, int rowNum ) throws SQLException {

                                                ContractBaseRec r = new ContractBaseRec ( );

                                                   r.setContractCode            ( rs.getInt     ( "contractCode"           ) );
                                                   r.setContractTypeCode        ( rs.getInt     ( "CONTRACT_TYPE_CODE"     ) );
                                                   r.setContractType            ( rs.getString  ( "CONTRACT_TYPE"          ) );
                                                   r.setPersonCode              ( rs.getInt     ( "PERSON_CODE"            ) );
                                                   r.setCompanyCode             ( rs.getInt     ( "COMPANY_CODE"           ) );
                                                   r.setCreationDate            ( rs.getString  ( "CREATION_DATE"          ) );
                                                   r.setActivationDate          ( rs.getString  ( "ACTIVATION_DATE"        ) );
                                                   r.setTerminationDate         ( rs.getString  ( "TERMINATION_DATE"       ) );
                                                   r.setActive                  ( rs.getBoolean ( "active"                 ) );

                                                return r;
                                            }
                                        } );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ ContractsModel.retrieveBaseContracts: " + ex.getMessage ( ) );
        }

        return l;
    }

    static public ContractRec retrieveContract ( int contractCode, final DataSource ds ) {
        
        String SQLQuery = "SELECT per_contractentity.contractCode, "                                     +
                                 "IFNULL(per_contractentity.contractTypeCode,0) AS CONTRACT_TYPE_CODE, " +
                                 "IFNULL(per_contracttypeentity.contractType,'') AS CONTRACT_TYPE, "     +
                                 "IFNULL(per_contractentity.personCode,0) AS PERSON_CODE, "              +
                                 "IFNULL(per_contractentity.companyCode,0) AS COMPANY_CODE, "            +
                                 "IFNULL(per_contractentity.creationDate,'') AS CREATION_DATE, "         +
                                 "IFNULL(per_contractentity.activationDate,'') AS ACTIVATION_DATE, "     +
                                 "IFNULL(per_contractentity.terminationDate,'') AS TERMINATION_DATE, "   +
                                 "per_contractentity.active "                                            +

                          "FROM per_contractentity "                                                     +

                          "LEFT OUTER JOIN per_contracttypeentity ON per_contracttypeentity.contractTypeCode = per_contractentity.contractTypeCode " + 

                          "WHERE per_contractentity.contractCode = " + contractCode;
        
        JdbcTemplate jdbcTemplate = new JdbcTemplate ( ds );

        ContractRec r = new ContractRec ( );

        try {
            r  =  jdbcTemplate.queryForObject (SQLQuery,
                                                 new RowMapper<ContractRec> ( ) {
                                                    @Override
                                                    public ContractRec mapRow ( ResultSet rs, int rowNum ) throws SQLException {

                                                        ContractRec r = new ContractRec ( );

                                                            r.setContractCode            ( rs.getInt     ( "contractCode"           ) );
                                                            r.setContractTypeCode        ( rs.getInt     ( "CONTRACT_TYPE_CODE"     ) );
                                                            r.setContractType            ( rs.getString  ( "CONTRACT_TYPE"          ) );
                                                            r.setPersonCode              ( rs.getInt     ( "PERSON_CODE"            ) );
                                                            r.setCompanyCode             ( rs.getInt     ( "COMPANY_CODE"           ) );
                                                            r.setCreationDate            ( rs.getString  ( "CREATION_DATE"          ) );
                                                            r.setActivationDate          ( rs.getString  ( "ACTIVATION_DATE"        ) );
                                                            r.setTerminationDate         ( rs.getString  ( "TERMINATION_DATE"       ) );
                                                            r.setActive                  ( rs.getBoolean ( "active"                 ) );
                                                           
                                                        return r;
                                                    }
                                                } );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ ContractsModel.retrieveContract: " + ex.getMessage ( ) );
        }

        return r;
    }

}
