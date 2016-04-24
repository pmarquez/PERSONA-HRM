<!DOCTYPE html>
<html>
  <head>
    <title>nexfidelitas, by nexglobal</title>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
    <meta name="description" content="nexfidelitas, nexglobal fidelity platform" />
    <meta name="keywords" content="Fidelity, Membership" />
    <meta name="author" content="Paulo Marquez Herrero, Mobile Cloud Architect" />
    <link rel="shortcut icon" href="img/favicon.png">
    
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <link href="css/new.css" rel="stylesheet">
    <!-- Important. For Theming change primary-color variable in main.css -->

    <link href="fonts/font-awesome.min.css" rel="stylesheet">
    <link href="css/pwdRec01.css" rel="stylesheet" type="text/css"/>

    <style>
        .login-wrapper {
            margin-top: 128px;
        }
    </style>
    <!-- HTML5 shiv and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="js/html5shiv.js"></script>
      <script src="js/respond.min.js"></script>
    <![endif]-->

    <script src="js/nexfidelitas/ganalytics.js"></script>
  </head>

  <body>
    <!-- Main Container start -->
    <div class="dashboard-container">

      <div class="container">

        <!-- Row Start -->
        <div class="row">
          <div class="col-lg-6 col-md-6 col-md-offset-3">
            <div class="sign-in-container">
              <form class="login-wrapper" id="pwdRec01Form" name="pwdRec01Form">
                <div class="header">
                  <div class="row">
                    <div class="col-md-12 col-lg-12">
                      <h3><img src="images/logo_fidelitas.png" alt="nexfidelitas" class="pull-left"></h3>
                    </div>
                  </div>
                </div>
                <div class="content">
                    <div id="pwdRecAlert" class="alert alert-block alert-info fade in no-margin">
                        <h4 style="margin-bottom: 6px;" id="messageCaption">Error en el proceso de regeneraci�n de contrase�a.</h4>
                        <p>
                            <span id="message">SOMETHING CHUNGO HAPPENED!</span>
                        </p>
                    </div>
                </div>
                <div class="actions">
                    <button type="button" class="btn btn-info" id="processBtn">Reintentar</button>
                    <div class="clearfix"></div>
                </div>
              </form>
            </div>
          </div>
        </div>
        <!-- Row End -->
      </div>
    </div>
    <!-- Main Container end -->
    <script src="js/jquery/jquery-2.1.0.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/nexfidelitas/regenError.js"></script>
  </body>
</html>