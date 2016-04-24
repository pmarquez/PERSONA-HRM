
package com.fxt.navigation;

//   Standard Libraries Imports
import java.util.ArrayList;
import java.util.List;

//   Third Party Libraries Imports


//   FENIX Framework Imports


//   Application Domain Imports


/**
 * NavigationRec.java<br/><br/>
 * Creation Date 2015-06-18 10:51<br/><br/>
 * <b>DESCRIPTION:</b><br/><br/>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br/>
 * Version Date: 2015-06-18 10:51<br/>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2015-06-18 10:51
 */

public class NavigationRec {
    
    private List<NavSectionRec> sections;
    private NavSectionRec       currentSection;

    public NavigationRec ( ) {
        this.sections       = new ArrayList<>   ( );

        this.currentSection = new NavSectionRec ( );
    }

    /**
     * @return the sections
     */
    public List<NavSectionRec> getSections() {
        return sections;
    }

    /**
     * Sets the sectios ofthe navigation to use. The first time around, it sets 
     * the current section to be the default section.
     * @param sections the sections to set
     */
    public void setSections ( List<NavSectionRec> sections ) {
        
        if ( ( sections != null ) && sections.size ( ) > 0 ) {
            this.setCurrentSection ( sections.get ( 0 ) );

//   SET THE CURRENT SECTION
            for ( int i = 0; i < sections.size ( ); i++ ) {
                NavSectionRec nsr = sections.get ( i );
                
                this.setCurrentCommandForSection ( nsr );
                
                if ( nsr.isDefault ( ) ) {
                    this.setCurrentSection ( nsr );

                }
            }
            
            if ( this.currentSection.getSectionCode ( ) == 0 ) {
                this.currentSection = sections.get ( 0 );
            }
            
        }

        this.sections = sections;

    }

    public void setCurrentCommandForSection ( NavSectionRec nsr ) {
        
//   SET THE CURRENT COMMAND
            
        List<NavCommandRec> l = nsr.getCommands ( );

        for ( int i = 0; i < l.size ( ); i++ ) {
            NavCommandRec ncr = l.get ( i );
            if ( ncr.isDefault ( ) ) {
                nsr.setCurrentCommand ( ncr );
            }
        }

        if ( nsr.getCurrentCommand ( ).getCommandCode ( ) == 0 ) {
            nsr.setCurrentCommand ( l.get ( 0 ) );
        }
    }
    
    /**
     * @return the currentSection
     */
    public NavSectionRec getCurrentSection ( ) {
        return currentSection;
    }

    /**
     * 
     * @param sectionName The name of the section to go to
     */
    public void navToSection ( String sectionName ) {
        
        for ( NavSectionRec nsr : sections ) {
            if ( nsr.getControllerCommand ( ).equals ( sectionName ) ) {
                this.setCurrentSection ( nsr );
            }
        }
    }

    /**
     * @param currentSection the currentSection to set
     */
    public void setCurrentSection ( NavSectionRec currentSection ) {
        this.currentSection = currentSection;
    }

    /**
     * 
     * @param commandName The name of the command to go to
     */
    public void navToCommand ( String commandName ) {
        
        List<NavCommandRec> lncr = this.getCurrentSection ( ).getCommands ( );
        
        for ( NavCommandRec ncr : lncr ) {
            if ( ncr.getControllerCommand ( ).equals ( commandName ) ) {
                this.setCurrentCommand ( ncr );
            }
        }
    }

    /**
     * @return the currentCommand
     */
    public NavCommandRec getCurrentCommand ( ) {
        return this.getCurrentSection ( ).getCurrentCommand ( );
    }
    
    /**
     * @param currentCommand the currentCommand to set
     */
    public void setCurrentCommand ( NavCommandRec currentCommand ) {
        this.getCurrentSection ( ).setCurrentCommand ( currentCommand );
    }

    /**
     * Checks whether the selected section is a valid choice for the current user 
     * @param navSection
     * @return true = is Valid; false = go fish!
     */
    public boolean isValidSectionForUser ( String navSection ) {

        boolean isValid = false;
        
        for (NavSectionRec section : sections) {
            if ( ( section.getControllerCommand ( ) ).equals ( navSection ) ) {
                isValid = true;
            }
        }
        
        return isValid;
    }

    /**
     * Checks whether the selected command is a valid choice for the current user 
     * @param navCommand
     * @return true = is Valid; false = go fish!
     */
    public boolean isValidCommandForUser ( String navCommand ) {

        boolean isValid = false;
        
        List<NavCommandRec> lncr = currentSection.getCommands ( );
        
        for (NavCommandRec ncr : lncr) {
            if ( ( ncr.getControllerCommand ( ) ).equals ( navCommand ) ) {
                isValid = true;
            }
        }
        
        return isValid;
    }

}
