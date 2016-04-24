
package com.fxt.util.file;

//   Standard Libraries Imports
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

//   Third Party Libraries Imports


//   FENIX Framework Imports


//   Application Domain Imports


/**
 * FileUtils.java<br/><br/>
 * Creation Date 2015-05-30 16:36<br/><br/>
 * <b>DESCRIPTION:</b><br/><br/>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br/>
 * Version Date: 2015-05-30 16:36<br/>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2015-05-30 16:36
 */
public class FileUtils {

    
    List<String> fileList;
    
//   GET RID OF THESE!!! - BEGIN
//    private static final String INPUT_LOG_FILE = "log.txt";
    private static final String OUTPUT_FOLDER  = "templog";
//   GET RID OF THESE!!! - END
    
    public static final int     DATE           = 1;
    public static final int     DATE_TIME      = 2;

    private static final int    BUFFER         = 2048;   //   Bytes

    /**
     * 
     * @param filePath
     * @param data 
     */
    public static void appendDataToFile ( String filePath, String data ) {
        
        BufferedWriter bw = null;

        try {
             // APPEND MODE SET HERE
            bw = new BufferedWriter ( new FileWriter ( filePath, true ) );
            bw.write ( data );
            bw.newLine ( );
            bw.flush ( );

        } catch ( IOException ioe ) {
            Logger.getLogger ( FileUtils.class.getName ( ) ).log ( Level.SEVERE, null, ioe );
            
        } finally { // always close the file
            if ( bw != null ) { 
                try {
                    bw.close ( );
            
                } catch ( IOException ioe2 ) {
                    Logger.getLogger ( FileUtils.class.getName ( ) ).log ( Level.SEVERE, null, ioe2 );

                }
            }
            
        } // end try/catch/finally

    }

    /**
     * Creates a new filename for the purpose of log files creation in the format <prefix>YYYYMMDD<suffix> or <prefix>YYYYMMDD_HH_mm_ss<suffix> 
     * @param type
     * @param suffix
     * @return 
     */
    public static String createFileName ( String prefix, int type, String suffix ) {
        
        String filename = "file.txt";
        
        switch ( type ) {
            case DATE:
                filename = prefix + FileUtils.getDateAsFileNameString ( ) + suffix;
                break;
                
            case DATE_TIME:
                filename = prefix + FileUtils.getDateTimeAsFileNameString ( ) + suffix;
                break;
                
            default:
                break;
                
        }

        return filename;
        
    }
    
    /**
     * Returns a String with the date, suitable for file names in the format "YYYYMMDD"
     * @return 
     */
    public static String getDateAsFileNameString ( ) {
        
        String date = "19000101";
        
        LocalDate ld = LocalDate.now ( );
        
        date = ld.getYear ( ) + "" + 
               ( ( ld.getMonthValue ( ) < 10 ) ?  "0" + ld.getMonthValue ( ) : ld.getMonthValue ( ) ) + 
               ( ( ld.getDayOfMonth ( ) < 10 ) ?  "0" + ld.getDayOfMonth ( ) : ld.getDayOfMonth ( ) );
        
        return date;
    }
    
    /**
     * Returns a String with the date, suitable for file names in the format "YYYYMMDD_HH_mm_ss"
     * @return 
     */
    public static String getDateTimeAsFileNameString ( ) {
        
        LocalDateTime ld = LocalDateTime.now ( );
        
        String date = ld.getYear ( ) + "" + 
                      ( ( ld.getMonthValue ( ) < 10 ) ?  "0" + ld.getMonthValue ( ) : ld.getMonthValue ( ) ) + 
                      ( ( ld.getDayOfMonth ( ) < 10 ) ?  "0" + ld.getDayOfMonth ( ) : ld.getDayOfMonth ( ) ) + "_" +
                      ( ( ld.getHour       ( ) < 10 ) ?  "0" + ld.getHour       ( ) : ld.getHour       ( ) ) + "_" +
                      ( ( ld.getMinute     ( ) < 10 ) ?  "0" + ld.getMinute     ( ) : ld.getMinute     ( ) ) + "_" +
                      ( ( ld.getSecond     ( ) < 10 ) ?  "0" + ld.getSecond     ( ) : ld.getSecond     ( ) );
        
        return date;
    }
    
    /**
     * 
     * @param inputFilePath 
     */
    public static void validateZipCRC32 ( String inputFilePath ) {
        BufferedInputStream bis = null; 
        
        try {
            bis = new BufferedInputStream ( new FileInputStream ( new File ( inputFilePath ) ) );
            
            int read;
            
            CRC32 checksum = new CRC32 ( );
            
            byte [ ] buffer = new byte [ FileUtils.BUFFER ];
            
            while ( ( read = bis.read ( buffer ) ) != -1 ) {
                checksum.update ( buffer, 0, read );
            }   
        
            bis.close ( );
            System.out.println ( "CRC32 of your zip is: " + checksum.getValue ( ) );
        
        } catch ( FileNotFoundException ex ) {
            Logger.getLogger ( FileUtils.class.getName ( ) ).log ( Level.SEVERE, null, ex );
        
        } catch ( IOException ex ) {
            Logger.getLogger ( FileUtils.class.getName ( ) ).log ( Level.SEVERE, null, ex );
            
        } finally {
            try {
                if ( bis != null ) { 
                    bis.close ( );
                }

            } catch ( IOException ex ) {
                Logger.getLogger ( FileUtils.class.getName ( ) ).log ( Level.SEVERE, null, ex );
            
            }
        }
    }
    
    /**
     * Zips a file located in inputFilePath, outputs it to outputZipPath with outputFileName
     * @param inputFilePath
     * @param outputZipPath 
     * @param outputFileName 
     */
    public static void zipIt ( String inputFilePath, String outputZipPath, String outputFileName ) {
        
        try {
            BufferedInputStream origin = null;
            
            FileOutputStream dest = new FileOutputStream ( outputZipPath );
            ZipOutputStream  out  = new ZipOutputStream  ( new BufferedOutputStream ( dest ) );
            
            byte data [ ] = new byte [ FileUtils.BUFFER ];

            File f = new File ( inputFilePath );

            FileInputStream fi = new FileInputStream ( f );
                
            origin = new BufferedInputStream ( fi, FileUtils.BUFFER );
            ZipEntry entry = new ZipEntry ( outputFileName );
                
            out.putNextEntry ( entry );
                
            int count;
                
            while ( ( count = origin.read(data, 0, BUFFER ) ) != -1 ) {
                out.write ( data, 0, count );
            }
                
            origin.close ( );
            out.close ( );
            
        } catch ( Exception ex ) {
            Logger.getLogger ( FileUtils.class.getName ( ) ).log ( Level.SEVERE, null, ex );
        
        }
    }

    /**
     * 
     * @param inputFilePath
     * @param outputZipPath 
     */
    public static void zipItCRC32 ( String inputFilePath, String outputZipPath ) {
        
        try {
            BufferedInputStream origin = null;

            FileOutputStream dest = new FileOutputStream ( outputZipPath );
            
            CheckedOutputStream checksum = new CheckedOutputStream ( dest, new CRC32 ( ) );
            ZipOutputStream out = new ZipOutputStream ( new BufferedOutputStream ( checksum ) );
        
            //out.setMethod(ZipOutputStream.DEFLATED);
            byte data [ ] = new byte [ FileUtils.BUFFER ];
            
            //   get a list of files from current directory
            File f = new File ( "C:/PROJECTS_ROOT/nexglobal/nexadsPi/src/main/webapp/json" );
            String files [ ] = f.list ( );

            for ( int i = 0; i < files.length; i++ ) {
                
System.out.println ( "Adding: " + files [ i ] );
System.out.println ( "Adding: " + f.getAbsolutePath ( ) + File.separator + files [ i ] );
                
                FileInputStream fi = new FileInputStream ( f.getAbsolutePath ( ) + File.separator + files [ i ] );
                
                origin = new BufferedInputStream ( fi, FileUtils.BUFFER );
                
                ZipEntry entry = new ZipEntry ( files [ i ] );
                
                out.putNextEntry ( entry );
                
                int count;
                
                while ( ( count = origin.read ( data, 0, FileUtils.BUFFER ) ) != -1 ) {
                    out.write ( data, 0, count );
                }
                origin.close ( );
            }
        
            out.close ( );
            System.out.println ( "checksum: " + checksum.getChecksum ( ).getValue ( ) );
 
        } catch ( Exception ex ) {
            Logger.getLogger ( FileUtils.class.getName ( ) ).log ( Level.SEVERE, null, ex );
        }
    }

    /**
     * 
     * @param zipFile
     * @param outputFolder 
     */
    public static String unZipIt ( String zipFile, String outputFolder ){
        
        String filePath = "";
        
        byte [ ] buffer = new byte [ FileUtils.BUFFER ];

        try {

            //create output directory is not exists
            File folder = new File ( OUTPUT_FOLDER );

            if ( !folder.exists ( ) ){
                folder.mkdir ( );
            }

            //get the zipped file list entry
            try ( 
                //get the zip file content
                ZipInputStream zis = new ZipInputStream ( new FileInputStream ( zipFile ) ) ) {
                
                //get the zipped file list entry
                ZipEntry ze = zis.getNextEntry ( );

                while( ze != null ){

                    String fileName = ze.getName ( );
                    filePath = outputFolder + File.separator + fileName;
                    File newFile = new File ( filePath );

                    new File ( newFile.getParent ( ) ).mkdirs ( );

                    FileOutputStream fos = new FileOutputStream ( newFile );

                    int len;
                    while ( ( len = zis.read ( buffer ) ) > 0 ) {
                        fos.write ( buffer, 0, len );
                    }

                    fos.close ( );
                    ze = zis.getNextEntry ( );
                }

                zis.closeEntry ( );
           }

//           System.out.println ( "Done" );

        } catch ( IOException ex ){
            
            filePath = "ERROR";
            
            Logger.getLogger ( FileUtils.class.getName ( ) ).log ( Level.SEVERE, null, ex );
       
        }
        
        return filePath;

    }    

} // end class
