$( function ( ) {
    var formCaption         = $( "#formCaptionLabel"    );
    var personForm          = $( "#personForm"          );
    var personCode          = $( "#personCode"          );

    var idType              = $( "#idType"              );
    var idDocNum            = $( "#idDocNum"            );
    var firstName           = $( "#firstName"           );
    var lastName            = $( "#lastName"            );

    var birthDate           = $( "#birthDate"           );

    var email               = $( "#email"               );
    var phone               = $( "#phone"               );

    var gender              = $( "#gender"              );
//   PERSONAL DATA - END    

//   ADDRESS THINGS - BEGIN
    var address             = $( "#address"             );
    var city                = $( "#city"                );
    var postCode            = $( "#postCode"            );
    var province            = $( "#province"            );
    var authonomy           = $( "#authonomy"           );
    var country             = $( "#country"             );
//   ADDRESS THINGS - END

    var cardNumber          = $( "#cardNumber"          );
    var applicationDateTime = $( "#applicationDateTime" );
    var printingDateTime    = $( "#printingDateTime"    );

    var receiveNews         = $( "#notifications"       );
    var acceptTerms         = $( "#terms"               );
    var acceptTermsLnk      = $( "#tAndC"               );

    var activeRadio         = $( "#clientIsActive"     );
    var notActiveRadio      = $( "#clientIsNotActive"  );

    var processFormButton   = $( "#btnProcessForm"     );

    processFormButton.on ( "click", processForm );

    initForm ( );

    function initForm ( ) {
        retrieveClient ( personCode.val ( ) );
    }

    function retrieveClient ( personCode ) {
        $.get( ( "fiClientAPI/1.0/ops/cli_persons/" + personCode ), fillForm );
    }

    function fillForm ( data ) {
//        formCaption.html       ( "Tarjeta: [" + data.cardNumber + "]" );
        personCode.val         ( data.personCode      );
        idType.val             ( data.personIdType    );
        idDocNum.val           ( data.personIdNumber  );
        firstName.val          ( data.personFirstName );
        lastName.val           ( data.personLastName  );
        birthDate.val          ( data.birthDate       );

        email.val              ( data.email           );
        phone.val              ( data.phone           );

        $( 'input:radio[name="gender"][value="' + data.genderCode + '"]' ).attr ( 'checked', true );

        country.val            ( data.country         );
        authonomy.val          ( data.authonomy       );
        province.val           ( data.province        );

        address.val            ( data.postalAddress    );
        city.val               ( data.city             );
        postCode.val           ( data.postCode         );

        $.each ( data.prefs, function ( ) {
            $( "#pref_" + this.preferenceCode ).prop ( "checked", true );
        } );

        if ( data.receiveNews ) {
            receiveNews.prop ( "checked", true );
        }
        
        if ( data.acceptTerms ) {
            acceptTerms.prop ( "checked", true );
        }

        cardNumber.html          ( ( data.cardNumber === "" ) ? "---" : data.cardNumber );
        applicationDateTime.html ( ( data.applicationDateTime === "01/01/1900 00:00" ) ? "---" : data.applicationDateTime );
        printingDateTime.html    ( ( data.printingDateTime === "01/01/1900 00:00" ) ? "---" : data.printingDateTime );

    }
    
    function processForm ( ) { }
   
    /*   ¿UTILITARIO?   */
    function viewportSize ( ) {
        var e = window;
        var a = 'inner';

        if ( !( 'innerWidth' in window ) ) {
            a = 'client';
            e = document.documentElement || document.body;
        }

        return { width : e [ a + 'Width' ] , height : e[ a + 'Height' ] };
    }

    /*   ¿UTILITARIO?   */
    function showMessage ( theAlert, message ) {
        
//        var miAlerta = $( "#fade_message" );
        
        var vps = viewportSize ( );
        var anchoAlerta  = theAlert.outerWidth ( true );
        var altoAlerta   = theAlert.outerHeight( true );

        theAlert.html ( message );
        theAlert.css ( "top", ( vps.height - altoAlerta ) / 2 );
        theAlert.css ( "left", ( vps.width - anchoAlerta ) / 2 );

        theAlert.fadeIn ( ).delay ( 2000 ).fadeOut ( );
    }

} );