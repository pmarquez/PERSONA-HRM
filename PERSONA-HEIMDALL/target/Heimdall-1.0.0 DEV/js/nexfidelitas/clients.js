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

//   DataTable column config. If you are going to add/delete columns, do so: 1) HERE, 
//                                                                           2) ON THE GENERATED JSON.
//   If any column is UNDEFINED, check the mRender...
        dataTableObj = clientsTable.DataTable ( {
            "sAjaxSource": "fiAPI/1.0/ops/clients",
            "aoColumns": [
                {"data": "clientCode", "sWidth": "5%", "sClass": "center", "mRender": renderCheckbox },              //   Client Code
                {"data": "clientName", "sTitle": "Nombre", "sWidth": "25%", "mRender": renderAnchorCN },             //   Client Name
                {"data": "clientTaxID", "sTitle": "CIF/NIF", "sWidth": "15%", "sClass": "center" },                  //   Tax ID
                {"data": "phone", "sTitle": "Phone", "sWidth": "20%", "sClass": "center" },                          //   Phone
                {"data": "website", "sTitle": "Website", "sWidth": "20%", "sClass": "center"  },                     //   Website
                {"data": "locale", "sTitle": "Locale", "sWidth": "10%", "sClass": "center"  },                       //   Locale
                {"data": "active", "sTitle": "Active", "sWidth": "5%", "sClass": "center", "mRender": renderIcon }   //   is client Active? (within nexfidelitas)
            ],
            "aaSorting": [[2, 'asc']],
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
    function renderAnchorCN ( data, type, row ) {
        return "<a href=\"client?c=" + row.clientCode + "\">" + row.clientName + "</a>";
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
        return "<input type=\"checkbox\" name=\"clientCode\" value=\"" + data + "\" />";
    }

} );
