
package org.pmh.persona.contract.controller;

//   Standard Libraries Imports
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

//   Third Party Libraries Imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//   FENIX Framework Imports
import org.pmh.util.ListWrapper;

//   Application Domain Imports
import org.pmh.persona.contract.contract.ContractBaseRec;
import org.pmh.persona.contract.contract.ContractRec;
import org.pmh.persona.contract.model.ContractsModel;
import org.springframework.web.client.RestTemplate;
import org.pmh.persona.contract.external.CompanyRec;
import org.pmh.persona.contract.external.PersonRec;

/**
 * ContractsRestController.java<br/><br/>
 * Creation Date 2015-03-25 19:04<br/><br/>
 * <b>DESCRIPTION:</b><br/><br/>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br/>
 * Version Date: 2015-03-25 19:04<br/>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2015-03-25 19:04
 */
@RestController
public class ContractsRestController {
    
    String personBaseURI  = "http://localhost:8084/PERSONA-PERSON/personsAPI/1.0/persons";
    String companyBaseURI = "http://localhost:8084/PERSONA-COMPANY/companiesAPI/1.0/companies";
    
    @Autowired
    private DataSource ds;
    
    @RequestMapping ( value = "/contractsAPI/1.0/contracts", method = RequestMethod.GET )
    public ListWrapper baseContracts ( HttpServletRequest request ) {

    //    HttpSession session = request.getSession ( );
        
        List<ContractBaseRec> l = ContractsModel.retrieveBaseContracts ( ds );
        
        ListWrapper lw = new ListWrapper ( );
        lw.setAaData ( l );

        return lw;
    }
    
    @RequestMapping ( value = "/contractsAPI/1.0/contracts/{contractCode}", method = RequestMethod.GET )
    public ContractRec retrieveContract ( @PathVariable int contractCode, HttpServletRequest request ) {

    //    HttpSession session = request.getSession ( );

        ContractRec r = ContractsModel.retrieveContract ( contractCode, ds );
        
        RestTemplate restTemplate = new RestTemplate ( );
        
        String       personUri    = personBaseURI  + "/" + r.getPersonCode  ( );
        String       companyUri   = companyBaseURI + "/" + r.getCompanyCode ( );

//        System.out.println ( restTemplate.getForEntity ( personUri,  String.class ) );
//        System.out.println ( restTemplate.getForEntity ( companyUri, String.class ) );
        
        PersonRec  person  = new PersonRec  ( );
        CompanyRec company = new CompanyRec ( );
        
        try {
            company = restTemplate.getForObject ( companyUri, CompanyRec.class );
            person  = restTemplate.getForObject ( personUri,  PersonRec.class  );

        } catch ( org.springframework.web.client.RestClientException ex ) {
            System.err.print ( ex.getMessage ( ) );
        }
        
        r.setPerson  ( person  );
        r.setCompany ( company );

        return r;
    }

}
