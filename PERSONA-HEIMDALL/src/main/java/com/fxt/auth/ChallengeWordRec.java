
package com.fxt.auth;

/**
 * ChallengeWordRec.java<br/><br/>
 * Creation Date 2015-06-03 19:13<br/><br/>
 * <b>DESCRIPTION:</b><br/><br/>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br/>
 * Version Date: 2015-06-03 19:13<br/>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2015-06-03 19:13
 */
public class ChallengeWordRec {
    private int    wordCode;
    private String word;
    private int    timesSelected;

    public ChallengeWordRec ( ) {
        this.wordCode      = 0;
        this.word          = "";
        this.timesSelected = 0;
    }

    /**
     * @return the wordCode
     */
    public int getWordCode() {
        return wordCode;
    }

    /**
     * @param wordCode the wordCode to set
     */
    public void setWordCode(int wordCode) {
        this.wordCode = wordCode;
    }

    /**
     * @return the word
     */
    public String getWord() {
        return word;
    }

    /**
     * @param word the word to set
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * @return the timesSelected
     */
    public int getTimesSelected() {
        return timesSelected;
    }

    /**
     * @param timesSelected the timesSelected to set
     */
    public void setTimesSelected(int timesSelected) {
        this.timesSelected = timesSelected;
    }
    
}
