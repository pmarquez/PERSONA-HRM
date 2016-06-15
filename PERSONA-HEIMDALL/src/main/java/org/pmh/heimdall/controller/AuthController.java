
package org.pmh.heimdall.controller;


//   Standard Libraries Imports
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.servlet.MultipartConfigElement;

//   Third Party Libraries Imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//   FENIX Framework Imports


//   Domain Imports


/**
 * AuthController.java<br><br>
 * Creation Date 2015-05-21 08:39<br><br>
 * <b>DESCRIPTION:</b><br><br>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br>
 * Version Date: 2015-05-21 08:39<br>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2015-05-21 08:39
 */
@Controller
public class AuthController {

    @Autowired
    private DataSource ds;

  // The Environment object will be used to read parameters from the application.properties configuration file
  @Autowired
  private MultipartConfigElement mce;

    
    @RequestMapping ( value = "/", method = RequestMethod.GET )
    public String homeSimple ( HttpServletRequest request ) {
        HttpSession session = request.getSession ( );
        
        return "dashboard";
    }
    
    @RequestMapping ( value = "/signout", method = RequestMethod.GET )
    public String signOut ( HttpServletRequest request ) {
        HttpSession session = request.getSession ( );
        session.invalidate ( );
        
        return "landing";
    }

    @RequestMapping ( value = "/dashboard", method = RequestMethod.GET )
    public String dashboard ( ) {
        
        return "dashboard";
    }

}
