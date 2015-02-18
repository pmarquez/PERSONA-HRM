
package com.pmh.hrm.persona.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pmh.hrm.persona.auth.LoginRec;
import com.pmh.hrm.persona.model.AuthModel;

/**
 *
 * @author pmarquez
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
