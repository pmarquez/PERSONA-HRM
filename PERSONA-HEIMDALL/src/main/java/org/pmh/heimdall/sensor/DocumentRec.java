
package org.pmh.heimdall.sensor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author pmarquez - 2016-04-17 23:13
 */
public class DocumentRec {
    private int           documentCode;
    private String        UUID;
    private String        documentName;
    private String        displayName;
    private String        base64Representation;
    private LocalDateTime timestamp;

    public DocumentRec ( ) {
        this.documentCode         = 0;
        this.UUID                 = "";
        this.documentName         = "";
        this.displayName          = "";
        this.base64Representation = "";
        this.timestamp            = LocalDateTime.now();
    }

    /**
     * @return the documentCode
     */
    public int getDocumentCode() {
        return documentCode;
    }

    /**
     * @param documentCode the documentCode to set
     */
    public void setDocumentCode(int documentCode) {
        this.documentCode = documentCode;
    }

    /**
     * @return the UUID
     */
    public String getUUID() {
        return UUID;
    }

    /**
     * @param UUID the UUID to set
     */
    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    /**
     * @return the documentName
     */
    public String getDocumentName() {
        return documentName;
    }

    /**
     * @param documentName the documentName to set
     */
    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    /**
     * @return the displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @param displayName the displayName to set
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * @return the base64Representation
     */
    public String getBase64Representation() {
        return base64Representation;
    }

    /**
     * @param base64Representation the base64Representation to set
     */
    public void setBase64Representation(String base64Representation) {
        this.base64Representation = base64Representation;
    }

    /**
     * @return the opTimestamp
     */
    @JsonIgnore
    public String getTimestampDate ( ) {
        return timestamp.format ( DateTimeFormatter.ofPattern ( "dd-MM-yyyy" ) );
    }

    @JsonIgnore
    public String getTimestampTime ( ) {
        return timestamp.format ( DateTimeFormatter.ofPattern ( "HH:mm:ss" ) );
    }

    public String getTimestamp ( ) {
        return timestamp.format ( DateTimeFormatter.ofPattern ( "yyyy-MM-dd HH:mm:ss" ) );
    }

    @JsonIgnore
    public LocalDateTime getTimestampLDT ( ) {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
//    @JsonIgnore
    public void setTimestamp ( String timestamp ) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern ( "yyyy-MM-dd HH:mm:ss" );
        try {
            this.timestamp = LocalDateTime.parse ( timestamp, dtf );
        } catch ( DateTimeParseException dtpe ) {            
            this.timestamp = LocalDateTime.MIN;
        }
    }

    @JsonIgnore
    public void setDateRequested ( LocalDateTime timestamp ) {
        this.timestamp = timestamp;
    }


    /**
     * Utility method that dumps the contents of a DocumentRec for trace and debugging purposes.
     */
    public void spillYourGuts ( ) {
        System.out.println ( "************************************************************************" );
        System.out.println ( "**** DocumentRec *******************************************************" );
        System.out.println ( "************************************************************************" );

        System.out.println ( "**** Document Name: " + this.getDocumentName         ( ) );
        System.out.println ( "**** Display Name : " + this.getDisplayName          ( ) );
        System.out.println ( "**** UUID         : " + this.getUUID                 ( ) );
        System.out.println ( "**** Timestamp    : " + this.getTimestamp            ( ) );
        System.out.println ( "**** Base65 Size  : " + this.getBase64Representation ( ).length ( ) );

        System.out.println ( "************************************************************************" );
        System.out.println ( "************************************************************************" );
        System.out.println ( "************************************************************************" );
    } 
    
}
