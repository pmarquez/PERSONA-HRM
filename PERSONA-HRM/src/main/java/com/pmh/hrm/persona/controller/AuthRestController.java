
package com.pmh.hrm.persona.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pmh.hrm.persona.auth.LoginRec;
import com.pmh.hrm.persona.model.AuthModel;

/**
 *
 * @author pmarquez
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
            lr.setStatusMessage ( "Credenciales incorrectas o usuario inactivo." );
        }
        
        return lr;
    }
    
}
