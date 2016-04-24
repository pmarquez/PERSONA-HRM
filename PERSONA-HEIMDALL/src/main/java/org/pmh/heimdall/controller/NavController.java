
package org.pmh.heimdall.controller;


//   Standard Libraries Imports
import com.fxt.navigation.NavigationRec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//   Third Party Libraries Imports
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//   FENIX Framework Imports


//   Domain Imports


/**
 * NavController.java<br/><br/>
 * Creation Date 2015-06-19 20:36<br/><br/>
 * <b>DESCRIPTION:</b><br/><br/>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br/>
 * Version Date: 2015-06-19 20:36<br/>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2015-06-19 20:36
 */
@Controller
public class NavController {
    
    @RequestMapping ( value = "/sections", method = RequestMethod.GET )
    public String dispatchSection ( HttpServletRequest request ) {

        HttpSession   session = request.getSession ( );
        NavigationRec nav     = ( NavigationRec ) session.getAttribute ( "nav" );
        
        String section = ( request.getParameter ( "s" ) != null ) ? request.getParameter ( "s" ) : "";
        
        if ( nav.isValidSectionForUser ( section ) ) {
            nav.navToSection ( section );
        }

        System.out.println ( "Command: " + nav.getCurrentCommand ( ).getControllerCommand ( ) );
        return nav.getCurrentCommand ( ).getControllerCommand ( );
        
    }

    @RequestMapping ( value = "/commands", method = RequestMethod.GET )
    public String dispatchCommand ( HttpServletRequest request ) {

        HttpSession   session = request.getSession ( );
        NavigationRec nav     = ( NavigationRec ) session.getAttribute ( "nav" );
        
        String command = ( request.getParameter ( "c" ) != null ) ? request.getParameter ( "c" ) : "";

        if ( nav.isValidCommandForUser ( command ) ) {
            nav.navToCommand ( command );
        }

        System.out.println ( "Command: " + nav.getCurrentCommand ( ).getControllerCommand ( ) );
        return nav.getCurrentCommand ( ).getControllerCommand ( );

    }
    
}
