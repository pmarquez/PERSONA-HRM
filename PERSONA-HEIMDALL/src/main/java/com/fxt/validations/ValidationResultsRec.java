
package com.fxt.validations;

//   Standard Libraries Imports
import java.util.ArrayList;
import java.util.List;

//   Third Party Libraries Imports


//   FENIX Framework Imports


//   Domain Imports


/**
 * ValidationResultsRec.java<br/><br/>
 * Creation Date 2015-08-17 09:39<br/><br/>
 * <b>DESCRIPTION:</b><br/><br/>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br/>
 * Version Date: 2015-08-17 09:39<br/>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2015-08-17 09:39
 */
public class ValidationResultsRec {
    protected IssueRec       status;
    protected String         navTo;
    protected String         authToken;
    
    protected List<IssueRec> issues;

    public ValidationResultsRec ( ) {
        this.status    = new IssueRec ( );
        this.navTo     = "";
        this.authToken = "";
        this.issues    = new ArrayList<> ( );
    }

    /**
     * @return the status
     */
    public IssueRec getStatus ( ) {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus ( IssueRec status ) {
        this.status = status;
    }

    /**
     * @return the issues
     */
    public List<IssueRec> getIssues() {
        return issues;
    }

    /**
     * @param issues the issues to set
     */
    public void setIssues(List<IssueRec> issues) {
        this.issues = issues;
    }

    /**
     * @return the navTo
     */
    public String getNavTo() {
        return navTo;
    }

    /**
     * @param navTo the navTo to set
     */
    public void setNavTo(String navTo) {
        this.navTo = navTo;
    }

    /**
     * @return the authToken
     */
    public String getAuthToken() {
        return authToken;
    }

    /**
     * @param authToken the authToken to set
     */
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
