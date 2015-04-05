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
import org.pmh.persona.contract.post.ContractPostRec;
import org.pmh.persona.contract.post.PostBaseRec;
import org.pmh.persona.contract.salary.SalaryBaseRec;

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
                                                           
                                                            r.setPosts    ( ContractsModel.retrievePosts    ( contractCode, ds ) );
                                                            r.setSalaries ( ContractsModel.retrieveSalaries ( contractCode, ds ) );

                                                        return r;
                                                    }
                                                } );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ ContractsModel.retrieveContract: " + ex.getMessage ( ) );
        }

        return r;
    }
    
    static private List<ContractPostRec> retrievePosts ( int contractCode, DataSource ds ) {
        
        String SQLQuery = "SELECT per_contractpostentity.contractPostCode, "                                                             +
                                 "IFNULL(per_contractpostentity.contractCode,0) AS CONTRACT_CODE, "                                      +
                                 "IFNULL(per_contractpostentity.creationDate,'') AS CREATION_DATE, "                                     +
                                 "IFNULL(per_contractpostentity.activationDate,'') AS ACTIVATION_DATE, "                                 +
                                 "IFNULL(per_contractpostentity.terminationDate,'') AS TERMINATION_DATE, "                               +
                                 "IFNULL(per_contractpostentity.postCode,0) AS POST_CODE, "                                              +
                                 "per_contractpostentity.active "                                                                        +

                          "FROM per_contractpostentity "                                                                                 +

                          "WHERE per_contractpostentity.contractCode = " + contractCode + " "                                            +

                          "ORDER BY per_contractpostentity.activationDate DESC";
        
        JdbcTemplate jdbcTemplate = new JdbcTemplate ( ds );

        List<ContractPostRec> l = new ArrayList<> ( );

        try {
            l = jdbcTemplate.query ( SQLQuery,
                                        new RowMapper<ContractPostRec> ( ) {
                                            @Override
                                            public ContractPostRec mapRow ( ResultSet rs, int rowNum ) throws SQLException {

                                                ContractPostRec r = new ContractPostRec ( );

                                                   r.setContractPostCode        ( rs.getInt     ( "contractPostCode"       ) );
                                                   r.setContractCode            ( rs.getInt     ( "CONTRACT_CODE"          ) );
                                                   r.setPostCode                ( rs.getInt     ( "POST_CODE"              ) );
                                                   r.setCreationDate            ( rs.getString  ( "CREATION_DATE"          ) );
                                                   r.setActivationDate          ( rs.getString  ( "ACTIVATION_DATE"        ) );
                                                   r.setTerminationDate         ( rs.getString  ( "TERMINATION_DATE"       ) );
                                                   r.setActive                  ( rs.getBoolean ( "active"                 ) );

                                                return r;

                                            }

                                        } );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ ContractsModel.retrievePosts: " + ex.getMessage ( ) );
        }

        return l;
    }

    static private List<SalaryBaseRec> retrieveSalaries ( int contractCode, DataSource ds ) {
        
        String SQLQuery = "SELECT IFNULL(per_contractentity.contractCode,0) AS CONTRACT_CODE, "                    +
                                 "IFNULL(per_contractpostsalaryentity.salaryCode,0) AS SALARY_CODE, "              +
                                 "IFNULL(per_contractpostsalaryentity.contractPostCode,0) AS CONTRACT_POST_CODE, " +
                                 "IFNULL(per_contractpostentity.postCode,0) AS POST_CODE, "                        +
                                 "IFNULL(per_contractpostsalaryentity.baseSalary,0) AS BASE_SALARY, "              +
                                 "IFNULL(per_contractpostsalaryentity.creationDate,'') AS CREATION_DATE, "         +
                                 "IFNULL(per_contractpostsalaryentity.activationDate,'') AS ACTIVATION_DATE, "     +
                                 "IFNULL(per_contractpostsalaryentity.terminationDate,'') AS TERMINATION_DATE, "   +
                                 "per_contractpostsalaryentity.active "                                            +

                          "FROM per_contractpostsalaryentity "                                                     +

                          "LEFT OUTER JOIN per_contractpostentity ON per_contractpostentity.contractPostCode = per_contractpostsalaryentity.contractPostCode " +
                          "LEFT OUTER JOIN per_contractentity ON per_contractentity.contractCode = per_contractpostentity.contractCode "                       +

                          "WHERE per_contractentity.contractCode = " + contractCode + " "                                                                      +

                          "ORDER BY per_contractpostsalaryentity.activationDate DESC";
        
        JdbcTemplate jdbcTemplate = new JdbcTemplate ( ds );

        List<SalaryBaseRec> l = new ArrayList<> ( );

        try {
            l = jdbcTemplate.query    ( SQLQuery,
                                        new RowMapper<SalaryBaseRec> ( ) {
                                            @Override
                                            public SalaryBaseRec mapRow ( ResultSet rs, int rowNum ) throws SQLException {

                                                SalaryBaseRec r = new SalaryBaseRec ( );

                                                   r.setContractCode            ( rs.getInt     ( "CONTRACT_CODE"      ) );
                                                   r.setContractPostCode        ( rs.getInt     ( "CONTRACT_POST_CODE" ) );
                                                   r.setPostCode                ( rs.getInt     ( "POST_CODE"          ) );
                                                   r.setSalaryCode              ( rs.getInt     ( "SALARY_CODE"        ) );
                                                   r.setBaseSalary              ( rs.getDouble  ( "BASE_SALARY"        ) );
                                                   r.setCreationDate            ( rs.getString  ( "CREATION_DATE"      ) );
                                                   r.setActivationDate          ( rs.getString  ( "ACTIVATION_DATE"    ) );
                                                   r.setTerminationDate         ( rs.getString  ( "TERMINATION_DATE"   ) );
                                                   r.setActive                  ( rs.getBoolean ( "active"             ) );

                                                return r;

                                            }

                                        } );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ ContractsModel.retrieveSalaries: " + ex.getMessage ( ) );
        }

        return l;
    }

}
