/**
 * Tutorial created by: Paulo E. Márquez Herrero
 *
 * email:   paulo.marquez@gmail.com / me@pmarquezh.info
 * twitter: @pauloemarquez
 * github:  https://github.com/pmarquez
 * blog:    http://ruminationsontechnology.blogspot.com.es/
 *
 * The code included in this tutorial is not guaranteed to work 
 * in a production environment or be fit for any particular purpose 
 * other that to showcase the technology related to this tutorial.
 */

$( function ( ) {
    var websocket;

//   Pie Chart - BEGIN
    var globalUsageChart = $( "#globalUsageChart" );
    var myGUChart = { "alpha":1,
                      "background-color":"#f5f5f5",
                      "background-color-2":"#f5f5f5",
                      "type" : "pie",
                      "stacked" : "true",

                      "scale-y": { "label": { "text":"Percentage Occupation" } },
                    
                      "series": [ { "values": [ ] } ] 
                    };
//   Pie Chart - END

//   Bar Chart - BEGIN
    var hourlyUsageChart = $( "#hourlyUsageChart" );
    var myHUChart = { "alpha":1,
                      "background-color":"#f5f5f5",
                      "background-color-2":"#f5f5f5",
                      "type" : "vbar",
                      "stacked" : "false",

                      "scale-y": { "label": { "text":"Percentage Occupation" } },
                    
                      "series": [ { "values": [ ] } ] 
                    };
//   Bar Chart - END

    init ( );   //   Set-up the magic.

    function init ( ) {

        //   Setup all button and event listeners
        window.addEventListener         ( "beforeunload", destroy, false );

        //   Initialize the chart object
        globalUsageChart.zingchart ( );
        globalUsageChart.zingchart ( { JSON: myGUChart } );
        
        hourlyUsageChart.zingchart ( );
        hourlyUsageChart.zingchart ( { JSON: myHUChart } );

    }

    //   When the client requests to start the monitoring, we sent the "startMonitor" message here.
    function sendStartCommand ( ) {
        websocket.send ( "startMonitor" ); 

        //   Set html buttons state
        buttonStates ( "startedState" );
        
    }

    //   When the client requests to stop the monitoring, we sent the "stopMonitor" message here.
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
//   WebSocket Lifecycle - END


//   Chart and Table Processing - BEGIN
    function processSnapshot ( obj ) {
        processDataForChart ( obj.dashboard );
    }

    function processDataForChart ( obj ) {

        var seriesObject = { series: [ ] };
        
        var GSUSeries = { values: [ ] };
//        var series2Item = { values: [ ] };

        $.each ( obj.dashboard[0].data, function ( idx, sensor ) {
            GSUSeries.values.push ( sensor.useCount );
//            series2Item.values.push ( 100 - usedCapacityPercentage );

        } );
        
        seriesObject.series.push ( GSUSeries );
//        seriesObject.series.push ( series2Item );

        globalUsageChart.setJSON ( seriesObject );
    }

} );