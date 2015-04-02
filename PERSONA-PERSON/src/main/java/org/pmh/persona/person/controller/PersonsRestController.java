
package org.pmh.persona.person.controller;


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
//import org.springframework.web.client.RestTemplate;

//   FENIX Framework Imports
import org.pmh.util.ListWrapper;

//   Application Domain Imports
import org.pmh.persona.person.person.PersonBaseRec;
import org.pmh.persona.person.person.PersonRec;
import org.pmh.persona.person.model.PersonsModel;

/**
 * PersonsRestController.java<br/><br/>
 * Creation Date 2015-03-23 17:22<br/><br/>
 * <b>DESCRIPTION:</b><br/><br/>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br/>
 * Version Date: 2015-03-23 17:22<br/>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2015-03-23 17:22
 */
@RestController
public class PersonsRestController {

    @Autowired
    private DataSource ds;
    
    @RequestMapping ( value = "/personsAPI/1.0/persons", method = RequestMethod.GET )
    public ListWrapper baseItems ( HttpServletRequest request ) {

    //    HttpSession session = request.getSession ( );
        
        List<PersonBaseRec> l = PersonsModel.retrieveBasePersons ( ds );
        
        ListWrapper lw = new ListWrapper ( );
        lw.setAaData ( l );

        return lw;
    }
    
    @RequestMapping ( value = "/personsAPI/1.0/persons/{personCode}", method = RequestMethod.GET )
    public PersonRec retrievePerson ( @PathVariable int personCode, HttpServletRequest request ) {

    //    HttpSession session = request.getSession ( );
        
        PersonRec r = PersonsModel.retrievePerson ( personCode, ds );

        org.pmh.persona.person.education.AcademiaBaseRec abr = r.getAcademia().get ( 0 );
        
        System.out.println ( "Start Date: " + abr.getStartDate ( ) );
        System.out.println ( "End Date  : " + abr.getEndDate   ( ) );
        
        return r;
    }
        
}
