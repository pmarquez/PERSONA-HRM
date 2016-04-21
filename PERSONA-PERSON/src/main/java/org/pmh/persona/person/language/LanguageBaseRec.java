
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
public class LanguageBaseRec {
    private int     languageCode;
    private String  languageName;
    private String  locale;
    private boolean active;

    public LanguageBaseRec ( ) {
        this.languageCode = 0;
        this.languageName = "";
        this.locale       = "";
        this.active       = false;
    }

    /**
     * @return the languageCode
     */
    public int getLanguageCode() {
        return languageCode;
    }

    /**
     * @param languageCode the languageCode to set
     */
    public void setLanguageCode(int languageCode) {
        this.languageCode = languageCode;
    }

    /**
     * @return the languageName
     */
    public String getLanguageName ( ) {
        return languageName;
    }

    /**
     * @param languageName the languageName to set
     */
    public void setLanguageName ( String languageName ) {
        this.languageName = languageName;
    }

    /**
     * @return the locale
     */
    public String getLocale ( ) {
        return locale;
    }

    /**
     * @param locale the locale to set
     */
    public void setLocale ( String locale ) {
        this.locale = locale;
    }

    /**
     * @return the active
     */
    public boolean isActive ( ) {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive ( boolean active ) {
        this.active = active;
    }
}
