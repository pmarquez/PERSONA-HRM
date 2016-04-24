$( function ( ) {

    var benefitsPane   = $( "#benefitsPane" );

    var q              = $( "#q"           );
    var benefitsButton = $( "#benefitsBtn" );
    var applyButton    = $( "#applyBtn"    );

//   EVENTS - BEGIN
    benefitsButton.on ( "click", toggleBenefitsPane );
    applyButton.on    ( "click", gotoApplication    );
//   EVENTS - END

//alert ( navigator.appName   );
//alert ( navigator.userAgent ); 

    function toggleBenefitsPane ( ) { 
        benefitsPane.toggle( 500 );
    }

    function gotoApplication ( ) { 
        window.location.replace ( "person?q=" + q.val ( ) );
    }

} );