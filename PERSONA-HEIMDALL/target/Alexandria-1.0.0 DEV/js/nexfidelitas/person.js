$( function ( ) {
    var validations         = new Array ( );                                    //   VALIDATIONS

    var q                   = $( "#q"                  );
    var formCaption         = $( "#formCaptionLabel"   );
    var personForm          = $( "#personForm"         );

    var termsModal          = $( "#termsModal"         );

//   PERSONAL DATA - BEGIN    
    var personCode          = $( "#personCode"         );
    var personFirstName     = $( "#firstName"          );
    var personLastName      = $( "#lastName"           );
    var birthDay            = $( "#birthDay"           );
    var birthMonth          = $( "#birthMonth"         );
    var birthYear           = $( "#birthYear"          );

    var idTypeCode          = $( "#idTypeCode"         );
    var idDocNumber         = $( "#idDocNum"           );
    var email               = $( "#email"              );
    var phone               = $( "#phone"              );
    var gender              = $( "#gender"             );
//   PERSONAL DATA - END    

//   ADDRESS THINGS - BEGIN
    var countryDropdown     = $( "#countryDropdown"    );
    var authonomyDropdown   = $( "#authonomyDropdown"  );
    var provinceDropdown    = $( "#provinceDropdown"   );

    var addressGroup        = $( "#addressGroup"       );

    var countryCode         = $( "#countryCode"        );
    var authonomyCode       = $( "#authonomyCode"      );
    var provinceCode        = $( "#provinceCode"       );

    var address             = $( "#address"            );
    var city                = $( "#city"               );
    var postCode            = $( "#postCode"           );
//   ADDRESS THINGS - END

    var receiveNews         = $( "#notifications"      );
    var acceptTerms         = $( "#terms"              );
    var acceptTermsLnk      = $( "#tAndC"              );

    var processFormButton   = $( "#btnProcessForm"     );

//   EVENTS - BEGIN
    processFormButton.on ( "click", processForm      );
    acceptTermsLnk.on    ( "click", toggleTermsModal );
    
    countryCode.on   ( "change", processCountryChange   );
    authonomyCode.on ( "change", processAuthonomyChange );
    provinceCode.on  ( "change", processProvinceChange  );
    
//   EVENTS - END

    initForm ( );

    function initForm ( ) {
        retrievePerson ( personCode.val ( ) );
        setupValidations ( );                                                   //   VALIDATIONS
        
    }

    function retrievePerson ( personCode ) {
        $.get ( ( "fiAPI/0.0/ops/persons/"+ q.val ( ) + "/" + personCode ), fillForm );
    }

    function fillForm ( data ) {
        
        personCode.val           ( data.personCode       );
        idTypeCode.val           ( data.personIdTypeCode );
        idDocNumber.val          ( data.personIdNumber   );
        personFirstName.val      ( data.personFirstName  );
        personLastName.val       ( data.personLastName   );
        birthDay.val           ( ( data.birthDay   !== 0 ) ? data.birthDay   : 0 );
        birthMonth.val         ( ( data.birthMonth !== 0 ) ? data.birthMonth : 0 );
        birthYear.val          ( ( data.birthYear  !== 0 ) ? data.birthYear  : 0 );
        email.val                ( data.email            );
        phone.val                ( data.phone            );
        $( 'input:radio[name="gender"][value="' + data.genderCode + '"]' ).attr ( 'checked', true );

        var countryCodeValue = ( data.countryCode   !== 0 ) ? data.countryCode   : 64;
        data.countryCode = countryCodeValue;
//        countryCode.val        ( ( data.countryCode   !== 0 ) ? data.countryCode   : 0 );

        var authonomyCodeValue = ( data.authonomyCode   !== 0 ) ? data.authonomyCode   : 13;
        data.authonomyCode = authonomyCodeValue;
//        authonomyCode.val      ( ( data.authonomyCode !== 0 ) ? data.authonomyCode : 0 );

        var provinceCodeValue = ( data.provinceCode   !== 0 ) ? data.provinceCode   : 44;
        data.provinceCode = provinceCodeValue;
//        provinceCode.val       ( ( data.provinceCode  !== 0 ) ? data.provinceCode  : 0 );

        address.val            ( data.postalAddress    );
        city.val               ( data.city             );
        postCode.val           ( data.postCode         );
        
        $.each ( data.prefs, function ( ) {
            $( "#pref_" + this.preferenceCode ).prop ( "checked", true );
        } );

        setupAddressVisibility ( data );

    }

    function processForm ( ) { 
    
        var personRec = { };

            personRec.personCode       = personCode.val         ( );
            personRec.personIdTypeCode = idTypeCode.val         ( );
            personRec.personIdNumber   = idDocNumber.val        ( );
            personRec.personFirstName = personFirstName.val     ( );
            personRec.personLastName  = personLastName.val      ( );
            personRec.birthDay        = birthDay.val            ( );
            personRec.birthMonth      = birthMonth.val          ( );
            personRec.birthYear       = birthYear.val           ( );
            personRec.email           = email.val               ( );
            personRec.phone           = phone.val               ( );
            personRec.genderCode      = $('input:radio[name="gender"]:checked').val ( ) ;
            personRec.countryCode     = countryCode.val         ( );
            personRec.authonomyCode   = authonomyCode.val       ( );
            personRec.provinceCode    = provinceCode.val        ( );
            personRec.postalAddress   = address.val             ( );
            personRec.city            = city.val                ( );
            personRec.postCode        = postCode.val            ( );
            personRec.receiveNews     = receiveNews.is ( ":checked" );
            personRec.acceptTerms     = acceptTerms.is ( ":checked" );

        var preferences = new Array ( );
        $( 'input[name="preferences"]:checked' ).each ( function ( ) {
            var preference = { };
            preference.preferenceCode = $( this ).val ( );
            preferences.push ( preference );
        } );
        personRec.prefs = preferences;

        var jsonPerson = JSON.stringify ( personRec );
        
//        alert ( jsonPerson );
        
        $.ajax ( { 
            url:         "fiAPI/0.0/ops/persons/"+ q.val ( ),
            method:      "POST", 
            dataType:    "json",
            contentType: "application/json; charset=utf-8",
            data:        jsonPerson,
            success:function ( result ) {
                processFormResult ( result );
            },
            error:function ( xhr, status, error ) {
                alert ( "ERROR: " + error + " STATUS: " + status );
           }
        } );
    }

    function toggleTermsModal ( ) {
        termsModal.modal ( "show" );
    }
    
//   ADDRESS STUFF - BEGIN

    function setupAddressVisibility ( data ) {
     
        if ( data.countryCode > 0 ) {
            retrieveAuthonomiesHtml ( data.countryCode, data.authonomyCode );
            authonomyDropdown.show ( );
        } else {
            authonomyDropdown.hide ( );
        }

        if ( data.authonomyCode > 0 ) {
            retrieveProvincesHtml ( data.authonomyCode, data.provinceCode );
            provinceDropdown.show ( );
        } else {
            provinceDropdown.hide ( );
        }

        if ( data.provinceCode > 0 ) {
            addressGroup.show ( );
        } else {
            addressGroup.hide ( );
        }

    }

    function retrieveAuthonomiesHtml ( cCode, aCode ) {
        $.get ( ( "fiAPI/0.0/geo/authonomiesHtml/"+ q.val ( ) + "/" + cCode ), function ( data ) { authonomyCode.html ( data  );
                                                                                                   authonomyCode.val  ( aCode );
                                                                                                 } );
    }
    
    function retrieveProvincesHtml ( aCode, pCode ) {
        $.get ( ( "fiAPI/0.0/geo/provincesHtml/"+ q.val ( ) + "/" + aCode ), function ( data ) { provinceCode.html ( data );
                                                                                                 provinceCode.val ( pCode );
                                                                                               } );
    }

    function processCountryChange ( ) {

        if ( countryCode.val ( ) === "0" ) {
            authonomyDropdown.hide ( );
            authonomyCode.val  ( "0" );
            provinceDropdown.hide  ( );
            provinceCode.val   ( "0" );
            addressGroup.hide      ( );
        } else {
            retrieveAuthonomiesHtml ( countryCode.val ( ), authonomyCode.val ( ) );
            authonomyDropdown.show  ( );
            provinceDropdown.hide   ( );
            addressGroup.hide       ( );
        }
    }

    function processAuthonomyChange ( ) {
        
        if ( authonomyCode.val ( ) === "0" ) {
            provinceDropdown.hide ( );
            provinceCode.val  ( "0" );
            addressGroup.hide     ( );
        } else {
            retrieveProvincesHtml ( authonomyCode.val ( ), provinceCode.val ( ) );
            provinceDropdown.show ( );
            addressGroup.hide     ( );            
        }
    }

    function processProvinceChange ( ) {
        
        if ( provinceCode.val ( ) === "0" ) {
            addressGroup.hide ( );
        } else {
            addressGroup.show ( );            
        }
    }

//   ADDRESS STUFF - END

//   ALERTIFY
      reset = function ( ) {
        $( "toggleCSS" ).href = "css/alertify.core.css";
        alertify.set ( {
          labels: {
            ok: "OK",
            cancel: "Cancel"
          },
          delay: 3000,
          buttonReverse: false,
          buttonFocus: "ok"
        } );
      };
//   ALERTIFY

//   VALIDATIONS - BEGIN
    function processFormResult ( result ) { 

        clearValidationIssues ( );

        if ( result.issues.length > 0 ) {
            displayValidationIssues ( result );
            reset ( );
            alertify.error ( result.status.issueMessage );

        } else {
            reset ( );
            alertify.success ( result.status.issueMessage );
            setInterval ( function ( ) { window.location.replace (  result.navTo + "?q=" + q.val ( ) ); } , 3500 );
        }
    }
    
//    function bePolite ( ) {
//        window.location.replace ( "thanks?q=" + q.val ( ) );        
//    }
    
    function setupValidations ( ) {

        validations.push ( "idDocument" );
        validations.push ( "firstName"  );
        validations.push ( "lastName"   );
        validations.push ( "birthdate"  );
        validations.push ( "idNumber"   );
        validations.push ( "email"      );
        validations.push ( "phone"      );
        validations.push ( "gender"     );

        validations.push ( "country"    );
        validations.push ( "authonomy"  );
        validations.push ( "province"   );
        validations.push ( "address"    );
        validations.push ( "city"       );
        validations.push ( "postCode"   );
        validations.push ( "terms"      );

    }

    function clearValidationIssues ( ) {
        
        for ( var i = 0; i < validations.length; i++ ) { 
            $( "#" + validations [ i ] + "_val" ).removeClass ( "has-error" );
            $( "#" + validations [ i ] + "_issues" ).html ( "" );
            $( "#" + validations [ i ] + "_issues" ).hide ( );
        }

    }

    function displayValidationIssues ( result ) {

        // Scroll to first issue 
        //alert ( "#" + result [ 0 ].issueID + "_val" );
        $( "#" + result.issues [ 0 ].issueID + "_val" ).scrollIntoView ( );

        for ( var i = 0; i < result.issues.length; i++ ) { 
            //alert ( "displayValidationIssues - #" + result [ i ].issueID + "_issues" + " - " + "#" + result [ i ].issueID + "_val" );
            $( "#" + result.issues [ i ].issueID + "_val"    ).addClass ( "has-error" );
            $( "#" + result.issues [ i ].issueID + "_issues" ).html     ( result.issues [ i ].issueMessage );
            $( "#" + result.issues [ i ].issueID + "_issues" ).show     ( );
        }

    }
//   VALIDATIONS - END

} );