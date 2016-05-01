
package org.pmh.persona.contract.controller;

//   Standard Libraries Imports
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

//   Third Party Libraries Imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

//   FENIX Framework Imports
import com.fxt.process.ResponseRec;

//   Application Domain Imports
import org.pmh.persona.contract.contract.ContractBaseRec;
import org.pmh.persona.contract.contract.ContractRec;
import org.pmh.persona.contract.external.CompanyOrgResponseRec;
import org.pmh.persona.contract.external.CompanyOrganizationalRec;
import org.pmh.persona.contract.external.CompanyResponseRec;
import org.pmh.persona.contract.external.PersonResponseRec;
import org.pmh.persona.contract.model.ContractsModel;
import org.pmh.persona.contract.post.ContractPostRec;
import org.pmh.persona.contract.salary.SalaryBaseRec;

/**
 * ContractsRestController.java<br><br>
 * Creation Date 2015-03-25 19:04<br><br>
 * <b>DESCRIPTION:</b><br><br>
 * <p>Rest Controller for all things CONTRACT</p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.1<br>
 * Version Date: 2016-04-21 22:51<br>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Updated RestController to use a <code>ResponseRec<></code> for handling the return values.</p></td>
 *</tr>
 *<tr>
 *<td width="20%">Version 1.0<br>
 * Version Date: 2015-03-25 19:04<br>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.1 - 2016-04-21 22:51
 */
//TODO Token Authorization
//TODO Handle User Profiles
//TODO MultiTenancy - Create Account-Contracts Hierarchy.
@RestController
public class ContractsRestController {

//   Controller Constants
    private static final int EMPTY_CONTRACT_LIST      = 0;
    private static final int EMPTY_CONTRACT_REC       = 0;
    
//   Response Status
    private static final String OPERATION_SUCCESSFUL                            = "CONTRACTS_001";   //   The requested operation was succesfully completed.
    private static final String NO_CONTRACT_FOUND                               = "CONTRACTS_002";   //   Could not find requested contract.
    private static final String NO_CONTRACTS_FOUND                              = "CONTRACTS_003";   //   The requested list of contracts is empty.
    private static final String INVALID_USER_PRIVILLEGES                        = "CONTRACTS_004";   //   User does not have the required privilleges for this call.
    private static final String INVALID_AUTHORIZATION_TOKEN                     = "CONTRACTS_005";   //   Authorization token received is not valid.
    private static final String INTERNAL_ERROR_ENCOUNTERED                      = "CONTRACTS_006";   //   An internal error was encountered.
    private static final String REQUESTED_CONTRACT_EXISTS                       = "CONTRACTS_007";   //   The requested contract exists.
    private static final String REQUESTED_CONTRACT_DOES_NOT_EXIST               = "CONTRACTS_008";   //   The requested contract does not exist.
    
    
    //TODO - JACK SPARROW WAS HERE - Get rid of this ASAP - BEGIN
    String serverBaseURI       = "http://localhost:8084";
    String personBaseURI       = "/PERSONA-PERSON/personsAPI/1.0/";
    String personAPIMethod     = "persons/persons/";
    String companyBaseURI      = "/PERSONA-COMPANY/companiesAPI/1.0/";
    String companyAPIMethod    = "companies/companies/";
    String companyOrgAPIMethod = "companies/organization/";
    //TODO - JACK SPARROW WAS HERE - Get rid of this ASAP - END
    
    @Autowired
    private DataSource ds;
    
    /**
     * 
     * @param request
     * @return 
     */
    @RequestMapping ( value = "/contractsAPI/1.0/contracts/contracts", method = RequestMethod.GET )
    public ResponseRec<List<ContractBaseRec>> contractList ( HttpServletRequest request ) {

        HttpSession session = request.getSession ( );
        
        ResponseRec<List<ContractBaseRec>> rr = new ResponseRec<> ( );

        List<ContractBaseRec> l = ContractsModel.retrieveBaseContracts ( ds );
        
        if ( l.size ( ) > ContractsRestController.EMPTY_CONTRACT_LIST ) {
            rr.setPayload       ( l );
            rr.setResultCode    ( ContractsRestController.OPERATION_SUCCESSFUL );
            rr.setResultMessage ( "The requested operation was succesfully completed." );   //TODO use the database based ISSUE_MESSAGE_ENTITY scheme. 
            
        } else {
            rr.setResultCode    ( ContractsRestController.NO_CONTRACTS_FOUND );
            rr.setResultMessage ( "The requested list of contracts is empty." );            //TODO use the database based ISSUE_MESSAGE_ENTITY scheme. 

        }

        return rr;
    }
    
    /**
     * 
     * @param contractCode
     * @param request
     * @return 
     */
    //TODO - Work it!!!, finish the job!!!
    @RequestMapping ( value = "/contractsAPI/1.0/contracts/contracts/{contractCode}", method = RequestMethod.GET )
    public ResponseRec<ContractRec> retrieveContract ( @PathVariable int contractCode, HttpServletRequest request ) {

        HttpSession session = request.getSession ( );

        ResponseRec<ContractRec> rr = new ResponseRec<> ( );

//   INTERNAL STUFF - BEGIN
        
        ContractRec r = ContractsModel.retrieveContract ( contractCode, ds );

        //   Retrieve this contract's post history
        retrievePostsInfo    ( r, request );

        //   Retrieve this contract's salary history
//        retrieveSalariesInfo ( r, request );
                
//   INTERNAL STUFF - END

//   EXTERNAL STUFF - BEGIN
        
                        RestTemplate restTemplate = new RestTemplate ( );
                        
                        PersonResponseRec  person  = new PersonResponseRec  ( );
                        CompanyResponseRec company = new CompanyResponseRec ( );
                
                        String personUri  = serverBaseURI + personBaseURI  + personAPIMethod  + r.getPersonCode  ( );
                        String companyUri = serverBaseURI + companyBaseURI + companyAPIMethod + r.getCompanyCode ( );
                        
                        try {
                            person  = restTemplate.getForObject ( personUri,  PersonResponseRec.class  );
                            company = restTemplate.getForObject ( companyUri, CompanyResponseRec.class );
                
                        } catch ( RestClientException ex ) {
                            System.err.print ( ex.getMessage ( ) );
                        
                        }
                
                        r.setPerson  ( person.getPayload  ( ) );
                        r.setCompany ( company.getPayload ( ) );

//   EXTERNAL STUFF - END

        if ( r.getContractCode ( ) > ContractsRestController.EMPTY_CONTRACT_REC ) {
            rr.setPayload       ( r );
            rr.setResultCode    ( ContractsRestController.OPERATION_SUCCESSFUL );
            rr.setResultMessage ( "The requested operation was succesfully completed." );   //TODO use the database based ISSUE_MESSAGE_ENTITY scheme. 
            
        } else {
            rr.setResultCode    ( ContractsRestController.NO_CONTRACT_FOUND );
            rr.setResultMessage ( "The requested contract could not be found." );           //TODO use the database based ISSUE_MESSAGE_ENTITY scheme. 

        }
        
        return rr;
    }

    /**
     * Retrieves post history for a given contract
     * @param cr
     * @param request
     */
    private void retrievePostsInfo ( ContractRec cr, HttpServletRequest request ) {
 
        retrieveOrganization ( cr.getCompanyCode ( ), request );
       
        HttpSession session = request.getSession ( );
        
        List<ContractPostRec> lpbr = cr.getPosts ( );
        List<CompanyOrganizationalRec> lorg = ( List<CompanyOrganizationalRec> ) session.getAttribute ( "companyOrganization" );
 
        for ( ContractPostRec cpr : lpbr ) {
            for ( CompanyOrganizationalRec cor : lorg ) {
                if ( cor.getPostCode ( ) == cpr.getPostCode ( ) ) {
                    cpr.setPostName           ( cor.getPostName           ( ) );
                    cpr.setDepartmentCode     ( cor.getDepartmentCode     ( ) );
                    cpr.setDepartmentName     ( cor.getDepartmentName     ( ) );
                    cpr.setSupervisorPostCode ( cor.getSupervisorPostCode ( ) );

                    for ( CompanyOrganizationalRec cor2 : lorg ) {
                        if ( cor2.getPostCode ( ) == cpr.getSupervisorPostCode ( ) ) {
                            cpr.setSupervisorPostName ( cor2.getPostName ( ) );
                        }
                    }
                }
            }
        }
    }

    /**
     * 
     * @param cr
     * @param request 
     */
    private void retrieveSalariesInfo ( ContractRec cr, HttpServletRequest request ) {
 
        HttpSession session = request.getSession ( );
        
        List<SalaryBaseRec> lsbr = cr.getSalaries ( );
        List<CompanyOrganizationalRec> lorg = ( List<CompanyOrganizationalRec> ) session.getAttribute ( "companyOrganization" );
 
        for ( SalaryBaseRec sbr : lsbr ) {
            for ( CompanyOrganizationalRec cor : lorg ) {
                if ( cor.getPostCode ( ) == sbr.getPostCode ( ) ) {
                    sbr.setPostName ( cor.getPostName ( ) );
                }
            }
        }
    }
    
    /**
     * Retrieves a company's organizational strutures (Departments and Posts).<br>
     * We will be using this list as a cache of organizational information and will be placing in in the session.
     * @param companyCode
     * @param request
     * @return 
     * @deprecated Use a RestTemplate to get this data from COMPANY
     */
    @RequestMapping ( value = "/contractsAPI/1.0/companies/organization/{companyCode}", method = RequestMethod.GET )
    public CompanyOrgResponseRec retrieveOrganization ( @PathVariable int companyCode, HttpServletRequest request ) {

        HttpSession session = request.getSession ( );

        RestTemplate restTemplate = new RestTemplate ( );
        
        String orgUri = serverBaseURI + companyBaseURI + companyOrgAPIMethod + companyCode;
        System.out.println ( "orgURI: " + orgUri );
        CompanyOrgResponseRec org = new CompanyOrgResponseRec ( );

        try {
            org = restTemplate.getForObject ( orgUri, CompanyOrgResponseRec.class );
            System.out.println ( "OrgArray: " + org );
//            ResponseEntity<CompanyOrganizationalRec [ ]> orgArray = restTemplate.getForEntity ( orgUri, CompanyOrganizationalRec [ ].class );

            for ( int i = 0; i < org.getPayload ( ).size ( ); i++ ) {
//                org.add ( ( orgArray.getBody ( ) ) [ i ] );
                System.out.println ( org.getPayload ( ).get ( i ).getCompanyName ( ) + " - " + org.getPayload ( ).get ( i ).getDepartmentName ( ) + " - " + org.getPayload ( ).get ( i ).getPostName ( ) );
            }

        } catch ( org.springframework.web.client.RestClientException ex ) {
            System.err.print ( ex.getMessage ( ) );
        }

        session.setAttribute ( "companyOrganization", org );
        
        return org;
        
    }

}
