$( function ( ) {
    var validations = new Array ( );                                            //   VALIDATIONS

    var loginForm   = $( "#loginForm" );

//   LOGIN DATA - BEGIN
    var loginAlert   = $( "#loginAlert" );
    var loginMessage = $( "#message"    );
    var userName     = $( "#username"   );
    var password     = $( "#passwd"     );
//   LOGIN DATA - END

    var loginBtn    = $( "#loginBtn"  );

//   EVENTS - BEGIN
    loginBtn.on ( "click",  processForm );
    //loginForm.on( "submit", processForm );
//   EVENTS - END

//    initForm ( );
//
//    function initForm ( ) {
//        setupValidations ( );                                                   //   VALIDATIONS
//    }

    function processForm ( ) { 

        var loginRec = { };

            loginRec.email  = userName.val ( );
            loginRec.passwd = password.val ( );

        var jsonLogin = JSON.stringify ( loginRec );
        
        $.ajax ( {        
            url:         "fiAPI/1.0/login",
            method:      "POST", 
            dataType:    "json",
            contentType: "application/json; charset=utf-8",
            data:        jsonLogin,
            success:function ( result ) {
                processFormResult ( result );
            },
            error:function ( xhr, status, error ) {
                alert ( "ERROR: " + error + " STATUS: " + status );
           }
        } );
    }

//   VALIDATIONS - BEGIN
    function processFormResult ( result ) { 

        if ( result.status.issueCode !== "" ) {
            loginMessage.html( result.status.issueMessage );
            loginAlert.show ( );
            //displayValidationIssues ( result );

        } else {
            window.location.replace ( result.navTo );        
        }
    }
//   VALIDATIONS - END

} );