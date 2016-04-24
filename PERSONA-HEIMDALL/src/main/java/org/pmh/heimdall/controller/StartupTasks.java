package org.pmh.heimdall.controller;

//   Standard Libraries Imports
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

//   Third Party Libraries Imports
import com.pi4j.system.SystemInfo;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

//   FENIX Framework Imports

//   Application Domain Imports


/**
 * StartupTasks.java<br/><br/>
 * Creation Date 2015-06-02 15:01<br/><br/>
 * <b>DESCRIPTION:</b><br/><br/>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br/>
 * Version Date: 2015-06-02 15:01<br/>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2015-06-02 15:01
 */
@Component
public class StartupTasks implements ApplicationListener<ContextRefreshedEvent> {

  /*
   * This method is called during Spring's startup.
   * 
   * @param event Event raised when an ApplicationContext gets initialized or
   * refreshed.
   */
  @Override
  public void onApplicationEvent ( final ContextRefreshedEvent event ) {
      System.out.println ( "Startup Tasks go here." );
  }
 
} // class StartupTasks    
