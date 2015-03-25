
package org.pmh.persona.company.controller;


//   Standard Libraries Imports
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.util.List;
import org.pmh.persona.company.company.CompanyBaseRec;
import org.pmh.persona.company.company.CompanyRec;
import org.pmh.persona.company.model.CompaniesModel;

//   Third Party Libraries Imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.client.RestTemplate;

//   FENIX Framework Imports
import org.pmh.util.ListWrapper;

//   Application Domain Imports


/**
 * CompaniesRestController.java<br/><br/>
 * Creation Date 2015-03-24 11:44<br/><br/>
 * <b>DESCRIPTION:</b><br/><br/>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br/>
 * Version Date: 2015-03-24 11:44<br/>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2015-03-24 11:44
 */
@RestController
public class CompaniesRestController {

    @Autowired
    private DataSource ds;
    
    @RequestMapping ( value = "/companiesAPI/1.0/companies", method = RequestMethod.GET )
    public ListWrapper baseItems ( HttpServletRequest request ) {

    //    HttpSession session = request.getSession ( );
        
        List<CompanyBaseRec> l = CompaniesModel.retrieveBaseCompanies ( ds );
        
        ListWrapper lw = new ListWrapper ( );
        lw.setAaData ( l );

        return lw;
    }
    
    @RequestMapping ( value = "/companiesAPI/1.0/companies/{companyCode}", method = RequestMethod.GET )
    public CompanyRec retrievePerson ( @PathVariable int companyCode, HttpServletRequest request ) {

    //    HttpSession session = request.getSession ( );
        
        CompanyRec r = CompaniesModel.retrieveCompany ( companyCode, ds );
        
        return r;
    }
        
}
