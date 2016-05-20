package com.fxt.validations;

//   Standard Libraries Imports
import java.time.LocalDate;

//   Third Party Libraries Imports
import com.aeat.valida.Validador;
import org.apache.commons.validator.routines.EmailValidator;

//   FENIX Framework Imports
import com.fxt.address.PostCodeRulesRec;

//   Domain Imports


/**
 * BaseValidators.java<br><br>
 * Creation Date 2015-07-20 11:43<br><br>
 * <b>DESCRIPTION:</b><br><br>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br>
 * Version Date: 2015-07-20 11:43<br>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2015-07-20 11:43
 */
public class BaseValidators {
    
    /**
     * Validates that a String is nos null or empty.
     * @param s The String to validate
     * @return false if the String is empty or null; true if the String is notempty or null.
     */
    public static boolean notEmpty ( String s ) {
        boolean validate = true;
        
        if ( s == null ) {
            validate = false;
        } else {
            if ( ! ( s.length ( ) > 0 ) ) {
                validate = false;
            }
        }
        
        return validate;
    }
    
    /**
     * Validates that a String is not null and that its lengthis within the given range.
     * @param s The String to Validate
     * @param min Min allowed String length.
     * @param max Max allowed String length.
     * @return false is the String length is out of range or is null; true ifthe String length is whitin range.
     */
    public static boolean validateLength ( String s, int min, int max ) {
        boolean validate = true;
        
        if ( s == null ) {
            validate = false;
        } else {
            validate = ( ( s.length ( ) >= min ) && ( s.length ( ) <= max ) );
        }
        
        return validate;
    }

    /**
     * Validates that a String contains only numbers.
     * @param s The String to validate
     * @return false if the string contains characters out of the [0..9] range; true otherwise.
     */
    public static boolean validateOnlyNumbers ( String s ) {
        boolean isIt = false;

        if ( s != null ) {
            if ( s.length ( ) > 0 ) {
                isIt = true;
                for (int i = 0; i < s.length ( ); i++ ) {
                    if ( !( Character.isDigit ( s.charAt ( i ) ) ) ) {
                        isIt = false;
                    }
                }
            }
        }
        return isIt;
    }

    /**
     * Validates whether a number is within a given range.
     * @param value
     * @param min
     * @param max
     * @return true if within range; false otherwise
     */
    public static boolean numberInRange ( int value,
                                          int min,
                                          int max ) {

        return ( ( value >= min ) && ( value <= max ) );
    }

    /**
     * Validates that an email is well constructed.
     * @param email
     * @return true if valid email; false otherwise
     */
    public static boolean isValidEmail ( String email ) {
        
        EmailValidator emailValidator = EmailValidator.getInstance ( );

        return emailValidator.isValid ( email );
    }

    public static boolean isValidPhoneNumber ( String phone, String locale ) {

        boolean isValid = false;

        String digits = phone.replaceAll ( "[^0-9]", "" );

        switch ( locale ) {
            case "es-ES":
                isValid = ( digits.length ( ) == 9 );
                break;

            case "en-GB":
                isValid = true;
                break;

            case "de-DE":
                isValid = true;
                break;

            default:
                isValid = true;
                break;
        }
        
        return isValid;

    }
    
    /**
     * validaDocIDES - Validates a Spanish DNI|NIE|NIF|CIF is Valid
     * @param docNumber dni|nie|nif
     * @return boolean
     */
    public static boolean validateIdDocumentES ( String docNumber ) {
        Validador val = new Validador ( );
        return ( val.checkNif ( docNumber ) > 0 );
    }
    
    /**
     * validaDocIDES - Performs date Sanity Check (NO February 31st and such)
     * @param year
     * @param month
     * @param day
     * @return boolean
     */
    public static boolean isValidDate ( int year, int month, int day ) {
        
        boolean isValid = false;
        
        try {
            LocalDate birthDate = LocalDate.of ( year, month, day );
            isValid = true;
        } catch ( java.time.DateTimeException e ) {
            System.out.println ( e.getMessage ( ) );
        }
            
        return isValid;
    }

    /**
     * Validatea a Postal Code string adheres to a specific pattern dictated by a Country-Province combination
     * @param postCode
     * @param pcrr
     * @return 
     */
    //TODO Make the comparisson MULTI-PATTERN PostCodePatterns may contain more than one...keep an eye on this.
    public static boolean validatePostalCode ( String postCode, PostCodeRulesRec pcrr ) {
        return ( postCode.matches ( pcrr.getPostCodePatterns ( ) ) && 
                 postCode.subSequence ( 0, pcrr.getPostCodePrefix ( ).length ( ) ).equals ( pcrr.getPostCodePrefix ( ) ) );
    }

}
