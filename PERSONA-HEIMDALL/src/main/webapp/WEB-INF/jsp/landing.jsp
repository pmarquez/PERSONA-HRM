<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Alexandria Document Management Microservice</title>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
    <meta name="description" content="Alexandria, document repository microservice." />
    <meta name="keywords" content="Documents, Document Manager, Microservices" />
    <meta name="author" content="Paulo Marquez Herrero, Mobile Cloud Architect" />
    <link rel="shortcut icon" href="img/favicon.png">
  </head>

  <body>
    <form id="upload-file-form">
        <label for="upload-file-input">Upload your file:</label>
        <input id="upload-file-input" type="file" name="uploadfile" />
    </form>
    <br>
    <span id="upload-file-message"></span>
    <script src="js/jquery/jquery-2.1.0.js"></script>
    <script src="js/alexandria/landing.js"></script>
  </body>
</html>