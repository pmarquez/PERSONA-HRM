
package org.pmh.persona.person.education;

//   Standard Libraries Imports
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

//   Third Party Libraries Imports
import com.fasterxml.jackson.annotation.JsonIgnore;

//   FENIX Framework Imports

//   Application Domain Imports

/**
 * AcademyBaseRec.java<br/><br/>
 * Creation Date 2015-03-28 21:22<br/><br/>
 * <b>DESCRIPTION:</b><br/><br/>
 * <p>Keeps track of academic achievements.</p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br/>
 * Version Date: 2015-03-28 21:22<br/>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2015-03-28 21:22
 */
public class AcademyBaseRec {
    private int           academyCode;
    private int           personCode;
    private LocalDate     startDate;
    private LocalDate     endDate;
    private boolean       ongoing;
    private String        degreeName;
    private String        institution;      //   Educational Institution
    private String        institutionCity;
    private String        institutionState;
    private String        institutionCountry;
    private String        achievements;
    private LocalDateTime creationDate;

    public AcademyBaseRec ( ) {
        this.academyCode        = 0;
        this.personCode         = 0;
        this.startDate          = LocalDate.MIN;
        this.endDate            = LocalDate.MAX;
        this.ongoing            = false;
        this.degreeName         = "";
        this.institution        = "";
        this.institutionCity    = "";
        this.institutionState   = "";
        this.institutionCountry = "";
        this.achievements       = "";
        this.creationDate       = LocalDateTime.now ( );
    }
    
    /**
     * @return the academyCode
     */
    public int getAcademyCode() {
        return academyCode;
    }

    /**
     * @param academyCode the academyCode to set
     */
    public void setAcademyCode(int academyCode) {
        this.academyCode = academyCode;
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
     * @return the degreeName
     */
    public String getDegreeName() {
        return degreeName;
    }

    /**
     * @param degreeName the degreeName to set
     */
    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    /**
     * @return the institution
     */
    public String getInstitution() {
        return institution;
    }

    /**
     * @param institution the institution to set
     */
    public void setInstitution(String institution) {
        this.institution = institution;
    }

    /**
     * @return the achievements
     */
    public String getAchievements() {
        return achievements;
    }

    /**
     * @param achievements the achievements to set
     */
    public void setAchievements(String achievements) {
        this.achievements = achievements;
    }
    
    /**
     * @return the creationDate
     */
    @JsonIgnore
    public LocalDateTime getCreationDateLDT ( ) {
        return creationDate;
    }
    
    public String getCretionDate ( ) {
        return ( creationDate.equals ( LocalDateTime.MIN ) ) ? "" : ( creationDate.getYear ( ) + "-" + creationDate.getMonthValue ( ) + "-" + creationDate.getDayOfMonth ( ) + " " + 
               creationDate.getHour ( ) + ":" + creationDate.getMinute ( ) + ":" + creationDate.getSecond ( ) );
    }

    /**
     * @param creationDate the creationDate to set
     */
    @JsonIgnore
    public void setCreationDate ( LocalDateTime creationDate ) {
        this.creationDate = creationDate;
    }
    
    public void setCreationDate ( String creationDate ) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern ( "yyyy-MM-dd HH:mm:ss" );
        try {
            this.creationDate = LocalDateTime.parse ( creationDate, dtf );
        } catch ( DateTimeParseException dtpe ) {            
            this.creationDate = LocalDateTime.MIN;
        }
    }

    /**
     * @return the startDate
     */
    @JsonIgnore
    public LocalDate getStartDateLD ( ) {
        return startDate;
    }
    
    public String getStartDate ( ) {
        return ( startDate.equals ( LocalDate.MIN ) ) ? "" : ( startDate.getYear ( ) + "-" + startDate.getMonthValue ( ) + "-" + startDate.getDayOfMonth ( ) );
    }

    /**
     * @param startDate the startDate to set
     */
    @JsonIgnore
    public void setStartDate ( LocalDate startDate ) {
        this.startDate = startDate;
    }
    
    public void setStartDate ( String startDate ) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern ( "yyyy-MM-dd HH:mm:ss" );
        try {
            this.startDate = LocalDate.parse ( startDate, dtf );
        } catch ( DateTimeParseException dtpe ) {            
            this.startDate = LocalDate.MIN;
        }
    }

    /**
     * @return the endDate
     */
    @JsonIgnore
    public LocalDate getEndDateLD ( ) {
        return endDate;
    }
    
    public String getEndDate ( ) {
        return ( endDate.equals ( LocalDate.MIN ) ) ? "" : ( endDate.getYear ( ) + "-" + endDate.getMonthValue ( ) + "-" + endDate.getDayOfMonth ( ) );
    }

    /**
     * @param endDate the endDate to set
     */
    @JsonIgnore
    public void setEndDate ( LocalDate endDate ) {
        this.endDate = endDate;
    }
    
    public void setEndDate ( String endDate ) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern ( "yyyy-MM-dd HH:mm:ss" );
        try {
            this.endDate = LocalDate.parse ( endDate, dtf );
        } catch ( DateTimeParseException dtpe ) {            
            this.endDate = LocalDate.MIN;
        }
    }

    /**
     * @return the ongoing
     */
    public boolean isOngoing() {
        return ongoing;
    }

    /**
     * @param ongoing the ongoing to set
     */
    public void setOngoing(boolean ongoing) {
        this.ongoing = ongoing;
    }

    /**
     * @return the institutionCity
     */
    public String getInstitutionCity() {
        return institutionCity;
    }

    /**
     * @param institutionCity the institutionCity to set
     */
    public void setInstitutionCity(String institutionCity) {
        this.institutionCity = institutionCity;
    }

    /**
     * @return the institutionState
     */
    public String getInstitutionState() {
        return institutionState;
    }

    /**
     * @param institutionState the institutionState to set
     */
    public void setInstitutionState(String institutionState) {
        this.institutionState = institutionState;
    }

    /**
     * @return the institutionCountry
     */
    public String getInstitutionCountry() {
        return institutionCountry;
    }

    /**
     * @param institutionCountry the institutionCountry to set
     */
    public void setInstitutionCountry(String institutionCountry) {
        this.institutionCountry = institutionCountry;
    }

}
