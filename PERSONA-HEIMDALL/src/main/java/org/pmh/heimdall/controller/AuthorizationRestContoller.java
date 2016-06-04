
package org.pmh.heimdall.controller;

//   Standard Libraries Imports
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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

//   FENIX Framework Imports
import com.fxt.auth.LoginRec;
import org.pmh.heimdall.Main;

//   Domain Imports
import org.pmh.heimdall.process.AuthenticationResponseRec;

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

    //TODO - JACK SPARROW WAS HERE - Get rid of this ASAP - BEGIN
    String cerberusBaseURI     = "/Cerberus-1/AuthenticationAPI/1.0/";
    String loginAPIMethod      = "login/";
    //TODO - JACK SPARROW WAS HERE - Get rid of this ASAP - END

    public static final int SALT_ROUNDS = 12;

    @Autowired
    private DataSource ds;


    @RequestMapping ( value = "/heimdallAPI/1.0/login/login", method = RequestMethod.POST, consumes="application/json" )
    public @ResponseBody LoginRec doLogin ( @RequestBody LoginRec lr, Model model, HttpServletRequest request ) {
//    public @ResponseBody TempLoginData doLogin ( @RequestBody LoginRec lr, Model model, HttpServletRequest request ) {

        HttpSession session = request.getSession ( );

        AuthenticationResponseRec response = new AuthenticationResponseRec ( );

//   EXTERNAL STUFF - BEGIN

            RestTemplate restTemplate = new RestTemplate ( );
            
            HttpHeaders  headers      = new HttpHeaders ( );
            
            headers.set ( "service-id", "heimdall" );

            HttpEntity<String> entity = new HttpEntity<> ( "parameters", headers );

            String loginUri  = Main.SERVER_BASE_URI + cerberusBaseURI  + loginAPIMethod  + lr.getUserName ( ) + "/" + lr.getPasswd ( );
            
            try {
                ResponseEntity<AuthenticationResponseRec> resp = restTemplate.exchange ( loginUri, HttpMethod.GET, entity, AuthenticationResponseRec.class );
                response = resp.getBody ( );

                if ( response.getCode ( ) == 1 ) {   //   1 = Authentication Successful
                    lr.setAuthorizationToken ( response.getData ( ).getUserToken ( ) );
                    lr.setUserOK             ( true );
                    lr.setName               ( response.getData ( ).getName     ( ) );
                    lr.setLastName           ( response.getData ( ).getSurename ( ) );
                    lr.setUserName           ( response.getData ( ).getUsername ( ) );
                    lr.setPasswd             ( "" );
                    
                    lr.setStatusCode ( LoginRec.AUTHENTICATION_SUCCESSFUL_CODE    );
                    lr.setMessage    ( LoginRec.AUTHENTICATION_SUCCESSFUL_MESSAGE );

                } else {
                    lr.setAuthorizationToken ( ""    );
                    lr.setUserOK             ( false );
                    lr.setName               ( ""    );
                    lr.setLastName           ( ""    );
                    lr.setUserName           ( ""    );
                    lr.setPasswd             ( ""    );
                    
                    lr.setStatusCode ( LoginRec.AUTHENTICATION_FAILED_CODE    );
                    lr.setMessage    ( LoginRec.AUTHENTICATION_FAILED_MESSAGE );
                    
                }

            } catch ( RestClientException ex ) {
                System.err.print ( ex.getMessage ( ) );

            }

//   EXTERNAL STUFF - END

        System.out.println ( "Message   : " + response.getMessage ( ) );
        System.out.println ( "Token     : " + response.getData    ( ).getUserToken  ( ) );
        System.out.println ( "PersonCode: " + response.getData    ( ).getPersonCode ( ) );
//        TempLoginData tld = AuthModel.tempAuthorizeUser ( lr, ds );

//        tld.setCodStatus( 1 );

        return lr;

    }

/*    
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
*/
}
