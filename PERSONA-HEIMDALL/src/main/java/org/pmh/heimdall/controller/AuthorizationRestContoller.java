
package org.pmh.heimdall.controller;

//   Standard Libraries Imports
import com.fxt.cryptography.BCrypt;
import com.fxt.navigation.NavCommandRec;
import com.fxt.navigation.NavSectionRec;
import javax.sql.DataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//   Third Party Libraries Imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

//   FENIX Framework Imports
import com.fxt.navigation.NavigationRec;
import com.fxt.validations.ValidationResultsRec;

//   Domain Imports
import org.pmh.heimdall.model.AuthModel;
import com.fxt.auth.LoginRec;
import org.pmh.heimdall.model.NavigationModel;
import org.pmh.heimdall.model.UtilsModel;
import org.pmh.heimdall.validations.LoginValidations;

/**
 * AuthorizationRestController.java<br><br>
 * Creation Date 2016-04-25 17:34<br><br>
 * <b>DESCRIPTION:</b><br><br>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br>
 * Version Date: 2015-04-25 17:34<br>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2015-04-25 17:34
 */
@RestController
public class AuthorizationRestContoller {

    public static final int SALT_ROUNDS = 12;
    
    @Autowired
    private DataSource ds;
    
    
    @RequestMapping ( value = "/heimdallAPI/1.0/login/login", method = RequestMethod.POST, consumes="application/json" )
    public @ResponseBody ValidationResultsRec doLogin ( @RequestBody LoginRec lr, Model model, HttpServletRequest request ) {

        HttpSession session = request.getSession ( );

        ValidationResultsRec vrr = new ValidationResultsRec ( );        

//TODO Clear PASSWORD!!? 
        BCrypt.hashpw (lr.getPasswd ( ), BCrypt.gensalt (AuthorizationRestContoller.SALT_ROUNDS ) );
        LoginRec logr = AuthModel.authorizeUser ( lr.getUserName ( ), ds );

        if ( logr.isActive ( ) && ( BCrypt.checkpw ( lr.getPasswd ( ), logr.getPasswd ( ) ) ) ) {
            
            session.setAttribute ( "loginData",  logr );

        //    Get Client
            NavigationRec nav = new NavigationRec ( );
            nav.setSections ( NavigationModel.retrieveNavigation ( logr.getRoleCode ( ), ds ) );   //   LOCALE?

            session.setAttribute ( "nav",  nav );
            
            for ( int i = 0; i < nav.getSections ( ).size ( ); i++ ) {
                NavSectionRec nsr = nav.getSections ( ).get ( i );
                System.out.println ( nsr.getSectionName ( ) + " - " + nsr.getControllerCommand ( ) );
                for ( int j = 0; j < nsr.getCommands ( ).size ( ); j++ ) {
                    NavCommandRec ncr = nsr.getCommands ( ).get ( j );
                    System.out.println ( "   " + ncr.getCommandName ( ) + " : " + ncr.getControllerCommand ( ) );
                }
            }
            
            //System.out.println ( "Section Controller Command: " + nav.getCurrentSection ( ).getControllerCommand ( ) );
            vrr.setNavTo ( "sections?s=" + nav.getCurrentSection ( ).getControllerCommand ( ) );
//            vrr.setNavTo ( "index" );

        } else {
            vrr.setStatus ( UtilsModel.retrieveIssueData ( LoginValidations.LOGIN_INCORRECT_USERNAME_OR_PASSWORD, "es-ES", ds ) );   //   JACK SPARROW WAS HERE - "es-ES cannot be hardwired"

        }
        
        return vrr;

    }

    @RequestMapping ( value = "/heimdallAPI/1.0/login/logout", method = RequestMethod.POST, consumes="application/json" )
    public @ResponseBody ValidationResultsRec doLogout ( @RequestBody LoginRec lr, Model model, HttpServletRequest request ) {

        HttpSession session = request.getSession ( );

        ValidationResultsRec vrr = new ValidationResultsRec ( );        

//TODO Clear PASSWORD!!? 
        BCrypt.hashpw (lr.getPasswd ( ), BCrypt.gensalt (AuthorizationRestContoller.SALT_ROUNDS ) );
        LoginRec logr = AuthModel.authorizeUser ( lr.getUserName ( ), ds );

        if ( logr.isActive ( ) && ( BCrypt.checkpw ( lr.getPasswd ( ), logr.getPasswd ( ) ) ) ) {
            
            session.setAttribute ( "loginData",  logr );

        //    Get Client
            NavigationRec nav = new NavigationRec ( );
            nav.setSections ( NavigationModel.retrieveNavigation ( logr.getRoleCode ( ), ds ) );   //   LOCALE?

            session.setAttribute ( "nav",  nav );
            
            for ( int i = 0; i < nav.getSections ( ).size ( ); i++ ) {
                NavSectionRec nsr = nav.getSections ( ).get ( i );
                System.out.println ( nsr.getSectionName ( ) + " - " + nsr.getControllerCommand ( ) );
                for ( int j = 0; j < nsr.getCommands ( ).size ( ); j++ ) {
                    NavCommandRec ncr = nsr.getCommands ( ).get ( j );
                    System.out.println ( "   " + ncr.getCommandName ( ) + " : " + ncr.getControllerCommand ( ) );
                }
            }
            
            //System.out.println ( "Section Controller Command: " + nav.getCurrentSection ( ).getControllerCommand ( ) );
            vrr.setNavTo ( "sections?s=" + nav.getCurrentSection ( ).getControllerCommand ( ) );
//            vrr.setNavTo ( "index" );

        } else {
            vrr.setStatus ( UtilsModel.retrieveIssueData ( LoginValidations.LOGIN_INCORRECT_USERNAME_OR_PASSWORD, "es-ES", ds ) );   //   JACK SPARROW WAS HERE - "es-ES cannot be hardwired"

        }
        
        return vrr;

    }

}
