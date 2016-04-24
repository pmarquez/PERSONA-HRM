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
    <link href="css/charts-graphs.css" rel="stylesheet">
    <link href="css/statistics.css" rel="stylesheet">

    <link href="fonts/font-awesome.min.css" rel="stylesheet">

    <!-- HTML5 shiv and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="js/html5shiv.js"></script>
      <script src="js/respond.min.js"></script>
    <![endif]-->

    <script src="js/nexfidelitas/ganalytics.js"></script>
  </head>

  <body>

    <!-- Header Start -->
    <jsp:include page="includes/top_menu.jsp" flush="true" />
    <!-- Header End -->
    <!-- Main Container start -->
    <div class="dashboard-container">

      <div class="container">
        <!-- Top Nav Start -->
            <jsp:include page="includes/main_menu.jsp" flush="true" />
        <!-- Top Nav End -->

        <!-- Sub Nav End -->
            <jsp:include page="includes/sub_menu_commands.jsp" flush="true" />
        <!-- Sub Nav End -->

        <!-- Dashboard Wrapper Start -->
        <div class="dashboard-wrapper-lg">
            <!-- Row Start -->
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="widget">
                        <div class="widget-header">
                            <div class="title">
                                Solicitudes
                            </div>
                        </div>
                        <div class="widget-body">
                            <div id="cardApplications" class="chart-height-md"></div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Row End -->
          
          <!-- Row Start -->
          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                <div class="widget">
                    <div class="widget-header">
                        <div class="title">
                            Web vs. Tablet
                        </div>
                    </div>
                    <div class="widget-body">
                        <div id="applicationOrigins" class="chart-height-md"></div>
                    </div>
                </div>
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
              <div class="widget">
                <div class="widget-header">
                  <div class="title">
                    Demografía [Género]
                  </div>
                </div>
                <div class="widget-body">
                  <div id="applicationDemography" class="chart-height-md"></div>
                </div>
              </div>
            </div>            
          </div>            
          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
              <div class="widget">
                <div class="widget-header">
                  <div class="title">
                    Tarjetas Consumidas
                  </div>
                </div>
                <div class="widget-body">
                  <div id="cardsConsumption" class="gauge chart-height-md"></div>
                </div>
              </div>
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
              <div class="widget">
                <div class="widget-header">
                  <div class="title">
                    Preferencias Seleccionadas
                  </div>
                </div>
                <div class="widget-body">
                  <div id="applicationPreferences" class="chart-height-md"></div>
                </div>
              </div>
            </div>
          </div>
          <!-- Row End -->
          
        </div>
        <!-- Dashboard Wrapper End -->

        <footer>
          <p>© nexglobal 2015</p>
        </footer>

      </div>
    </div>
    <!-- Main Container end -->

    <script src="js/jquery/jquery-2.1.0.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.scrollUp.js"></script>
    
    <!-- jQuery UI JS -->
    <script src="js/jquery-ui-v1.10.3.js"></script>

    <!-- Just Gage -->
    <script src="js/justgage/justgage.js"></script>
    <script src="js/justgage/raphael.2.1.0.min.js"></script>

    <!-- Flot Charts -->
    <script src="js/flot/jquery.flot.js"></script>
    <script src="js/flot/jquery.flot.orderBar.min.js"></script>
    <script src="js/flot/jquery.flot.stack.min.js"></script>
    <script src="js/flot/jquery.flot.pie.min.js"></script>
    <script src="js/flot/jquery.flot.tooltip.min.js"></script>
    <script src="js/flot/jquery.flot.resize.min.js"></script>

    <!-- Custom JS -->
    <script src="js/menu.js"></script>
    <script src="js/nexfidelitas/stats.js"></script>
    
    <script type="text/javascript">
      //ScrollUp
      $(function () {
        $.scrollUp({
          scrollName: 'scrollUp', // Element ID
          topDistance: '300',     // Distance from top before showing element (px)
          topSpeed: 300,          // Speed back to top (ms)
          animation: 'fade',      // Fade, slide, none
          animationInSpeed: 400,  // Animation in speed (ms)
          animationOutSpeed: 400, // Animation out speed (ms)
          scrollText: 'Top',      // Text for element
          activeOverlay: false    // Set CSS color to display scrollUp active point, e.g '#00FFFF'
        });
      });
    </script>

  </body>
</html>