
package org.pmh.hrm.persona.controller;


//   Standard Libraries Imports
import com.pmh.hrm.persona.controller.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.util.List;

//   Third Party Libraries Imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

//   FENIX Framework Imports

//   PERSONA HRM Domain Imports
import org.pmh.hrm.persona.contract.ContractBaseRec;
import org.pmh.hrm.persona.model.ContractModel;

/**
 * ContractRestController.java<br/><br/>
 * Creation Date 2015-02-28 11:10<br/><br/>
 * <b>DESCRIPTION:</b><br/><br/>
 * <p>Controller for all things Authentication/Authorization</p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br/>
 * Version Date: 2015-02-28 11:10<br/>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2015-02-28 11:10
 */
@RestController
public class ContractRestController {

    @Autowired
    private DataSource ds;

    @RequestMapping ( value = "/contractAPI/1.0/contract", method = RequestMethod.GET )
    public List baseContracts ( HttpServletRequest request ) {

    //    HttpSession session = request.getSession ( );
        
        List<ContractBaseRec> l = ContractModel.retrieveBaseContracts ( ds );
        
        return l;
    }
    
    @RequestMapping ( value = "/contractAPI/1.0/contract/{contractCode}", method = RequestMethod.GET )
    public ContractBaseRec baseContract ( @PathVariable int contractCode, HttpServletRequest request ) {

//        HttpSession session = request.getSession ( );
        
        ContractBaseRec cbr = ContractModel.retrieveBaseContract ( contractCode, ds );
        
        return cbr;
    }
    
}
