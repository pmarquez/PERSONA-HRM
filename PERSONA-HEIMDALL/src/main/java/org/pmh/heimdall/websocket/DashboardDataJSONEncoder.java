
package org.pmh.heimdall.websocket;

import java.util.List;
import org.pmh.heimdall.process.DashboardElement;

/**
 *
 * @author pmarquez - 2016-05-23 17:27
 */
public class DashboardDataJSONEncoder {

    public static String encode ( List<DashboardElement> data ) {

        String dashboardElements = "";

        for ( int i = 0; i < data.size ( ); i++ ) {

            DashboardElement de = data.get ( i );

            dashboardElements += "{\"item\":" + " \"" + de.getItem ( ) + "\",";

            String dudr = "";
            for ( int j = 0; j < de.getData ( ).size ( ); j++ ) {
                dudr += "{\"hour\": \""      + de.getData ( ).get( j ).getHour       ( ) +  "\",";
                dudr += "\"sensorName\": \"" + de.getData ( ).get( j ).getSensorName ( ) +  "\",";
                dudr += "\"useCount\": \""   + de.getData ( ).get( j ).getUseCount   ( ) +  "\"},";
            }

            dashboardElements += "\"data\": [" + ( dudr.substring ( 0, dudr.length ( ) - 1 ) ) + " ]},";
        }

        dashboardElements = "{\"dashboard\": [" + ( dashboardElements.substring ( 0, dashboardElements.length ( ) - 1 ) ) + "]}";

        return dashboardElements;

    }
       
}
