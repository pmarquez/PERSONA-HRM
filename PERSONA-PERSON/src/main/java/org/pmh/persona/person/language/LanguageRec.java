
package org.pmh.persona.person.language;

//   Standard Libraries Imports

//   Third Party Libraries Imports

//   FENIX Framework Imports

//   Application Domain Imports

/**
 * LanguageBaseRec.java<br/><br/>
 * Creation Date 2015-04-06 11:30<br/><br/>
 * <b>DESCRIPTION:</b><br/><br/>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br/>
 * Version Date: 2015-04-06 11:30<br/>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2015-04-06 11:30
 */
public class LanguageRec extends LanguageBaseRec {
    private int     personCode;
    
    private boolean nativeLanguage;

    private int     listeningProficiencyCode;
    private String  listeningProficiencyLevel;
    private int     readingProficiencyCode;
    private String  readingProficiencyLevel;
    
    private int     spokenIteractionProficiencyCode;
    private String  spokenIteractionProficiencyLevel;
    private int     spokenProductionProficiencyCode;
    private String  spokenProductionProficiencyLevel;
    
    private int     writingProficiencyCode;
    private String  writingProficiencyLevel;

    public LanguageRec ( ) {
        
        super ( );
        
        this.personCode                       = 0;

        this.nativeLanguage                   = false;

        this.listeningProficiencyCode         = 0;
        this.listeningProficiencyLevel        = "";
        this.readingProficiencyCode           = 0;
        this.readingProficiencyLevel          = "";
    
        this.spokenIteractionProficiencyCode  = 0;
        this.spokenIteractionProficiencyLevel = "";
        this.spokenProductionProficiencyCode  = 0;
        this.spokenProductionProficiencyLevel = "";

        this.writingProficiencyCode           = 0;
        this.writingProficiencyLevel          = "";

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
     * @return the nativeLanguage
     */
    public boolean isNativeLanguage() {
        return nativeLanguage;
    }

    /**
     * @param nativeLanguage the nativeLanguage to set
     */
    public void setNativeLanguage(boolean nativeLanguage) {
        this.nativeLanguage = nativeLanguage;
    }

    /**
     * @return the listeningProficiencyCode
     */
    public int getListeningProficiencyCode() {
        return listeningProficiencyCode;
    }

    /**
     * @param listeningProficiencyCode the listeningProficiencyCode to set
     */
    public void setListeningProficiencyCode(int listeningProficiencyCode) {
        this.listeningProficiencyCode = listeningProficiencyCode;
    }

    /**
     * @return the listeningProficiencyLevel
     */
    public String getListeningProficiencyLevel() {
        return listeningProficiencyLevel;
    }

    /**
     * @param listeningProficiencyLevel the listeningProficiencyLevel to set
     */
    public void setListeningProficiencyLevel(String listeningProficiencyLevel) {
        this.listeningProficiencyLevel = listeningProficiencyLevel;
    }

    /**
     * @return the readingProficiencyCode
     */
    public int getReadingProficiencyCode() {
        return readingProficiencyCode;
    }

    /**
     * @param readingProficiencyCode the readingProficiencyCode to set
     */
    public void setReadingProficiencyCode(int readingProficiencyCode) {
        this.readingProficiencyCode = readingProficiencyCode;
    }

    /**
     * @return the readingProficiencyLevel
     */
    public String getReadingProficiencyLevel() {
        return readingProficiencyLevel;
    }

    /**
     * @param readingProficiencyLevel the readingProficiencyLevel to set
     */
    public void setReadingProficiencyLevel(String readingProficiencyLevel) {
        this.readingProficiencyLevel = readingProficiencyLevel;
    }

    /**
     * @return the spokenIteractionProficiencyCode
     */
    public int getSpokenIteractionProficiencyCode() {
        return spokenIteractionProficiencyCode;
    }

    /**
     * @param spokenIteractionProficiencyCode the spokenIteractionProficiencyCode to set
     */
    public void setSpokenIteractionProficiencyCode(int spokenIteractionProficiencyCode) {
        this.spokenIteractionProficiencyCode = spokenIteractionProficiencyCode;
    }

    /**
     * @return the spokenIteractionProficiencyLevel
     */
    public String getSpokenIteractionProficiencyLevel() {
        return spokenIteractionProficiencyLevel;
    }

    /**
     * @param spokenIteractionProficiencyLevel the spokenIteractionProficiencyLevel to set
     */
    public void setSpokenIteractionProficiencyLevel(String spokenIteractionProficiencyLevel) {
        this.spokenIteractionProficiencyLevel = spokenIteractionProficiencyLevel;
    }

    /**
     * @return the spokenProductionProficiencyCode
     */
    public int getSpokenProductionProficiencyCode() {
        return spokenProductionProficiencyCode;
    }

    /**
     * @param spokenProductionProficiencyCode the spokenProductionProficiencyCode to set
     */
    public void setSpokenProductionProficiencyCode(int spokenProductionProficiencyCode) {
        this.spokenProductionProficiencyCode = spokenProductionProficiencyCode;
    }

    /**
     * @return the spokenProductionProficiencyLevel
     */
    public String getSpokenProductionProficiencyLevel() {
        return spokenProductionProficiencyLevel;
    }

    /**
     * @param spokenProductionProficiencyLevel the spokenProductionProficiencyLevel to set
     */
    public void setSpokenProductionProficiencyLevel(String spokenProductionProficiencyLevel) {
        this.spokenProductionProficiencyLevel = spokenProductionProficiencyLevel;
    }

    /**
     * @return the writingProficiencyCode
     */
    public int getWritingProficiencyCode() {
        return writingProficiencyCode;
    }

    /**
     * @param writingProficiencyCode the writingProficiencyCode to set
     */
    public void setWritingProficiencyCode(int writingProficiencyCode) {
        this.writingProficiencyCode = writingProficiencyCode;
    }

    /**
     * @return the writingProficiencyLevel
     */
    public String getWritingProficiencyLevel() {
        return writingProficiencyLevel;
    }

    /**
     * @param writingProficiencyLevel the writingProficiencyLevel to set
     */
    public void setWritingProficiencyLevel(String writingProficiencyLevel) {
        this.writingProficiencyLevel = writingProficiencyLevel;
    }

}
