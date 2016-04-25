
package com.fxt.validations;

//   Standard Libraries Imports


//   Third Party Libraries Imports


//   FENIX Framework Imports


//   Domain Imports


/**
 * IssueRec.java<br><br>
 * Creation Date 2015-07-21 12:38<br><br>
 * <b>DESCRIPTION:</b><br><br>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br>
 * Version Date: 2015-07-21 12:38<br>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2015-07-21 12:38
 */
public class IssueRec {
    protected String issueCode;
    protected String issueID;
    protected String issueMessage;
    protected String explanation;

    public IssueRec ( ) {
        this.issueCode    = "";
        this.issueID      = "";
        this.issueMessage = "";
        this.explanation  = "";
    }
    
    /**
     * @return the issueCode
     */
    public String getIssueCode() {
        return issueCode;
    }

    /**
     * @param issueCode the issueCode to set
     */
    public void setIssueCode(String issueCode) {
        this.issueCode = issueCode;
    }

    /**
     * @return the issueID
     */
    public String getIssueID() {
        return issueID;
    }

    /**
     * @param issueID the issueID to set
     */
    public void setIssueID(String issueID) {
        this.issueID = issueID;
    }

    /**
     * @return the issueMessage
     */
    public String getIssueMessage() {
        return issueMessage;
    }

    /**
     * @param issueMessage the issueMessage to set
     */
    public void setIssueMessage(String issueMessage) {
        this.issueMessage = issueMessage;
    }

    /**
     * @return the explanation
     */
    public String getExplanation() {
        return explanation;
    }

    /**
     * @param explanation the explanation to set
     */
    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
