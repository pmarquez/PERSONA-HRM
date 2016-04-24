
package com.fxt.address;

//   Standard Libraries Imports


//   Third Party Libraries Imports


//   FENIX Framework Imports


//   Domain Imports


/**
 * PostCodeRulesRec.java<br/><br/>
 * Created on 2015-08-11 15:51<br/><br/>
 * <b>DESCRIPTION:</b><br/><br/>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br/>
 * 2015-08-11 15:51<br/>
 *Paulo Márquez </td>
 *<td width="80%"><p>Creación</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2015-08-11 15:51
 */
public class PostCodeRulesRec {
    protected int     lineCode; 
    protected int     provinceCode; 
    protected String  provinceName;
    protected String  postCodePrefix;
    protected String  postCodePatterns;
    protected boolean active;

    public PostCodeRulesRec ( ) {
        this.lineCode         = 0;
        this.provinceCode     = 0;
        this.provinceName     = "";
        this.postCodePrefix   = "";
        this.postCodePatterns = "";
        this.active           = false;
    }

    /**
     * @return the lineCode
     */
    public int getLineCode() {
        return lineCode;
    }

    /**
     * @param lineCode the lineCode to set
     */
    public void setLineCode(int lineCode) {
        this.lineCode = lineCode;
    }

    /**
     * @return the provinceCode
     */
    public int getProvinceCode() {
        return provinceCode;
    }

    /**
     * @param provinceCode the provinceCode to set
     */
    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }

    /**
     * @return the provinceName
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * @param provinceName the provinceName to set
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    /**
     * @return the postCodePrefix
     */
    public String getPostCodePrefix() {
        return postCodePrefix;
    }

    /**
     * @param postCodePrefix the postCodePrefix to set
     */
    public void setPostCodePrefix(String postCodePrefix) {
        this.postCodePrefix = postCodePrefix;
    }

    /**
     * @return the postCodePatterns
     */
    public String getPostCodePatterns() {
        return postCodePatterns;
    }

    /**
     * @param postCodePatterns the postCodePatterns to set
     */
    public void setPostCodePatterns(String postCodePatterns) {
        this.postCodePatterns = postCodePatterns;
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
