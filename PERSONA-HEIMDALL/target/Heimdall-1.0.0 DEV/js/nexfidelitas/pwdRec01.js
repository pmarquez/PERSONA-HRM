$( function ( ) {
    var pwdRec01Form   = $( "#pwdRec01Form" );

//   PWD REG DATA - BEGIN
    var pwdRecAlert    = $( "#pwdRecAlert"    );
    var message        = $( "#message"        );
    var messageCaption = $( "#messageCaption" );
    var email          = $( "#email"          );
    var emailHolder    = $( "#emailHolder"    );
//   PWD REG DATA - END

    var processBtn     = $( "#processBtn"     );

//   EVENTS - BEGIN
    processBtn.on ( "click", processForm );
//   EVENTS - END

    function processForm ( ) { 

        var pwdRecoveryRec = { };

        pwdRecoveryRec.email  = email.val ( );

        var jsonLogin = JSON.stringify ( pwdRecoveryRec );

        $.ajax ( {        
            url:         "fiAPI/1.0/rec/pwdRecovery",
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
        if ( processBtn.html ( ) === "Iniciar Proceso" ) {
            if ( result.messageType === 0 ) {
                var messages = (result.message).split ( "#" );
                messageCaption.html( messages [ 0 ] );
                message.html( messages [ 1 ] );
                pwdRecAlert.removeClass ( "alert-info"  );
                pwdRecAlert.removeClass ( "alert-danger" );
                pwdRecAlert.addClass    ( "alert-success" );

                emailHolder.hide ( );
                processBtn.html  ( "Regresar" );

    //            loginAlert.show ( );
                //displayValidationIssues ( result );
    //            window.location.replace ( result.navTo );        
            } else if ( result.messageType === 1 ) {
                var messages = (result.message).split ( "#" );
                messageCaption.html( messages [ 0 ] );
                message.html( messages [ 1 ] );
                pwdRecAlert.removeClass ( "alert-info"    );
                pwdRecAlert.removeClass ( "alert-success" );
                pwdRecAlert.addClass    ( "alert-danger"   );
            }
            
        } else if ( processBtn.html ( ) === "Regresar" ) {
            window.location.replace ( "landing" );
        }

    }
//   VALIDATIONS - END

} );