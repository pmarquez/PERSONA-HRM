$( function ( ) {
    var clientsForm     = $( "#clientsForm"  );
    var clientsTable    = $( "#clientsTable" );
    var newClientButton = $( "#btnNew"       );
    var delClientButton = $( "#btnDelete"    );

    newClientButton.on ( "click", newClient    );
    delClientButton.on ( "click", deleteClient );

    var dataTableObj;
    
    initThings ( );
    
    function initThings ( ) {

        $.fn.dataTable.moment( 'DD-MM-YYYY' );
        $.fn.dataTable.moment( 'DD-MM-YYYY HH:mm:ss' );

//   DataTable column config. If you are going to add/delete columns, do so: 1) HERE, 
//                                                                           2) ON THE GENERATED JSON.
//   If any column is UNDEFINED, check the mRender...
        dataTableObj = clientsTable.DataTable ( {
            "sAjaxSource": "fiClientAPI/1.0/ops/cli_persons",
            "aoColumns": [
                {"data": "personIdNumber", "sTitle": "DNI...", "sWidth": "7%", "sClass": "center" },                //   DNI
                {"data": "personFirstName", "sTitle": "Nombres", "sWidth": "10%", "mRender": renderAnchorFN },      //   First Name
                {"data": "personLastName", "sTitle": "Apellidos", "sWidth": "10%", "mRender": renderAnchorLN },     //   Last Name
                {"data": "cardNumber", "sTitle": "N&uacute;m. Tarjeta", "sWidth": "10%", "sClass": "center" },      //   Card Number
                {"data": "birthDate", "sTitle": "F. nacimiento", "sWidth": "8%", "sClass": "center" },              //   BirthDate
                {"data": "applicationDateTime", "sTitle": "Fecha Registro", "sWidth": "10%", "sClass": "center"  }, //   Application Date Time
                {"data": "email", "sTitle": "Email", "sWidth": "15%", "sClass": "center"  },                        //   Email
                {"data": "phone", "sTitle": "Tel&eacute;fono", "sWidth": "10%", "sClass": "center" },               //   Phone
                {"data": "active", "sTitle": "Activo", "sWidth": "5%", "sClass": "center", "mRender": renderIcon }  //   is client Active? (within nexfidelitas)
            ],
            "aaSorting": [ [ 2, 'asc' ] ],
//            "bAutoWidth":  false,
//            "sScrollY":    "400px",
//            "bPaginate":   false,
//            "bProcessing": true,
            "sPaginationType": "full_numbers"
        } );
    }
    
    var formProcessResult = { };

    function newClient    ( ) { 
        window.location.href = "client";
        return false;
    }
    
    function deleteClient ( ) { }
    
    /*   ¿UTILITARIO?   */
    function signalFormProcessResult ( ) {
        var miAlerta;
        
        if ( formProcessResult.codTipoRespuesta === 1 ) {
            miAlerta = $( "#fade_message_success" );
            dismissForm ( );
        } else if ( formProcessResult.codTipoRespuesta === 4 ) {
            miAlerta = $( "#fade_message_error" );
        } else {
            miAlerta = $( "#fade_message_info" );
            dismissForm ( );            
        }

        clientsTable.fnClearTable ( );
        clientsTable.fnReloadAjax ( );        
        muestraMensaje ( miAlerta, formProcessResult.mensaje );
    } 

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
    
    /*   ¿UTILITARIO?   */
    function renderAnchorFN ( data, type, row ) {
        return "<a href=\"cli_person?c=" + row.personCode + "\">" + row.personFirstName + "</a>";
    }

    function renderAnchorLN ( data, type, row ) {
        return "<a href=\"cli_person?c=" + row.personCode + "\">" + row.personLastName + "</a>";
    }

    /*   ¿UTILITARIO?   */
    function renderIcon ( data, type, row ) {
        if ( data === true ) {
            return "<img src=\"images/powered_on.png\" class=\"statusIcon\" alt=\"[ACTIVE]\">";        
        } else {
            return "<img src=\"images/powered_off.png\" alt=\"[INACTIVE]\">";        
        }
    }

    /*   ¿UTILITARIO?   */
    function renderCheckbox ( data, type, row ) {
        return "<input type=\"checkbox\" name=\"personCode\" value=\"" + data + "\" />";
    }

} );
