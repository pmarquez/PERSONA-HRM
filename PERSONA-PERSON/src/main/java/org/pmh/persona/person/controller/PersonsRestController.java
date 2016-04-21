
package org.pmh.persona.person.controller;


//   Standard Libraries Imports
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.util.List;
import javax.servlet.http.HttpSession;

//   Third Party Libraries Imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

//   FENIX Framework Imports
import com.fxt.process.ResponseRec;

//   Application Domain Imports
import org.pmh.persona.person.person.PersonBaseRec;
import org.pmh.persona.person.person.PersonRec;
import org.pmh.persona.person.model.PersonsModel;

/**
 * PersonsRestController.java<br><br>
 * Creation Date 2015-03-23 17:22<br><br>
 * <b>DESCRIPTION:</b><br><br>
 * <p>Rest Controller for all things PERSON</p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.1<br>
 * Version Date: 2016-04-21 21:39<br>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Updated RestController to use a <code>ResponseRec<></code> for handling the return values.</p></td>
 *<td width="80%"><p>Created method <code>personExists</code>.</p></td>
 *</tr>
 *<tr>
 *<td width="20%">Version 1.0<br>
 * Version Date: 2015-03-23 17:22<br>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.1 - 2016-04-21 21:39
 */
//TODO Token Authorization
//TODO Handle User Profiles
//TODO MultiTenancy - Create Account-Persons Hierarchy.
@RestController
public class PersonsRestController {

//   Controller Constants
    private static final int EMPTY_PERSON_LIST = 0;
    private static final int EMPTY_PERSON_REC  = 0;
    
//   Response Status
    private static final String OPERATION_SUCCESSFUL                            = "PERSONS_001";   //   The requested operation was succesfully completed.
    private static final String NO_PERSON_FOUND                                 = "PERSONS_002";   //   Could not find requested person.
    private static final String NO_PERSONS_FOUND                                = "PERSONS_003";   //   The requested list of persons is empty.
    private static final String INVALID_USER_PRIVILLEGES                        = "PERSONS_004";   //   User does not have the required privilleges for this call.
    private static final String INVALID_AUTHORIZATION_TOKEN                     = "PERSONS_005";   //   Authorization token received is not valid.
    private static final String INTERNAL_ERROR_ENCOUNTERED                      = "PERSONS_006";   //   An internal error was encountered.
    private static final String REQUESTED_PERSON_EXISTS                         = "PERSONS_007";   //   The requested person exists.
    private static final String REQUESTED_PERSON_DOES_NOT_EXIST                 = "PERSONS_008";   //   The requested person does not exist.

    @Autowired
    private DataSource ds;
    
    /**
     * Retrieves a list of persons from storage.
     * @param request
     * @return ResponseRec<List<PersonBaseRec>>
     */
    @RequestMapping ( value = "/personsAPI/1.0/persons/persons", method = RequestMethod.GET )
    public ResponseRec<List<PersonBaseRec>> personList ( HttpServletRequest request ) {

        HttpSession session = request.getSession ( );
        
        ResponseRec<List<PersonBaseRec>> rr = new ResponseRec<> ( );
        
        List<PersonBaseRec> l = PersonsModel.retrieveBasePersons ( ds );

        if ( l.size ( ) > PersonsRestController.EMPTY_PERSON_LIST ) {
            rr.setPayload       ( l );
            rr.setResultCode    ( PersonsRestController.OPERATION_SUCCESSFUL );
            rr.setResultMessage ( "The requested operation was succesfully completed." );   //TODO use the database based ISSUE_MESSAGE_ENTITY scheme. 
            
        } else {
            rr.setResultCode    ( PersonsRestController.NO_PERSONS_FOUND );
            rr.setResultMessage ( "The requested list of persons is empty." );              //TODO use the database based ISSUE_MESSAGE_ENTITY scheme. 

        }
        
        return rr;
    }

    /**
     * Retrieves a single person from storage
     * @param personCode
     * @param request
     * @return ResponseRec<PersonRec>
     */
    @RequestMapping ( value = "/personsAPI/1.0/persons/persons/{personCode}", method = RequestMethod.GET )
    public ResponseRec<PersonRec> retrievePerson ( @PathVariable int personCode, HttpServletRequest request ) {

    //    HttpSession session = request.getSession ( );
        
        ResponseRec<PersonRec> rr = new ResponseRec<> ( );

        PersonRec r = PersonsModel.retrievePerson ( personCode, ds );

        if ( r.getPersonCode ( ) > PersonsRestController.EMPTY_PERSON_REC ) {
            rr.setPayload ( r );
            rr.setResultCode    ( PersonsRestController.OPERATION_SUCCESSFUL );
            rr.setResultMessage ( "The requested operation was succesfully completed." );   //TODO use the database based ISSUE_MESSAGE_ENTITY scheme. 

        } else {
            rr.setResultCode    ( PersonsRestController.NO_PERSON_FOUND );
            rr.setResultMessage ( "Could not find requested person." );                     //TODO use the database based ISSUE_MESSAGE_ENTITY scheme. 
            
        }
//        org.pmh.persona.person.education.AcademiaBaseRec abr = r.getAcademia().get ( 0 );
        
        return rr;
    }

    /**
     * Checks whether a person exists in storage.
     * @param personCode
     * @param request
     * @return ResponseRec<Boolean>
     * @since 1.1
     */
    @RequestMapping ( value = "/personsAPI/1.0/persons/exists/{personCode}", method = RequestMethod.GET )
    public ResponseRec<Boolean> personExists ( @PathVariable int personCode, HttpServletRequest request ) {

    //    HttpSession session = request.getSession ( );
        
        ResponseRec<Boolean> rr = new ResponseRec<> ( );

        PersonRec r = PersonsModel.retrievePerson ( personCode, ds );

        if ( r.getPersonCode ( ) > PersonsRestController.EMPTY_PERSON_REC ) {
            rr.setPayload ( Boolean.TRUE );
            rr.setResultCode    ( PersonsRestController.REQUESTED_PERSON_EXISTS );
            rr.setResultMessage ( "The requested person exists." );                         //TODO use the database based ISSUE_MESSAGE_ENTITY scheme. 

        } else {
            rr.setPayload ( Boolean.FALSE );
            rr.setResultCode    ( PersonsRestController.REQUESTED_PERSON_DOES_NOT_EXIST );
            rr.setResultMessage ( "The requested person does not exist." );                 //TODO use the database based ISSUE_MESSAGE_ENTITY scheme. 
            
        }
        
        return rr;
    }

}
