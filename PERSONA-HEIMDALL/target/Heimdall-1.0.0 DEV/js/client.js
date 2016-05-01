$( function ( ) {
    var msgBtn    = $( "#msgBtn"  );

//   EVENTS - BEGIN
    msgBtn.on ( "click",  processMessage );
//   EVENTS - END

    function processMessage ( ) {

		alert ( "Me han clikeao!" );

        var messageRec = { };

                connection = { connectionCode: 0, clientCode: 0, connectionName: "", hostname: "smtp.gmail.com", smtpPort: 587, encryptionType: "tls", user: "paulomarquez@nexglobal.es", password: "t3n3r1f3" };
            messageRec.connection = connection;



                sender = { name: "Paulo Marquez Herrero", address: "paulomarquez@nexglobal.es" };
            messageRec.sender = sender;

                replyTo = { name: "Paulo Marquez Herrero", address: "paulomarquez@nexglobal.es" };
            messageRec.replyTo = replyTo;

                bounceAddress = { name: "Paulo Marquez Herrero", address: "paulomarquez@nexglobal.es" };
            messageRec.bounceAddress = bounceAddress;

              recipients = new Array ( );
              recipient1 = { name:"Paulo Marquez Herrero", address:"me@pmarquezh.info", type:"to" };
			  recipients.push ( recipient1 );

              recipient2 = { name:"Paulo Marquez Herrero", address:"paulomarquez@nexglobal.es", type:"cc" };
			  recipients.push ( recipient2 );

              recipient3 = { name:"Paulo Marquez Herrero", address:"paulo.marquez@gmail.com", type:"bcc" };
			  recipients.push ( recipient3 );

            messageRec.recipients = recipients;

              headers = new Array ( );
              header1 = { name:"site", value:"http://fidelitas.nexglobal.es" };
			  headers.push ( header1 );

            messageRec.headers = headers;

            messageRec.subject = "HERMES - PRUEBA";
            messageRec.message = "Esta es una prueba de un correo enviado por HERMES, su plataforma de mensajeria en la nube!.";

        var jsonMsg = JSON.stringify ( messageRec );

		alert ( jsonMsg );

        $.ajax ( {
            url:         "hermesAPI/1.0/sendEmail",
            method:      "POST",
            dataType:    "json",
            contentType: "application/json; charset=utf-8",
            data:        jsonMsg,
            success:function ( result ) {
                //processFormResult ( result );
                alert ( result );
            },
            error:function ( xhr, status, error ) {
                alert ( "ERROR: " + error + " - STATUS: " + status + " - xhr: " + xhr );
           }
        } );
    }

} );