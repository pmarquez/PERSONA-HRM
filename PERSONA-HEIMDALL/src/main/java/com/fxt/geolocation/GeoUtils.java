
package com.fxt.geolocation;

/**
 * GeoUtils.java<br/><br/>
 * Creation Date 2014-07-20 19:01<br/><br/>
 * <b>DESCRIPTION:</b><br/><br/>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br/>
 * Version Date: 2014-07-20 19:01<br/>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2014-07-20 19:01
 */
public class GeoUtils {
    
//   Geofences - BEGIN
    
    public static final double METRES_IN_KILOMETER = 1000.0;
    
    public static final double ELLIPTICITY_OF_EARTH             = 0.00335282;
    
    public static final double RADIUS_AT_EQUATOR                = 6378.137;     //   Km.
    public static final double RADIUS_AT_POLE                   = 6356.752314;  //   Km.

    public static final double CIRCUMFERENCE_AT_EQUATOR         = 40075.01669;  //   Km.
    public static final double CIRCUMFERENCE_AT_POLE            = 39940.65274;  //   Km.
    
    public static final double KILOMETERS_PER_DEGREE_AT_EQUATOR = 111.3194908;  //   Km.
    public static final double KILOMETERS_PER_DEGREE_AT_POLE    = 111.3194908;  //   Km.

//   Geofences - END

    public static double transformToDecimalCoordinate ( int degrees, int minutes, double seconds ) {

        double transformedCoordinate = 0.0;

        transformedCoordinate = ( ( degrees * 3600 ) + ( minutes * 60.0 ) + ( seconds ) ) / 3600.0;
    
        return transformedCoordinate;
        
    }
    
    public static boolean isVenueInCircularGeofence ( LocationRec center, 
                                                double      radius, 
                                                LocationRec venueMarker ) {
        
        double deltaLat  = Math.abs  ( center.getLatitude  ( ) - venueMarker.getLatitude  ( ) );
        double deltaLong = Math.abs  ( center.getLongitude ( ) - venueMarker.getLongitude ( ) );
        double distance  = Math.sqrt ( Math.pow ( deltaLat, 2 ) + Math.pow ( deltaLong, 2 ) );
        
        return ( distance <= radius );
    }

    public static boolean isVenueInRectangularGeofence ( LocationRec center, 
                                                  double      radius, 
                                                  LocationRec venueMarker ) {
        
        boolean inVertically   = ( ( venueMarker.getLatitude  ( ) >= center.getLatitude  ( ) - radius ) && ( venueMarker.getLatitude  ( ) <= center.getLatitude  ( ) + radius ) );
        boolean inHorizontally = ( ( venueMarker.getLongitude ( ) >= center.getLongitude ( ) - radius ) && ( venueMarker.getLongitude ( ) <= center.getLongitude ( ) + radius ) );
        
        return ( inVertically && inHorizontally );
    }

}
