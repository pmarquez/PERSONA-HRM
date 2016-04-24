
package org.pmh.heimdall.controller;


//   Standard Libraries Imports
import java.nio.file.Paths;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import javax.servlet.MultipartConfigElement;

//   Third Party Libraries Imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

//   FENIX Framework Imports


//   Domain Imports


/**
 * AuthController.java<br><br>
 * Creation Date 2015-05-21 08:39<br><br>
 * <b>DESCRIPTION:</b><br><br>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br>
 * Version Date: 2015-05-21 08:39<br>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2015-05-21 08:39
 */
@Controller
public class AuthController {

    @Autowired
    private DataSource ds;

  // The Environment object will be used to read parameters from the application.properties configuration file
  @Autowired
  private MultipartConfigElement mce;

    
    @RequestMapping ( value = "/", method = RequestMethod.GET )
    public String homeSimple ( HttpServletRequest request ) {
        HttpSession session = request.getSession ( );
        
        return "landing";
    }
    
    @RequestMapping ( value = "/signout", method = RequestMethod.GET )
    public String signOut ( HttpServletRequest request ) {
        HttpSession session = request.getSession ( );
        session.invalidate ( );
        
        return "landing";
    }

  /**
   * POST /uploadFile -> receive and locally save a file.
   * 
   * @param uploadfile The uploaded file as Multipart file parameter in the 
   * HTTP request. The RequestParam name must be the same of the attribute 
   * "name" in the input tag with type file.
   * 
   * @return An http OK status in case of success, an http 4xx status in case 
   * of errors.
   */
  @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> uploadFile( @RequestParam ( "uploadfile" ) MultipartFile uploadfile ) {
    
    try {
      // Get the filename and build the local file path
      String filename = uploadfile.getOriginalFilename ( );
      String directory = mce.getLocation ( );
      String filepath = Paths.get ( directory, filename ).toString ( );
      
      // Save the file locally
      BufferedOutputStream stream = new BufferedOutputStream ( new FileOutputStream ( new File ( filepath ) ) );
      stream.write(uploadfile.getBytes());
      stream.close();
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    return new ResponseEntity<>(HttpStatus.OK);
  } // method uploadFile

}
