
package org.pmh.hrm.persona.contract;

//   Standard Libraries Imports
import java.time.LocalDate;

//   Third Party Libraries Imports

//   FENIX Framework Imports

//   Application Domain Imports


/**
 * ContractBaseRec.java<br/><br/>
 * Creation Date 2015-02-28 10:37<br/><br/>
 * <b>DESCRIPTION:</b><br/><br/>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br/>
 * Version Date: 2015-02-28 10:37<br/>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez Herrero
 * @version 1.0 - 2015-02-28 10:37
 */
public class  ContractBaseRec {
    private int       contractCode;
    private int       contractTypeCode;
    private String    contractType;
    private int       postCode;   //   Position/Office/Cargo/Plaza - NEVER POSTAL CODE, PLEASE!!!
    private String    postName;
    private int       companyCode;
    private String    companyName;
    private int       personCode;
    private String    personFirstName;
    private String    personMiddleName;
    private String    personLastName;
    private String    personIdType;
    private String    personIdNumber;
    private LocalDate creationDate;
    private LocalDate startingDate;
    private boolean   active;

    public ContractBaseRec ( ) {
        this.contractCode     = 0;
        this.contractTypeCode = 0;
        this.contractType     = "";
        this.postCode         = 0;
        this.postName         = "";
        this.companyCode      = 0;
        this.companyName      = "";
        this.personCode       = 0;
        this.personFirstName  = "";
        this.personMiddleName = "";
        this.personLastName   = "";
        this.personIdType     = "";
        this.personIdNumber   = "";
        this.creationDate     = LocalDate.MIN;
        this.startingDate     = LocalDate.MIN;
        this.active           = false;
    }

    
    
    /**
     * @return the contractCode
     */
    public int getContractCode() {
        return contractCode;
    }

    /**
     * @param contractCode the contractCode to set
     */
    public void setContractCode(int contractCode) {
        this.contractCode = contractCode;
    }

    /**
     * @return the contractTypeCode
     */
    public int getContractTypeCode() {
        return contractTypeCode;
    }

    /**
     * @param contractTypeCode the contractTypeCode to set
     */
    public void setContractTypeCode(int contractTypeCode) {
        this.contractTypeCode = contractTypeCode;
    }

    /**
     * @return the postCode
     */
    public int getPostCode() {
        return postCode;
    }

    /**
     * @param postCode the postCode to set
     */
    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    /**
     * @return the companyCode
     */
    public int getCompanyCode() {
        return companyCode;
    }

    /**
     * @param companyCode the companyCode to set
     */
    public void setCompanyCode(int companyCode) {
        this.companyCode = companyCode;
    }

    /**
     * @return the personCode
     */
    public int getPersonCode() {
        return personCode;
    }

    /**
     * @param personCode the personCode to set
     */
    public void setPersonCode(int personCode) {
        this.personCode = personCode;
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
     * @return the startingDate
     */
    public LocalDate getStartingDate() {
        return startingDate;
    }

    /**
     * @param startingDate the startingDate to set
     */
    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
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

    /**
     * @return the contractType
     */
    public String getContractType() {
        return contractType;
    }

    /**
     * @param contractType the contractType to set
     */
    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    /**
     * @return the postName
     */
    public String getPostName() {
        return postName;
    }

    /**
     * @param postName the postName to set
     */
    public void setPostName(String postName) {
        this.postName = postName;
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
     * @return the personFirstName
     */
    public String getPersonFirstName() {
        return personFirstName;
    }

    /**
     * @param personFirstName the personFirstName to set
     */
    public void setPersonFirstName(String personFirstName) {
        this.personFirstName = personFirstName;
    }

    /**
     * @return the personMiddleName
     */
    public String getPersonMiddleName() {
        return personMiddleName;
    }

    /**
     * @param personMiddleName the personMiddleName to set
     */
    public void setPersonMiddleName(String personMiddleName) {
        this.personMiddleName = personMiddleName;
    }

    /**
     * @return the personLastName
     */
    public String getPersonLastName() {
        return personLastName;
    }

    /**
     * @param personLastName the personLastName to set
     */
    public void setPersonLastName(String personLastName) {
        this.personLastName = personLastName;
    }

    /**
     * @return the personIdType
     */
    public String getPersonIdType() {
        return personIdType;
    }

    /**
     * @param personIdType the personIdType to set
     */
    public void setPersonIdType(String personIdType) {
        this.personIdType = personIdType;
    }

    /**
     * @return the personIdNumber
     */
    public String getPersonIdNumber() {
        return personIdNumber;
    }

    /**
     * @param personIdNumber the personIdNumber to set
     */
    public void setPersonIdNumber(String personIdNumber) {
        this.personIdNumber = personIdNumber;
    }
    
}
