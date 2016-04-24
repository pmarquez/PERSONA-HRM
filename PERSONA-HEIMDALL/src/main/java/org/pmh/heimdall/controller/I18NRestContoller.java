
package org.pmh.heimdall.controller;

//   Standard Libraries Imports
import javax.sql.DataSource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

//   Third Party Libraries Imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

//   FENIX Framework Imports
import com.fxt.util.HtmlListElement;

//   Domain Imports
import org.pmh.heimdall.model.UtilsModel;

/**
 * I18NRestController.java<br><br>
 * Creation Date 2015-07-20 06:57<br><br>
 * <b>DESCRIPTION:</b><br><br>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br>
 * Version Date: 2015-07-20 06:57<br>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2015-07-20 06:57
 */
@RestController
public class I18NRestContoller {

    @Autowired
    private DataSource ds;

    @RequestMapping ( value = "/fiAPI/1.0/i18n/formData/{formCode}/{locale}", method = RequestMethod.GET )
    public List<HtmlListElement> retrieveI18NData ( @PathVariable int formCode, @PathVariable String locale, HttpServletRequest request ) {
        
        List<HtmlListElement> i18nData = UtilsModel.retrieveI18NStringsList ( formCode, locale, ds );
        
        return i18nData;
    }

}
