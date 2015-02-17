package org.pmh.hrm.persona.person;

//   Standard Libraries Imports

//   Third Party Libraries Imports

//   FENIX Framework Imports

//   Application Domain Imports


/**
 * PersonBaseRec.java<br/><br/>
 * Creation Date 2015-02-09 07:19<br/><br/>
 * <b>DESCRIPTION:</b><br/><br/>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br/>
 * Version Date: 2015-02-09 07:19<br/>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2015-02-09 07:19
 */
/**
 *
 * @author pmarquez- 2015-02-09 
 */
public class  PersonBaseRec {
    private int    personCode;
    private int    idTypeCode;
    private String idType;
    private String idNumber;
    private String firstName;
    private String middleName;
    private String lastName;
    
//    DateTimeUtil dateOfBirth;

    public PersonBaseRec ( ) {
        this.personCode       = 0;
        this.idTypeCode       = 0;
        this.idType           = "";
        this.idNumber         = "";
        this.firstName        = "";
        this.middleName       = "";
        this.lastName         = "";
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
     * @return the idTypeCode
     */
    public int getIdTypeCode() {
        return idTypeCode;
    }

    /**
     * @param IdTypeCode the idTypeCode to set
     */
    public void setIdTypeCode(int IdTypeCode) {
        this.idTypeCode = IdTypeCode;
    }

    /**
     * @return the idType
     */
    public String getIdType() {
        return idType;
    }

    /**
     * @param idType the idType to set
     */
    public void setIdType(String idType) {
        this.idType = idType;
    }

    /**
     * @return the idNumber
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * @param idNumber the idNumber to set
     */
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
    
    /**
     * @return the personFullName
     */
    public String getPersonFullName ( ) {
        return this.firstName + " " + 
               ( ( this.middleName.equals ( "" ) ) ? "" : this.middleName + " " ) + 
               this.lastName;
    }    

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * @param middleName the middleName to set
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
