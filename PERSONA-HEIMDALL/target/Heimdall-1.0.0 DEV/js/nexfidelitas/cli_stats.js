var $grid_color    = "#eee";
var $border_color  = "#e1e8ed";
var $default_black = "#666";
var $red           = "#3693cf";

$( function ( ) {

    var cardApplications       = $( '#cardApplications'        );
    var applicationOrigins     = $( '#applicationOrigins'      );
    var applicationDemography  = $( '#applicationDemography'   );
    var applicationPreferences = $( '#applicationPreferences'  );

    initDashboard ( );
    
    function initDashboard ( ) {
//   FLOT GRAPHS - PWNED
        $.get( ( "fiClientAPI/1.0/kpi/cli_cardApplications"       ), doCardApplications       );
        $.get( ( "fiClientAPI/1.0/kpi/cli_applicationOrigins"     ), doApplicationOrigins     );
        $.get( ( "fiClientAPI/1.0/kpi/cli_cardConsumption"        ), doCardsConsumption       );
        $.get( ( "fiClientAPI/1.0/kpi/cli_applicationPreferences" ), doApplicationPreferences );

// JustGage Pwned
        $.get( ( "fiClientAPI/1.0/kpi/cli_applicationDemography"  ), doApplicationDemography  );

    }

    // Card Applications
    function doCardApplications ( jsonData ) {

        var d1 = new Array ( );
        var data;
        var chartOptions;
      
        for ( var i = 0; i < jsonData.length; i++ ) {
            var dataPoint = new Array ( );
            dataPoint.push ( jsonData [ i ].milliseconds );
            dataPoint.push ( jsonData [ i ].count );
          
            d1.push ( dataPoint ) ;
        }

        data = [ { label: "Solicitudes",    //   Series label
                 data: d1 } ];

        var maxDate = d1 [ d1.length - 1 ][ 0 ];  
        var minDate = d1 [ 0 ][ 0 ];

      chartOptions = {
        xaxis: {
          min: minDate,
          max: maxDate,
          mode: "time",
          timeformat: "%m/%d",
//          tickSize: [ 1, "Fecha" ],
//          monthNames: [ "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic" ],
          tickLength: 0
        },
        yaxis: {

        },
        series: {
          lines: {
            show: true, 
    //        fill: true,
            fill: 0.1,
            lineWidth: 2
          },
          points: {
            show: true,
            radius: 5,
            fill: true,
            fillColor: "#ffffff",
            lineWidth: 2
          }
        },
        grid:{
          hoverable: true,
          clickable: false,
          borderWidth: 0,
          tickColor: "#eee",
          borderColor: "#ccc"
        },
        legend: {
          show: true,
          position: 'nw'
        },
        tooltip: true,
        tooltipOpts: {
          content: '%s: %y'
        },
        shadowSize: 0,
        colors: [ '#058DC7', '#666666', '#333333', '#CCCCCC' ]
      };

      var holder = cardApplications;

      if (holder.length) {
        $.plot(holder, data, chartOptions);
      }
    }

    // Applications Origins
    function doApplicationOrigins ( jsonData ) {
        var data = new Array ( );
        var chartOptions;

//        data = [ { label: "Web",    data: 42 }, 
//                 { label: "Tablet", data:  9 } ];

        for ( var i = 0; i < jsonData.length; i++ ) {
            var dataPoint = new Object ( );
            dataPoint.label = jsonData [ i ].recordOrigin;
            dataPoint.data  = jsonData [ i ].originCount;
          
            data.push ( dataPoint ) ;
        }

        chartOptions = {
            series: {
                pie: {
                    show: true,  
                    innerRadius: .6, 
                    stroke: {
                        width: 0
                    }
                }
            }, 
            legend: {
                show: true
            },

            tooltip: true,

            tooltipOpts: {
                content: '%s: %y'
            },

            grid: {
                hoverable:   true,
                clickable:   false,
                borderWidth: 1,
                tickColor:   $border_color,
                borderColor: $grid_color
            },
            shadowSize: 0,
            colors:     [ '#5cb85c', 
                          '#058DC7', 
                          '#999999', 
                          '#CCCCCC' ]
        };

        var holder = applicationOrigins;

        if ( holder.length ) {
            $.plot(holder, data, chartOptions );
        } 
    }

    // Applications Demography
    function doApplicationDemography ( jsonData ) {
        var data = new Array ( );
        var chartOptions;

        for ( var i = 0; i < jsonData.length; i++ ) {
            var dataPoint = new Object ( );
            dataPoint.label = jsonData [ i ].gender;
            dataPoint.data  = jsonData [ i ].genderCount;
          
            data.push ( dataPoint ) ;
        }

        chartOptions = {
            series: {
                pie: {
                    show: true,  
                    innerRadius: .6, 
                    stroke: {
                        width: 0
                    }
                }
            }, 
            legend: {
                show: true
            },

            tooltip: true,

            tooltipOpts: {
                content: '%s: %y'
            },

            grid: {
                hoverable:   true,
                clickable:   false,
                borderWidth: 1,
                tickColor:   $border_color,
                borderColor: $grid_color
            },
            shadowSize: 0,
            colors:     [ '#5cb85c', 
                          '#058DC7', 
                          '#999999', 
                          '#CCCCCC' ]
        };

        var holder = applicationDemography;

        if ( holder.length ) {
            $.plot(holder, data, chartOptions );
        } 
    }

    // Applications Preferences
    function doApplicationPreferences ( jsonData ) {
        var data = new Array ( );
        var chartOptions;

        for ( var i = 0; i < jsonData.length; i++ ) {
            var dataPoint = new Object ( );
            dataPoint.label = jsonData [ i ].preference;
            dataPoint.data  = jsonData [ i ].preferenceCount;
          
            data.push ( dataPoint ) ;
        }

        chartOptions = {
            series: {
                pie: {
                    show: true,  
                    innerRadius: .6, 
                    stroke: {
                        width: 0
                    }
                }
            }, 
            legend: {
                show: true
            },

            tooltip: true,

            tooltipOpts: {
                content: '%s: %y'
            },

            grid: {
                hoverable:   true,
                clickable:   false,
                borderWidth: 1,
                tickColor:   $border_color,
                borderColor: $grid_color
            },
            shadowSize: 0,
            colors:     [ '#5cb85c', 
                          '#058DC7', 
                          '#999999', 
                          '#CCCCCC' ]
        };

        var holder = applicationPreferences;

        if ( holder.length ) {
            $.plot(holder, data, chartOptions );
        } 
    }

    // Cards Consumption
    function doCardsConsumption ( jsonData ) {

        var cons = jsonData.printedCardsCount;

        // JustGage
        var cardsConsumption =  new JustGage ( {
                                        id:              "cardsConsumption",
                                        value:           cons,
                                        min:             0,
                                        max:             1000,
                                        gaugeWidthScale: 1,
                                        counter:         true,
                                        formatNumber:    true,
                                        gaugeColor:      $grid_color,
                                        //levelColorsGradient: false, 
                                        levelColors:     [ "#5cb85c",  "#5cb85c", "#5cb85c", "#ffff00", "#ff0000" ],
                                        label:           "Tarjetas Cons."
                                    } );

    }

} );
