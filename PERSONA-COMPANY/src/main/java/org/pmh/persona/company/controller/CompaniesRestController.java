
package org.pmh.persona.company.controller;


//   Standard Libraries Imports
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.util.List;

//   Third Party Libraries Imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

//   FENIX Framework Imports
import com.fxt.process.ResponseRec;
import javax.servlet.http.HttpSession;

//   Application Domain Imports
import org.pmh.persona.company.company.CompanyBaseRec;
import org.pmh.persona.company.company.CompanyRec;
import org.pmh.persona.company.model.CompaniesModel;
import org.pmh.persona.company.organization.CompanyOrgRec;

/**
 * CompaniesRestController.java<br><br>
 * Creation Date 2015-03-24 11:44<br><br>
 * <b>DESCRIPTION:</b><br><br>
 * <p>Rest Controller for all things COMPANY</p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.1<br>
 * Version Date: 2016-04-21 22:20<br>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Updated RestController to use a <code>ResponseRec<></code> for handling the return values.</p></td>
 *<td width="80%"><p>Created method <code>companyExists</code>.</p></td>
 *</tr>
 *<tr>
 *<td width="20%">Version 1.0<br>
 * Version Date: 2015-03-24 11:44<br>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.1 - 2016-04-21 22:20
 */
//TODO Token Authorization
//TODO Handle User Profiles
//TODO MultiTenancy - Create Account-Company Hierarchy.
@RestController
public class CompaniesRestController {

//   Controller Constants
    private static final int EMPTY_COMPANY_LIST      = 0;
    private static final int EMPTY_COMPANY_REC       = 0;
    private static final int EMPTY_ORG_REC_NODE_LIST = 0;
    
//   Response Status
    private static final String OPERATION_SUCCESSFUL                            = "COMPANIES_001";   //   The requested operation was succesfully completed.
    private static final String NO_COMPANY_FOUND                                = "COMPANIES_002";   //   Could not find requested company.
    private static final String NO_COMPANIES_FOUND                              = "COMPANIES_003";   //   The requested list of companies is empty.
    private static final String INVALID_USER_PRIVILLEGES                        = "COMPANIES_004";   //   User does not have the required privilleges for this call.
    private static final String INVALID_AUTHORIZATION_TOKEN                     = "COMPANIES_005";   //   Authorization token received is not valid.
    private static final String INTERNAL_ERROR_ENCOUNTERED                      = "COMPANIES_006";   //   An internal error was encountered.
    private static final String REQUESTED_COMPANY_EXISTS                        = "COMPANIES_007";   //   The requested company exists.
    private static final String REQUESTED_COMPANY_DOES_NOT_EXIST                = "COMPANIES_008";   //   The requested company does not exist.
    private static final String NO_ORG_REC_NODES_FOUND                          = "COMPANIES_009";   //   The requested list of organizational nodes is empty.

    @Autowired
    private DataSource ds;
    
    /**
     * Returns a list of all the companies available to the current role. 
     * @param request
     * @return ResponseRec<List<CompanyBaseRec>>
     */
    @RequestMapping ( value = "/companiesAPI/1.0/companies/companies", method = RequestMethod.GET )
    public ResponseRec<List<CompanyBaseRec>> baseCompanies ( HttpServletRequest request ) {

        HttpSession session = request.getSession ( );
        
        ResponseRec<List<CompanyBaseRec>> rr = new ResponseRec<> ( );
        
        List<CompanyBaseRec> l = CompaniesModel.retrieveBaseCompanies ( ds );

        if ( l.size ( ) > CompaniesRestController.EMPTY_COMPANY_LIST ) {
            rr.setPayload       ( l );
            rr.setResultCode    ( CompaniesRestController.OPERATION_SUCCESSFUL );
            rr.setResultMessage ( "The requested operation was succesfully completed." );   //TODO use the database based ISSUE_MESSAGE_ENTITY scheme. 
            
        } else {
            rr.setResultCode    ( CompaniesRestController.NO_COMPANIES_FOUND );
            rr.setResultMessage ( "The requested list of companies is empty." );            //TODO use the database based ISSUE_MESSAGE_ENTITY scheme. 

        }
        
        return rr;
    }
    
    /**
     * Returns all the relevant information for a company identified by <code>companyCode</code>
     * @param companyCode the ID that iniquely identifies a given company.
     * @param request
     * @return ResponseRec<CompanyRec>
     */
    @RequestMapping ( value = "/companiesAPI/1.0/companies/companies/{companyCode}", method = RequestMethod.GET )
    public ResponseRec<CompanyRec> retrieveCompany ( @PathVariable int companyCode, HttpServletRequest request ) {

    //    HttpSession session = request.getSession ( );
        
        ResponseRec<CompanyRec> rr = new ResponseRec<> ( );

        CompanyRec r = CompaniesModel.retrieveCompany ( companyCode, ds );

        if ( r.getCompanyCode ( ) > CompaniesRestController.EMPTY_COMPANY_REC ) {
            rr.setPayload ( r );
            rr.setResultCode    ( CompaniesRestController.OPERATION_SUCCESSFUL );
            rr.setResultMessage ( "The requested operation was succesfully completed." );   //TODO use the database based ISSUE_MESSAGE_ENTITY scheme. 

        } else {
            rr.setResultCode    ( CompaniesRestController.NO_COMPANY_FOUND );
            rr.setResultMessage ( "Could not find requested company." );                    //TODO use the database based ISSUE_MESSAGE_ENTITY scheme. 
            
        }

        return rr;
    }
    
    /**
     * Checks whether a company exists in storage.
     * @param companyCode
     * @param request
     * @return ResponseRec<Boolean>
     * @since 1.1
     */
    @RequestMapping ( value = "/companiesAPI/1.0/companies/exists/{companyCode}", method = RequestMethod.GET )
    public ResponseRec<Boolean> companyExists ( @PathVariable int companyCode, HttpServletRequest request ) {

    //    HttpSession session = request.getSession ( );
        
        ResponseRec<Boolean> rr = new ResponseRec<> ( );

        CompanyRec r = CompaniesModel.retrieveCompany ( companyCode, ds );

        if ( r.getCompanyCode ( ) > CompaniesRestController.EMPTY_COMPANY_REC ) {
            rr.setPayload ( Boolean.TRUE );
            rr.setResultCode    ( CompaniesRestController.REQUESTED_COMPANY_EXISTS );
            rr.setResultMessage ( "The requested company exists." );                        //TODO use the database based ISSUE_MESSAGE_ENTITY scheme. 

        } else {
            rr.setPayload ( Boolean.FALSE );
            rr.setResultCode    ( CompaniesRestController.REQUESTED_COMPANY_DOES_NOT_EXIST );
            rr.setResultMessage ( "The requested company does not exist." );                //TODO use the database based ISSUE_MESSAGE_ENTITY scheme. 

        }
        
        return rr;
    }
        
    /**
     * Retrieves a list of Organizational Rec Nodes for the requested company.
     * @param companyCode
     * @param request
     * @return 
     */
    @RequestMapping ( value = "/companiesAPI/1.0/companies/organization/{companyCode}", method = RequestMethod.GET )
    public ResponseRec<List<CompanyOrgRec>> retrieveCompanyOrganization ( @PathVariable int companyCode, HttpServletRequest request ) {

        HttpSession session = request.getSession ( );
        
        ResponseRec<List<CompanyOrgRec>> rr = new ResponseRec<> ( );

        List<CompanyOrgRec> l = CompaniesModel.retrieveCompanyOrganization ( companyCode, ds );
        
        if ( l.size ( ) > CompaniesRestController.EMPTY_ORG_REC_NODE_LIST ) {
            rr.setPayload       ( l );
            rr.setResultCode    ( CompaniesRestController.OPERATION_SUCCESSFUL );
            rr.setResultMessage ( "The requested operation was succesfully completed." );     //TODO use the database based ISSUE_MESSAGE_ENTITY scheme. 
            
        } else {
            rr.setResultCode    ( CompaniesRestController.NO_ORG_REC_NODES_FOUND );
            rr.setResultMessage ( "The requested list of organizational nodes is empty." );   //TODO use the database based ISSUE_MESSAGE_ENTITY scheme. 

        }

        return rr;
    }
        
}
