
package com.fxt.navigation;

//   Standard Libraries Imports


//   Third Party Libraries Imports


//   FENIX Framework Imports


//   Application Domain Imports


/**
 * NavCommandRec.java<br><br>
 * Creation Date 2015-06-18 10:49<br><br>
 * <b>DESCRIPTION:</b><br><br>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br>
 * Version Date: 2015-06-18 10:49<br>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2015-06-18 10:49
 */
public class NavCommandRec {
    private int     commandCode;
    private int     sectionCode;
    private String  commandClass;
    private String  commandName;
    private int     commandTypeCode;
    private int     cardinality;
    private String  controllerCommand;
    private boolean isDefault;
    private boolean active;

    public NavCommandRec ( ) {
        this.commandCode       = 0;
        this.sectionCode       = 0;
        this.commandClass      = "";
        this.commandName       = "";
        this.commandTypeCode   = 0;
        this.cardinality       = 0;
        this.controllerCommand = "";
        this.isDefault         = false;
        this.active            = false;
    }

    /**
     * @return the commandCode
     */
    public int getCommandCode() {
        return commandCode;
    }

    /**
     * @param commandCode the commandCode to set
     */
    public void setCommandCode(int commandCode) {
        this.commandCode = commandCode;
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
     * @return the commandClass
     */
    public String getCommandClass() {
        return commandClass;
    }

    /**
     * @param commandClass the commandClass to set
     */
    public void setCommandClass(String commandClass) {
        this.commandClass = commandClass;
    }

    /**
     * @return the commandName
     */
    public String getCommandName() {
        return commandName;
    }

    /**
     * @param commandName the commandName to set
     */
    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    /**
     * @return the commandTypeCode
     */
    public int getCommandTypeCode() {
        return commandTypeCode;
    }

    /**
     * @param commandTypeCode the commandTypeCode to set
     */
    public void setCommandTypeCode(int commandTypeCode) {
        this.commandTypeCode = commandTypeCode;
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
    public String getControllerCommand() {
        return controllerCommand;
    }

    /**
     * @param controllerCommand the controllerCommand to set
     */
    public void setControllerCommand(String controllerCommand) {
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
}
