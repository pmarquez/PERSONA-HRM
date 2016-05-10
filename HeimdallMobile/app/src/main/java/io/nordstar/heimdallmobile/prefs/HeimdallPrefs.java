
package io.nordstar.heimdallmobile.prefs;

/**
 * Defines app-wide constants and utilities
 * Created by pmarquez on 04/05/2016.
 */
public class HeimdallPrefs {




    // BASE_URI_CLOUD_SERVICE
//    public static final String BASE_URI = "http://localhost:8084/Heimdall";
    public static final String BASE_URI = "http://192.168.1.68:8084/Heimdall";

    //   API CALLS
    public static final String LOGIN_USER     = "/heimdallAPI/1.0/login/login";
    public static final String USER_EVENT     = "/heimdallAPI/1.0/events/events";

    //   SHARED PREFERENCES
    public static final String heimdallPrefs      = "HEIMDALL_PREFS" ;
    public static final String userCode           = "USER_CODE";
    public static final String userFirstName      = "USER_FIRST_NAME";
    public static final String userLastName       = "USER_LAST_NAME";
    public static final String authorizationToken = "AUTHORIZATION_TOKEN";

}
