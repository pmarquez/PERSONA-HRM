
package org.pmh.hrm.persona.controller;


//   Standard Libraries Imports
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

//   Third Party Libraries Imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//   FENIX Framework Imports

//   PERSONA HRM Domain Imports
import com.pmh.hrm.persona.auth.LoginRec;
import com.pmh.hrm.persona.model.AuthModel;

/**
 * AuthController.java<br/><br/>
 * Creation Date 2015-02-28 11:03<br/><br/>
 * <b>DESCRIPTION:</b><br/><br/>
 * <p>Controller for all things Authentication/Authorization</p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br/>
 * Version Date: 2015-02-28 11:03<br/>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2015-02-28 11:03
 */
@Controller
public class AuthController {

    @Autowired
    private DataSource ds;

    @RequestMapping ( value = "/", method = RequestMethod.GET )
    public String homeSimple ( HttpServletRequest request ) {
        
        return "landing";
    }

    @RequestMapping ( value = "/login", method = RequestMethod.GET )
    public String login ( HttpServletRequest request ) {
  
        String returnTo = "landing";
        
        HttpSession session = request.getSession ( );
        
        //TODO Clear PASSWORD!!?
        LoginRec lr = AuthModel.authorizeUser ( "paulo.marquez@gmail.com", "5t1n6r4y", ds );

        if ( lr.isActive ( ) ) {
            session.setAttribute ( "loginData",  lr );
            returnTo = "registry";
        }
        
        return returnTo;
    }
    
    @RequestMapping ( value = "/signout", method = RequestMethod.GET )
    public String signOut ( HttpServletRequest request ) {
        HttpSession session = request.getSession ( );
        session.invalidate ( );
        
        return "landing";
    }

}
