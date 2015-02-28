
package org.pmh.hrm.persona.controller;


//   Standard Libraries Imports
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

//   Third Party Libraries Imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//   FENIX Framework Imports

//   PERSONA HRM Domain Imports
import com.pmh.hrm.persona.auth.LoginRec;
import com.pmh.hrm.persona.model.AuthModel;

/**
 * AuthRestController.java<br/><br/>
 * Creation Date 2015-02-28 11:06<br/><br/>
 * <b>DESCRIPTION:</b><br/><br/>
 * <p>REST Micrroservice controller for all things Authentication/Authorization</p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br/>
 * Version Date: 2015-02-28 11:06<br/>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2015-02-28 11:06
 */
@RestController
public class AuthRestController {

    @Autowired
    private DataSource ds;

    @RequestMapping ( value = "/api/1.0/mobile/login", method = RequestMethod.POST )
    public @ResponseBody LoginRec mobileLogin ( @RequestBody LoginRec loginRec, HttpServletRequest request ) {
          
        HttpSession session = request.getSession ( );
        
        //TODO Clear PASSWORD!!?
        LoginRec lr = AuthModel.authorizeUser ( loginRec.getEmail ( ), loginRec.getPasswd ( ), ds );

        if ( lr.isActive ( ) ) {
            session.setAttribute ( "loginData",  lr );
            lr.setStatusCode ( "000" );
            lr.setStatusMessage ( "No Errors." );

        } else {
            lr.setStatusCode ( "999" );
            lr.setStatusMessage ( "Incorrect Credentials or Inactive User." );
        }
        
        return lr;
    }
    
}
