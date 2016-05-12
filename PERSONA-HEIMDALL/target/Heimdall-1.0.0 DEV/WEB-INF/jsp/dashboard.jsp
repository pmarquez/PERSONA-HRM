<!DOCTYPE html>
<html>
  <head>
    <title>HEIMDALL</title>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
    <meta name="description" content="HEIMDALL REAL TIME ACCESS CONTROL SYSTEM" />
    <meta name="keywords" content="Access, Control" />
    <meta name="author" content="Paulo Marquez Herrero, Mobile Cloud Architect" />
    <link rel="shortcut icon" href="img/favicon.png">
    
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/new.css" rel="stylesheet"> 
    <link href="css/charts-graphs.css" rel="stylesheet">
    <link href="css/dashboard.css" rel="stylesheet">

    <link href="fonts/font-awesome.min.css" rel="stylesheet">

  </head>
  
  <body>
    <!-- Main Container start -->
    <div class="dashboard-container">

        <div class="container">
            <!-- Dashboard Wrapper Start -->
            <div class="dashboard-wrapper-lg">

            <!-- Row Start -->
            <div class="row">
                <div class="col-lg-3 col-md-3 col-sm-3 col-xs-6">
                    <div class="wrapper">
                      <button class="btn btn-block btn-default" id="btnOpenConnection" type="button">
                        Open WS
                      </button>
                    </div>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-3 col-xs-6">
                    <div class="wrapper">
                      <button class="btn btn-block btn-default" id="btnStartMonitor" type="button">
                        Start Monitor
                      </button>
                    </div>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-3 col-xs-6">
                    <div class="wrapper">
                      <button class="btn btn-block btn-default" id="btnStopMonitor" type="button">
                        End Monitor
                      </button>
                    </div>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-3 col-xs-6">
                    <div class="wrapper">
                      <button class="btn btn-block btn-default" id="btnCloseServerConnection" type="button">
                        Close WS
                      </button>
                    </div>
                </div>
            </div>
            <br>
            
            <!-- Row Start -->
            <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                    <div class="widget">
                        <div class="widget-header">
                            <div class="title">
                                Global Sensor Usage [24 Hours]
                            </div>
                        </div>
                        <div class="widget-body">
                            <section id="globalUsageChart" class="chart-height-lg"></section>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                    <div class="widget">
                        <div class="widget-header">
                            <div class="title">
                                Hourly Sensor Usage [24 Hours]
                            </div>
                        </div>
                        <div class="widget-body">
                            <section id="hourlyUsageChart" class="chart-height-lg"></section>
                        </div>
                    </div>
                </div>            
            </div>
          <!-- Row End -->
          
        </div>
        <!-- Dashboard Wrapper End -->

        <footer>
          <p>Heimdall - ©NordStar 2016</p>
        </footer>

      </div>
    </div>
    <!-- Main Container end -->

    <script src="js/jquery/jquery-2.1.0.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.scrollUp.js"></script>
    
    <!-- jQuery UI JS -->
    <script src="js/jquery-ui-v1.10.3.js"></script>

    <!-- Custom JS -->
    <script src="js/menu.js"></script>
    <script src="js/nexfidelitas/stats.js"></script>

    <script src="js/jquery/jquery-2.1.0.js"></script>
    <script src="js/zingchart/zingchart.min.js"></script>
    <script src="js/zingchart/zingchart.jquery.js"></script>
    <script src="js/heimdall/dashboard.js"></script>
    
  </body>
</html>