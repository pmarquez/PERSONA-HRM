$( function( ) {
    var wsUri = "ws://localhost:8084/Heimdall/dashboardUpdates";
    var websocket;

//   Pie Chart - BEGIN
    var globalUsageChart = $( "#globalUsageChart" );
    var myGUChart = { "alpha":              1,
                      "background-color":   "#f5f5f5",
                      "background-color-2": "#f5f5f5",
                      "type":               "pie",
                      "stacked":            "true",

                      "scale-y": { "label": { "text":"Percentage Occupation" } },
                    
//                     "series":[ { "text":"NFC 1", 
//                                  "values":[1] }, 
//                                { "text":"NFC 2", 
//                                  "values":[2] }, 
//                                { "text":"NFC 3",
//                                  "values":[7] }, 
//                                { "text":"NFC 4",
//                                  "values":[3] } ]
                      "series": [ { "values": [ ] } ] 
                    };
//   Pie Chart - END

//   Bar Chart - BEGIN
    var hourlyUsageChart = $( "#hourlyUsageChart" );
    var myHUChart = { "alpha":              1,
                      "background-color":   "#f5f5f5",
                      "background-color-2": "#f5f5f5",
                      "type":               "bar",
                      "stacked":            "false",

                      "scale-y": { "label": { "text":"Times Sensor Used per Hour (24 Hours)" } },
                    
                      "series": [ { "values": [ ] } ] 
//                      "series": [ { "values": [ 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0,4,7,3,20,21,12,2,17 ] }, 
//                                  { "values": [ 3,5,9,6,0,0,0,2,0,0,0,4,0,0,0,9,0,0,0,0,5,0,0,4 ] }, 
//                                  { "values": [ 2,8,7,5,0,0,0,0,9,0,1,2,3,4,5,6,7,8,9,0,11,0,0,0 ] }, 
//                                  { "values": [ 4,0,6,7,9,8,7,6,5,4,3,2,1,0,9,8,7,6,5,4,3,2,1,10] } ] 
                    };
//   Bar Chart - END

    init ( );   //   Set-up the magic.

    function init ( ) {

        websocket = new WebSocket ( wsUri );
        websocket.onmessage = function ( evt ) { onMessage ( evt ); };
        websocket.onerror   = function ( evt ) { onError   ( evt ); };
        websocket.onopen    = function ( evt ) { onOpen    ( evt ); };


        //   Setup all button and event listeners
        window.addEventListener         ( "beforeunload", destroy, false );

        //   Initialize the chart object
        globalUsageChart.zingchart ( );
        globalUsageChart.zingchart ( { JSON: myGUChart } );
        
        hourlyUsageChart.zingchart ( );
        hourlyUsageChart.zingchart ( { JSON: myHUChart } );

    }

    function onMessage ( evt ) {
        console.log ( "received over websockets: " + evt.data );
        var obj = $.parseJSON ( evt.data );

        if ( obj.dashboard !== undefined ) { processSnapshot   ( obj ); } 
        else { "No Data To Update"; }

    }

    function onError ( evt ) {
        console.log ( 'ERROR: ' + evt.data );
    }

    function onOpen ( ) {
        console.log ( "Connected to: " + wsUri );
    }

    // For testing purposes
    var output = document.getElementById ( "output" );

    function sendStopCommand ( ) {
        websocket.send ( "stopMonitor" ); 

        //   Set html buttons state
        buttonStates ( "stoppedState" );
    
    }

    function sendCloseCommand ( ) {
        websocket.send ( "closeConnection" ); 

        //   Set html buttons state
        buttonStates ( "closedState" );

    }

    //   Closes the WebSocket connection from the client side.
    function closeSocketConnection ( ) {
        websocket.close ( );
    }

    //   We are exiting, close everything.
    function destroy ( ) {
        sendStopCommand       ( );
        closeSocketConnection ( );
    }

    function processSnapshot ( obj ) {
        processDataForGlobalChart ( obj.dashboard[0] );
        processDataForHourlyChart ( obj.dashboard[1] );
    }

    function processDataForGlobalChart ( obj ) {

        var seriesObject = { series: [ ] };
        
        var slice;
        var values;
                
        $.each ( obj.data, function ( idx, sensor ) {

            slice  = new Object ( );
            values = new Array  ( );
            values.push ( sensor.useCount );
            
            slice.text   = sensor.sensorName;
            slice.values = values;

            seriesObject.series.push ( slice );
        } );
        
        console.log ( JSON.stringify ( seriesObject ) );

        globalUsageChart.setJSON ( seriesObject );
    }

    function processDataForHourlyChart ( obj ) {
        
//        console.log ( "processDataForHourlyChart: ENTER" );

//        console.log ( "processDataForHourlyChart - [OBJ]: " + JSON.stringify ( obj ) );

        var values = new Array ( );
        var series = new Array ( );
        
        var sensorSeries;

        var sentinnel = obj.data[0].sensorName;

        $.each ( obj.data, function ( idx, sensor ) {

//            console.log ( "processDataForHourlyChart - [SENSOR]: " + JSON.stringify ( sensor ) );
    
            if ( sentinnel === sensor.sensorName ) {
                values.push ( sensor.useCount );
            
            } else  {
                
//                console.log ( "processDataForHourlyChart - [VALUES]: " + JSON.stringify ( values ) );

                sentinnel = sensor.sensorName;
                
                sensorSeries = new Object ( );
                sensorSeries.values = values;
                series.push ( sensorSeries );
                
                values = new Array  ( ); 
                values.push ( sensor.useCount );
            
            }
//            console.log ( "processDataForHourlyChart - [sentinnel]: " + sentinnel + " - " + idx );

        } );

        sensorSeries = new Object ( );
        sensorSeries.values = values;
        series.push ( sensorSeries );

//        console.log ( "processDataForHourlyChart - [VALUES]: " + JSON.stringify ( values ) );

//        console.log ( "processDataForHourlyChart - [SERIES]: " + JSON.stringify ( series ) );

        var seriesObject = new Object ( );
        
        seriesObject.series = series;

        hourlyUsageChart.setJSON ( seriesObject );
        
//        console.log ( "processDataForHourlyChart: EXIT" );
    }

    /*
    function writeToScreen ( message ) {
    if ( output == null ) { output = document.getElementById ( "output" ); }
        //output.innerHTML += message + "<br>";
        output.innerHTML = message + "<br>";
    }

    function sendText ( json ) {
        console.log ( "sending text: " + json );
        websocket.send ( json );
    }
*/

} );