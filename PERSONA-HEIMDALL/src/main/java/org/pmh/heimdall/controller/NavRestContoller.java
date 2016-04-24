
package org.pmh.heimdall.controller;

//   Standard Libraries Imports
import javax.sql.DataSource;
import javax.servlet.http.HttpServletRequest;

//   Third Party Libraries Imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//   FENIX Framework Imports
import com.fxt.navigation.NavigationRec;

//   Domain Imports
import javax.servlet.http.HttpSession;

/**
 * NavRestController.java<br/><br/>
 * Creation Date 2015-06-19 10:05<br/><br/>
 * <b>DESCRIPTION:</b><br/><br/>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br/>
 * Version Date: 2015-06-19 10:05<br/>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2015-06-19 10:05
 */
@RestController
public class NavRestContoller {

    @Autowired
    private DataSource ds;
    
    @RequestMapping ( value = "/adsAPI/1.0/nav/retrieveNavigation", method = RequestMethod.GET )
    public NavigationRec retrieveNavigation ( HttpServletRequest request ) {

        HttpSession   session = request.getSession ( );
        NavigationRec nav     = ( NavigationRec ) session.getAttribute ( "nav" );

        return nav;
    }

}
