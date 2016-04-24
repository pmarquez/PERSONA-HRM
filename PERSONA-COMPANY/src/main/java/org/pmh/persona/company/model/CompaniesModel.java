
package org.pmh.persona.company.model;

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
import org.pmh.persona.company.company.CompanyBaseRec;
import org.pmh.persona.company.company.CompanyRec;
import org.pmh.persona.company.organization.CompanyOrgRec;


/**
 * CompaniesModel.java<br><br>
 * Creation Date 2015-03-23 17:45<br><br>
 * <b>DESCRIPTION:</b><br><br>
 * <p>Manage all the interactions with persistent Company data.</p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br>
 * Version Date: 2015-03-23 17:45<br>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez Herrero
 * @version 1.0 - 2015-03-23 17:45
 */
public class CompaniesModel {
    
    static public List<CompanyBaseRec> retrieveBaseCompanies ( DataSource ds ) {
        
        String SQLQuery = "SELECT per_companyentity.companyCode, "                                                                              +
                                 "IFNULL(per_companyentity.taxId,'') AS TAX_ID, "                                                               +
                                 "IFNULL(per_companyentity.name,'') AS COMPANY_NAME, "                                                          +
                                 "per_companyentity.companyTypeCode, "                                                                          +
                                 "IFNULL(per_companytypeentity.name,'') AS COMPANY_TYPE, "                                                      +
                                 "IFNULL(per_companyentity.companySocialSecurityNumber,'') AS COMPANY_SSN, "                                    +
                                 "IFNULL(per_companyentity.email,'') AS EMAIL, "                                                                +
                                 "IFNULL(per_companyentity.web,'') AS WEB, "                                                                    +
                                 "IFNULL(per_companyentity.phone,'') AS PHONE, "                                                                +
                                 "IFNULL(per_companyentity.fax,'') AS FAX, "                                                                    +
                                 "IFNULL(per_companyentity.creationDate,'') AS CREATION_DATE, "                                                 +
                                 "IFNULL(per_companyentity.active,'') AS ACTIVE "                                                               +

                          "FROM per_companyentity "                                                                                             +

                          "LEFT OUTER JOIN per_companytypeentity ON per_companytypeentity.companyTypeCode = per_companyentity.companyTypeCode " +

                          "ORDER BY per_companyentity.name";
        
        JdbcTemplate jdbcTemplate = new JdbcTemplate ( ds );

        List<CompanyBaseRec> l = new ArrayList<> ( );

        try {
            l = jdbcTemplate.query ( SQLQuery,
                                        new RowMapper<CompanyBaseRec> ( ) {
                                            @Override
                                            public CompanyBaseRec mapRow ( ResultSet rs, int rowNum ) throws SQLException {

                                                CompanyBaseRec r = new CompanyBaseRec ( );

                                                   r.setCompanyCode                 ( rs.getInt     ( "companyCode"      ) );
                                                   r.setTaxId                       ( rs.getString  ( "TAX_ID"           ) );
                                                   r.setName                        ( rs.getString  ( "COMPANY_NAME"     ) );
                                                   r.setCompanySocialSecurityNumber ( rs.getString  ( "COMPANY_SSN"      ) );
                                                   r.setCompanyTypeCode             ( rs.getInt     ( "companyTypeCode"  ) );
                                                   r.setCompanyType                 ( rs.getString  ( "COMPANY_TYPE"     ) );
                                                   r.setEmail                       ( rs.getString  ( "EMAIL"            ) );
                                                   r.setWeb                         ( rs.getString  ( "WEB"              ) );
                                                   r.setPhone                       ( rs.getString  ( "PHONE"            ) );
                                                   r.setFax                         ( rs.getString  ( "FAX"              ) );
                                                   r.setCreationDate                ( rs.getString  ( "CREATION_DATE"    ) );
                                                   r.setActive                      ( rs.getBoolean ( "ACTIVE"           ) );

                                                return r;
                                            }
                                        } );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ CompaniesModel.retrieveBaseCompanies: " + ex.getMessage ( ) );
        }

        return l;
    }

    static public CompanyRec retrieveCompany ( int companyCode, final DataSource ds ) {
        
        String SQLQuery = "SELECT per_companyentity.companyCode, "                                                                              +
                                 "IFNULL(per_companyentity.taxId,'') AS TAX_ID, "                                                               +
                                 "IFNULL(per_companyentity.name,'') AS COMPANY_NAME, "                                                          +
                                 "per_companyentity.companyTypeCode, "                                                                          +
                                 "IFNULL(per_companytypeentity.name,'') AS COMPANY_TYPE, "                                                      +
                                 "IFNULL(per_companyentity.companySocialSecurityNumber,'') AS COMPANY_SSN, "                                    +
                                 "IFNULL(per_companyentity.email,'') AS EMAIL, "                                                                +
                                 "IFNULL(per_companyentity.web,'') AS WEB, "                                                                    +
                                 "IFNULL(per_companyentity.phone,'') AS PHONE, "                                                                +
                                 "IFNULL(per_companyentity.fax,'') AS FAX, "                                                                    +
                                 "IFNULL(per_companyentity.creationDate,'') AS CREATION_DATE, "                                                 +
                                 "IFNULL(per_companyentity.active,'') AS ACTIVE "                                                               +

                          "FROM per_companyentity "                                                                                             +

                          "LEFT OUTER JOIN per_companytypeentity ON per_companytypeentity.companyTypeCode = per_companyentity.companyTypeCode " +

                          "WHERE per_companyentity.companyCode = " + companyCode;
        
        JdbcTemplate jdbcTemplate = new JdbcTemplate ( ds );

        CompanyRec r = new CompanyRec ( );

        try {
            r  =  jdbcTemplate.queryForObject (SQLQuery,
                                                 new RowMapper<CompanyRec> ( ) {
                                                    @Override
                                                    public CompanyRec mapRow ( ResultSet rs, int rowNum ) throws SQLException {

                                                        CompanyRec r = new CompanyRec ( );

                                                            r.setCompanyCode                 ( rs.getInt     ( "companyCode"      ) );
                                                            r.setTaxId                       ( rs.getString  ( "TAX_ID"           ) );
                                                            r.setName                        ( rs.getString  ( "COMPANY_NAME"     ) );
                                                            r.setCompanySocialSecurityNumber ( rs.getString  ( "COMPANY_SSN"      ) );
                                                            r.setCompanyTypeCode             ( rs.getInt     ( "companyTypeCode"  ) );
                                                            r.setCompanyType                 ( rs.getString  ( "COMPANY_TYPE"     ) );
                                                            r.setEmail                       ( rs.getString  ( "EMAIL"            ) );
                                                            r.setWeb                         ( rs.getString  ( "WEB"              ) );
                                                            r.setPhone                       ( rs.getString  ( "PHONE"            ) );
                                                            r.setFax                         ( rs.getString  ( "FAX"              ) );
                                                            r.setCreationDate                ( rs.getString  ( "CREATION_DATE"    ) );
                                                            r.setActive                      ( rs.getBoolean ( "ACTIVE"           ) );
                                                           
                                                        return r;
                                                    }
                                                } );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ CompaniesModel.retrieveCompany: " + ex.getMessage ( ) );
        }

        return r;
    }
    
    static public List<CompanyOrgRec> retrieveCompanyOrganization ( int companyCode, final DataSource ds ) {
      
        String SQLQuery = "SELECT IFNULL(per_companyentity.companyCode,0) AS COMPANY_CODE, "                                             +
                                 "IFNULL(per_companyentity.name,'') AS COMPANY_NAME, "                                                   +
                                 "IFNULL(per_postentity.departmentCode,0) AS DEPARTMENT_CODE, "                                          +
                                 "IFNULL(per_departmententity.departmentName,'') AS DEPARTMENT_NAME, "                                   +
                                 "per_postentity.postCode, "                                                                             +
                                 "IFNULL(per_postentity.postId,'') AS POST_ID, "                                                         +
                                 "IFNULL(per_postentity.postName,'') AS POST_NAME, "                                                     +
                                 "IFNULL(per_postentity.numPostOpenings,0) AS NUM_POST_OPENINGS, "                                       +
                                 "IFNULL(per_postentity.supervisorPostCode,0) AS SUPERVISOR_POST_CODE, "                                 +
                                 "per_postentity.active "                                                                                +

                          "FROM per_postentity "                                                                                         +

                          "LEFT OUTER JOIN per_departmententity ON per_departmententity.departmentCode = per_postentity.departmentCode " +
                          "LEFT OUTER JOIN per_companyentity ON per_companyentity.companyCode = per_departmententity.companyCode "       +

                          "WHERE per_companyentity.companyCode = " + companyCode;

        JdbcTemplate jdbcTemplate = new JdbcTemplate ( ds );

        List<CompanyOrgRec> l = new ArrayList<> ( );

        try {
            l = jdbcTemplate.query ( SQLQuery,
                                        new RowMapper<CompanyOrgRec> ( ) {
                                            @Override
                                            public CompanyOrgRec mapRow ( ResultSet rs, int rowNum ) throws SQLException {

                                                CompanyOrgRec r = new CompanyOrgRec ( );

                                                   r.setCompanyCode                 ( rs.getInt     ( "COMPANY_CODE"          ) );
                                                   r.setCompanyName                 ( rs.getString  ( "COMPANY_NAME"          ) );
                                                   r.setDepartmentCode              ( rs.getInt     ( "DEPARTMENT_CODE"       ) );
                                                   r.setDepartmentName              ( rs.getString  ( "DEPARTMENT_NAME"       ) );
                                                   r.setPostCode                    ( rs.getInt     ( "postCode"              ) );
                                                   r.setPostId                      ( rs.getString  ( "POST_ID"               ) );
                                                   r.setPostName                    ( rs.getString  ( "POST_NAME"             ) );
                                                   r.setNumPostOpenings             ( rs.getInt     ( "NUM_POST_OPENINGS"     ) );
                                                   r.setSupervisorPostCode          ( rs.getInt     ( "SUPERVISOR_POST_CODE"  ) );
                                                   r.setActive                      ( rs.getBoolean ( "ACTIVE"                ) );

                                                return r;
                                            }
                                        } );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ CompaniesModel.retrieveCompanyOrganization: " + ex.getMessage ( ) );
        }

        return l;

    }

}
