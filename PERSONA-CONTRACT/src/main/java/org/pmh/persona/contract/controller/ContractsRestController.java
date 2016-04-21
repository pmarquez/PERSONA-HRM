
package org.pmh.persona.contract.controller;

//   Standard Libraries Imports
import java.util.ArrayList;
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

//   FENIX Framework Imports
import org.pmh.util.ListWrapper;

//   Application Domain Imports
import org.pmh.persona.contract.contract.ContractBaseRec;
import org.pmh.persona.contract.contract.ContractRec;
import org.pmh.persona.contract.external.CompanyOrganizationalRec;
import org.pmh.persona.contract.model.ContractsModel;
import org.pmh.persona.contract.external.CompanyRec;
import org.pmh.persona.contract.external.PersonRec;
import org.pmh.persona.contract.post.ContractPostRec;
import org.pmh.persona.contract.salary.SalaryBaseRec;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

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
    
    String personBaseURI  = "http://localhost:8084/PERSONA-PERSON/personsAPI/1.0/";
    String companyBaseURI = "http://localhost:8084/PERSONA-COMPANY/companiesAPI/1.0/";
    
    @Autowired
    private DataSource ds;
    
    @RequestMapping ( value = "/contractsAPI/1.0/contracts/contracts", method = RequestMethod.GET )
    public ListWrapper baseContracts ( HttpServletRequest request ) {

    //    HttpSession session = request.getSession ( );
        
        List<ContractBaseRec> l = ContractsModel.retrieveBaseContracts ( ds );
        
        ListWrapper lw = new ListWrapper ( );
        lw.setAaData ( l );

        return lw;
    }
    
    @RequestMapping ( value = "/contractsAPI/1.0/contracts/contracts/{contractCode}", method = RequestMethod.GET )
    public ContractRec retrieveContract ( @PathVariable int contractCode, HttpServletRequest request ) {

    //    HttpSession session = request.getSession ( );

        ContractRec r = ContractsModel.retrieveContract ( contractCode, ds );
        
        fillPostsInfo    ( r, request );
        fillSalariesInfo ( r, request );
        
        RestTemplate restTemplate = new RestTemplate ( );
        
        String       personUri    = personBaseURI  + "persons/"   + r.getPersonCode  ( );
        String       companyUri   = companyBaseURI + "companies/" + r.getCompanyCode ( );

        PersonRec  person  = new PersonRec  ( );
        CompanyRec company = new CompanyRec ( );
        
        try {
            company = restTemplate.getForObject ( companyUri, CompanyRec.class );
            person  = restTemplate.getForObject ( personUri,  PersonRec.class  );

        } catch ( RestClientException ex ) {
            System.err.print ( ex.getMessage ( ) );
        
        }

        r.setPerson  ( person  );
        r.setCompany ( company );

        return r;
    }

    private void fillPostsInfo ( ContractRec cr, HttpServletRequest request ) {
 
        retrieveOrganization ( cr.getCompanyCode ( ), request );   //TODO  Jack Sparrow [JACK_SPARROW_001] - Do something about this ASAP!!!
       
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

    private void fillSalariesInfo ( ContractRec cr, HttpServletRequest request ) {
 
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
     * @deprecated Use a RestTemplate to get this data from COMPANY
     */
    @RequestMapping ( value = "/contractsAPI/1.0/organization/{companyCode}", method = RequestMethod.GET )
    public void retrieveOrganization ( @PathVariable int companyCode, HttpServletRequest request ) {

        HttpSession session = request.getSession ( );

        RestTemplate restTemplate = new RestTemplate ( );
        
        String                         orgUri = companyBaseURI + "organization/" + companyCode;
        List<CompanyOrganizationalRec> org = new ArrayList<> ( );
        
        try {
            ResponseEntity<CompanyOrganizationalRec [ ]> orgArray = restTemplate.getForEntity ( orgUri, CompanyOrganizationalRec [ ].class );

            for ( int i = 0; i < orgArray.getBody ( ).length; i++ ) {
                org.add ( ( orgArray.getBody ( ) ) [ i ] );
                System.out.println ( org.get ( i ).getCompanyName ( ) + " - " + org.get ( i ).getDepartmentName ( ) + " - " + org.get ( i ).getPostName ( ) );
            }

        } catch ( org.springframework.web.client.RestClientException ex ) {
            System.err.print ( ex.getMessage ( ) );
        }

        session.setAttribute ( "companyOrganization", org );
        
    }

}
