
package org.pmh.heimdall.controller;


//   Standard Libraries Imports
import javax.servlet.http.HttpServletRequest;

//   Third Party Libraries Imports
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//   FENIX Framework Imports


//   Domain Imports


/**
 * HomeController.java<br><br>
 * Creation Date 2015-05-21 08:42<br><br>
 * <b>DESCRIPTION:</b><br><br>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br>
 * Version Date: 2015-05-21 08:42<br>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2015-05-21 08:42
 */
@Controller
public class HomeController {
    
    /**
     * Captain Sparrow was here!!!
     * @param request
     * @return 
     */
    @RequestMapping ( value = "/client", method = RequestMethod.GET )
    public String client ( HttpServletRequest request ) {
        
        return "client";
    }
    
    @RequestMapping ( value = "/home", method = RequestMethod.GET )
    public String homeFull ( HttpServletRequest request ) {
        
        return "home";
    }
    
    @RequestMapping ( value = "/publicHome", method = RequestMethod.GET )
    public String publicHome ( HttpServletRequest request ) {
        
        return "splash_es";
    }

    
//    @RequestMapping ( value = "/error", method = RequestMethod.GET )
//    public String handleError ( ) {
//        return "error";
//    }
    
}
