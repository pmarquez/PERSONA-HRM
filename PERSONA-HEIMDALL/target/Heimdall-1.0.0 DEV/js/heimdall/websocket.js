var wsUri = "ws://localhost:8084/Heimdall/dashboardUpdates";
var websocket = new WebSocket ( wsUri );

websocket.onmessage = function ( evt ) { onMessage ( evt ); };
websocket.onerror   = function ( evt ) { onError   ( evt ); };
websocket.onopen    = function ( evt ) { onOpen    ( evt ); };

function onMessage ( evt ) {
    alert ( "received over websockets: " + evt.data );
    var obj = $.parseJSON( evt.data );
    
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