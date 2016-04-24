package com.fxt.util;


//   standard libraries imports


//   third party libraries imports


//   FENIX Framework imports


//   application domain imports


/**
 * RandomPasswordGenerator.java<br/><br/>
 * Creada el Jun 10, 2011 3:43:44 PM<br/><br/>
 * <b>DESCRIPCION:</b><br/><br/>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   Historia   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br/>
 * Jun 10, 2011 3:43:44 PM<br/>
 *Paulo Márquez </td>
 *<td width="80%"><p>Creación</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 -  Jun 10, 2011 3:43:44 PM
 */
public class RandomPasswordGenerator {

    /** The random number generator. */
    protected static java.util.Random r = new java.util.Random ( );

    /*
     * Set of characters that is valid. Must be printable, memorable, and "won't
     * break HTML" (i.e., not ' <', '>', '&', '=', ...). or break shell commands
     * (i.e., not ' <', '>', '$', '!', ...). I, L and O are good to leave out,
     * as are numeric zero and one.
     */
    protected static char[] goodChar = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k',
                                         'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
                                         'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
                                         'H', 'J', 'K', 'M', 'N', 'P', 'Q', 'R', 'S', 'T',
                                         'U', 'V', 'W', 'X', 'Y', 'Z', '2', '3', '4', '5',
                                         '6', '7', '8', '9' };

    /* Generate a Password object with a random password. */
    public static String getPassword ( int minLength ) {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < minLength; i++) {
            sb.append ( goodChar [ r.nextInt ( goodChar.length ) ] );
        }

        return sb.toString ( );
    }

    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//        for ( int i = 0; i < 20; i++ ) {
//            System.out.println ( RandomPasswordGenerator.getPassword ( 10 ) );
//        }
//    }

}

