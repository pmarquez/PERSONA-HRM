
package com.fxt.navigation;

//   Standard Libraries Imports
import java.util.ArrayList;
import java.util.List;

//   Third Party Libraries Imports


//   FENIX Framework Imports


//   Application Domain Imports


/**
 * NavSectionRec.java<br/><br/>
 * Creation Date 2015-06-18 10:50<br/><br/>
 * <b>DESCRIPTION:</b><br/><br/>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br/>
 * Version Date: 2015-06-18 10:50<br/>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2015-06-18 10:50
 */
public class NavSectionRec {
    private int           sectionCode;
    private String        sectionClass;
    private String        sectionName;
    private int           tabTypeCode;
    private String        tabTypeName;
    private String        subMenuLabel;
    private int           cardinality;
    private String        controllerCommand;
    private boolean       isDefault;
    private boolean       active;
    
    private List<NavCommandRec> commands;
    
    private NavCommandRec currentCommand;

    public NavSectionRec ( ) {
        this.sectionCode       = 0;
        this.sectionClass      = "";
        this.sectionName       = "";
        this.tabTypeCode       = 0;
        this.tabTypeName       = "";
        this.subMenuLabel      = "";
        this.cardinality       = 0;
        this.controllerCommand = "";
        this.isDefault         = false;
        this.active            = false;
        
        this.commands          = new ArrayList<>   ( );

        this.currentCommand    = new NavCommandRec ( );

    }

    /**
     * @return the sectionCode
     */
    public int getSectionCode() {
        return sectionCode;
    }

    /**
     * @param sectionCode the sectionCode to set
     */
    public void setSectionCode(int sectionCode) {
        this.sectionCode = sectionCode;
    }

    /**
     * @return the sectionClass
     */
    public String getSectionClass() {
        return sectionClass;
    }

    /**
     * @param sectionClass the sectionClass to set
     */
    public void setSectionClass(String sectionClass) {
        this.sectionClass = sectionClass;
    }

    /**
     * @return the sectionName
     */
    public String getSectionName() {
        return sectionName;
    }

    /**
     * @param sectionName the sectionName to set
     */
    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    /**
     * @return the tabTypeCode
     */
    public int getTabTypeCode() {
        return tabTypeCode;
    }

    /**
     * @param tabTypeCode the tabTypeCode to set
     */
    public void setTabTypeCode(int tabTypeCode) {
        this.tabTypeCode = tabTypeCode;
    }

    /**
     * @return the tabTypeName
     */
    public String getTabTypeName() {
        return tabTypeName;
    }

    /**
     * @param tabTypeName the tabTypeName to set
     */
    public void setTabTypeName(String tabTypeName) {
        this.tabTypeName = tabTypeName;
    }

    /**
     * @return the subMenuLabel
     */
    public String getSubMenuLabel() {
        return subMenuLabel;
    }

    /**
     * @param subMenuLabel the subMenuLabel to set
     */
    public void setSubMenuLabel(String subMenuLabel) {
        this.subMenuLabel = subMenuLabel;
    }

    /**
     * @return the cardinality
     */
    public int getCardinality() {
        return cardinality;
    }

    /**
     * @param cardinality the cardinality to set
     */
    public void setCardinality(int cardinality) {
        this.cardinality = cardinality;
    }

    /**
     * @return the controllerCommand
     */
    public String getControllerCommand ( ) {
        return controllerCommand;
    }

    /**
     * @param controllerCommand the controllerCommand to set
     */
    public void setControllerCommand ( String controllerCommand ) {
        this.controllerCommand = controllerCommand;
    }

    /**
     * @return the isDefault
     */
    public boolean isDefault ( ) {
        return isDefault;
    }

    /**
     * @param isDefault the isDefault to set
     */
    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
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
     * @return the commands
     */
    public List<NavCommandRec> getCommands() {
        return commands;
    }

    /**
     * @param commands the commands to set
     */
    public void setCommands(List<NavCommandRec> commands) {
        this.commands = commands;
    }

    /**
     * @return the currentCommand
     */
    public NavCommandRec getCurrentCommand() {
        return currentCommand;
    }

    /**
     * @param currentCommand the currentCommand to set
     */
    public void setCurrentCommand(NavCommandRec currentCommand) {
        this.currentCommand = currentCommand;
    }

}