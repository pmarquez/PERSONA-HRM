$( function ( ) {

    var numPieces       = $( "#numPieces"       );
    var baseTime        = $( "#baseTime"        );
    var dimension       = $( "#dimension"       );    
    var simParamsForm   = $( "#simParamsForm"   );
    var btnProcessForm  = $( "#btnProcessForm"  );

    var simTablePane    = $( "#simTablePane"    );
    var simulationTable = $( "#simulationTable" );

    var clickedRowData;
    var tableData;
    
    btnProcessForm.on ( "click",  calculate             );
    dimension.on      ( "change", calcPrintsAndExposure );
    
    $( '#simulationTable tbody' ).on ( 'click', 'tr', retrieveRowValues );
    
    var dataTableObj;

    initCalc ( );

    function initCalc ( ) {
        numPieces.val (  6 );
        baseTime.val  ( 12 );
        dimension.val (  2 );
    }
    
    function calculate ( ) {

        //simTablePane.hide ( );

        var calcParamsRec = { };

        calcParamsRec.numPieces = numPieces.val ( );
        calcParamsRec.baseTime  = baseTime.val  ( );
        calcParamsRec.dimension = dimension.val ( );

        var jsonParams = JSON.stringify ( calcParamsRec );

        $.ajax ( {        
            url:         "adsAPI/1.0/sim/adsSimulation",
            method:      "POST", 
            dataType:    "json",
            contentType: "application/json; charset=utf-8",
            data:        jsonParams,
            success:function ( result ) {
                displaySimulationResult ( result.aaData );
            },
            error:function ( xhr, status, error ) {
                alert ( "ERROR: " + error + " STATUS: " + status );
            }
        } );

    }
    
    function displaySimulationResult ( data ) {
                
        tableData = data;

        var dataArray = [ ];

        for ( var i = 0; i < data.length; i++ ) {
            var innerArray = [ ];

                innerArray.push ( data [ i ].pieceName    );
                innerArray.push ( data [ i ].assignedTime );
                innerArray.push ( data [ i ].printNumber  );
                innerArray.push ( data [ i ].exposureTime );
                innerArray.push ( data [ i ].timeframe    );
            
            dataArray.push ( innerArray );
        }
        
        if ( dataTableObj !== undefined ) {
            dataTableObj.destroy ( );
        }
        
        dataTableObj = simulationTable.DataTable ( {
            data: dataArray,
            columns: [
                { "title": "Pieza" },
                { "title": "Tiempo por Impresi&oacute;n" },
                { "title": "Total Impresiones" },
                { "title": "Tiempo Exposici&oacute;n" }
            ]
        } );

        simTablePane.show ( );
    }
    
    var yaEntre = false;
    
    function retrieveRowValues ( ) {
        var theRow = $( this );

        clickedRowData = {};
        clickedRowData.piece = ( dataTableObj.row ( theRow ).data ( ) ) [ 0 ];
        clickedRowData.time  = ( dataTableObj.row ( theRow ).data ( ) ) [ 1 ];

        yaEntre = false;
        showPieceTimeDialog ( clickedRowData );

    }
    
    function showPieceTimeDialog ( data ) {

        if ( yaEntre === false ) {

            bootbox.prompt ( {
                title:    "Ingrese el tiempo deseado para la pieza: '" + data.piece + ":",
                value:    data.time,
                callback: function ( result ) {
                              if ( result === null ) {
                                  //alert ( "Prompt dismissed" );
                              } else {
                                  if ( isNaN ( result ) ) {
                                      alert ( "El valor introducido no es un n\372mero v\341lido." );
                                  } else {
                                    //alert ( "New Value: " + result );
                                    data.newTime = parseInt ( result );
                                    localSimulationCalc ( data );
                                    yaEntre = true;
                                  }
                              }
                          }
            } );   
        }
    }

    var SECONDS_IN_A_DAY   = 45000;                   //   45000 seconds = 12 hours 30 minutes. 
    var SECONDS_IN_A_WEEK  = 7  * SECONDS_IN_A_DAY;   //   7 Days * 12 hours 30 minutes. 
    var SECONDS_IN_A_MONTH = 28 * SECONDS_IN_A_DAY;   //   28 Days * 12 hours 30 minutes. 

    function getTimeFrame ( timeFrameCode ) {

        var totalTimeToPlay = 0;

        switch ( timeFrameCode ) {
            case 1:
                totalTimeToPlay = SECONDS_IN_A_DAY;
                break;

            case 2:
                totalTimeToPlay = SECONDS_IN_A_WEEK;
                break;

            case 3:
                totalTimeToPlay = SECONDS_IN_A_MONTH;
                break;

        }
        
        return totalTimeToPlay ;
    }

    function localSimulationCalc ( data ) {

        for ( var i = 0; i < tableData.length; i++ ) {

            if ( tableData [ i ].pieceName === data.piece && tableData [ i ].assignedTime === data.time ) {
                tableData [ i ].assignedTime = data.newTime;
                data.newTime = undefined;
            }
        }
        
        calcPrintsAndExposure ( );        

    }
    
//   BEGIN CALCULATION...
    function calcPrintsAndExposure ( ) {

        for (var i = 0; i < tableData.length; i++ ) {
            tableData [ i ].printNumber = 0;
            tableData [ i ].exposureTime = "";
        }

        
        var timeToSpare = getTimeFrame ( parseInt ( dimension.val ( ) ) );
        var moreTimeToSpare = true;
        
        //alert ( timeToSpare ); 
        
        while ( moreTimeToSpare ) {
            for (var i = 0; i < tableData.length; i++ ) {
                
                if ( timeToSpare >= tableData [ i ].assignedTime ) {
                    tableData [ i ].printNumber = ( tableData [ i ].printNumber + 1 );
                    timeToSpare = timeToSpare - tableData [ i ].assignedTime;
                } else {
                    moreTimeToSpare = false;
                }
            }
        }

        for (var i = 0; i < tableData.length; i++ ) {
            tableData [ i ].exposureTime = secondsToTime ( tableData [ i ].printNumber * tableData [ i ].assignedTime );
        }

        displaySimulationResult ( tableData );

    }
    
    function secondsToTime ( sec ) {
        var hours   = Math.floor ( sec / 3600 );
        var minutes = Math.floor ( ( sec - ( hours * 3600 ) ) / 60 );
        var seconds = sec - ( hours * 3600 ) - ( minutes * 60 );

        if ( hours   < 10 ) { hours   = "0" + hours; }
        if ( minutes < 10 ) { minutes = "0" + minutes; }
        if ( seconds < 10 ) { seconds = "0" + seconds; }
        return ( hours + " h " + minutes + " m " + seconds + " s" );
    
    }


} );