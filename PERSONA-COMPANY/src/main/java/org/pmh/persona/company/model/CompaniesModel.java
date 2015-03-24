
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


/**
 * PersonsModel.java<br/><br/>
 * Creation Date 2015-03-23 17:45<br/><br/>
 * <b>DESCRIPTION:</b><br/><br/>
 * <p>Manage all the interactions with persistent Items data.</p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br/>
 * Version Date: 2015-03-23 17:45<br/>
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

}
