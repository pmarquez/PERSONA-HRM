
package org.pmh.hrm.persona.company;

//   Standard Libraries Imports
import java.time.LocalDate;

//   Third Party Libraries Imports

//   FENIX Framework Imports

//   Application Domain Imports


/**
 * CompanyBaseRec.java<br/><br/>
 * Creation Date 2015-02-28 10:30<br/><br/>
 * <b>DESCRIPTION:</b><br/><br/>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br/>
 * Version Date: 2015-02-28 10:30<br/>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez Herrero 
 * @version 1.0 - 2015-02-28 10:30
 */
public class  CompanyBaseRec {
    private int       compnyCode;
    private String    companyTaxId;
    private String    companyName;
    private String    companySocialSecurityNumber;
    private LocalDate creationDate;
    private boolean   active;

    public CompanyBaseRec ( ) {
        this.compnyCode                  = 0;
        this.companyTaxId                = "";
        this.companyName                 = "";
        this.companySocialSecurityNumber = "";
        this.creationDate                = LocalDate.MIN;
        this.active                      = false;
    }

    /**
     * @return the compnyCode
     */
    public int getCompnyCode() {
        return compnyCode;
    }

    /**
     * @param compnyCode the compnyCode to set
     */
    public void setCompnyCode(int compnyCode) {
        this.compnyCode = compnyCode;
    }

    /**
     * @return the companyTaxId
     */
    public String getCompanyTaxId ( ) {
        return companyTaxId;
    }

    /**
     * @param companyTaxId the companyTaxId to set
     */
    public void setCompanyTaxId ( String companyTaxId ) {
        this.companyTaxId = companyTaxId;
    }

    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @return the companySocialSecurityNumber
     */
    public String getCompanySocialSecurityNumber() {
        return companySocialSecurityNumber;
    }

    /**
     * @param companySocialSecurityNumber the companySocialSecurityNumber to set
     */
    public void setCompanySocialSecurityNumber(String companySocialSecurityNumber) {
        this.companySocialSecurityNumber = companySocialSecurityNumber;
    }

    /**
     * @return the creationDate
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate the creationDate to set
     */
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }
    
}
