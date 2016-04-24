package com.fxt.util;

import java.awt.Color;

/**
 *
 * @author pmarquez - 2013/08/09 09:49
 */
public class ColorUtil {
    private static final int TRENDY_STUPID_RGB_FORMAT = 3;
    private static final int LEGAL_CORRECT_RGB_FORMAT = 6;
    
    public static Color transformRGB2JavaColor ( String rgb ) {
        
        Color color = null;
        
        String rHex = "88";
        String gHex = "88";
        String bHex = "88";
        
        String myRGB = ( rgb.startsWith("#") ) ? rgb.substring ( 1, rgb.length ( ) ) : rgb;
        
        if ( myRGB.length ( ) == ColorUtil.TRENDY_STUPID_RGB_FORMAT ) {
            rHex = myRGB.substring ( 0, 1) + myRGB.substring ( 0, 1);
            gHex = myRGB.substring ( 1, 2) + myRGB.substring ( 1, 2);
            bHex = myRGB.substring ( 2, 3) + myRGB.substring ( 2, 3);
            
        } else if ( myRGB.length ( ) == ColorUtil.LEGAL_CORRECT_RGB_FORMAT ) {
            rHex = myRGB.substring ( 0, 2);
            gHex = myRGB.substring ( 2, 4);
            bHex = myRGB.substring ( 4, 6);
            
        }
        
        int rInt = Integer.parseInt ( rHex, 16);
        int gInt = Integer.parseInt ( gHex, 16);
        int bInt = Integer.parseInt ( bHex, 16);

//        int decimal = Integer.parseInt("ff", 16);
//        System.out.println("Hex value is " + decimal);

//        String hex = Integer.toHexString(255);
//        System.out.println("Hex value is " + hex);
        
        color = new Color ( rInt, gInt, bInt );
        System.out.println("color.r: " + color.getRed ( ) );
        System.out.println("color.g: " + color.getGreen ( ) );
        System.out.println("color.b: " + color.getBlue ( ) );
        return color;
    }

}
