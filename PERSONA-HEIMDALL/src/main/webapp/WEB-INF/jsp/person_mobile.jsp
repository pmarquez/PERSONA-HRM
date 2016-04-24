<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@page import="com.fxt.util.HtmlListElement"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
  <head>
    <title>nexfidelitas, by nexglobal</title>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
    <meta name="description" content="Blue Moon - Responsive Admin Dashboard" />
    <meta name="keywords" content="Notifications, Admin, Dashboard, Bootstrap3, Sass, transform, CSS3, HTML5, Web design, UI Design, Responsive Dashboard, Responsive Admin, Admin Theme, Best Admin UI, Bootstrap Theme, Wrapbootstrap, Bootstrap, bootstrap.gallery" />
    <meta name="author" content="Bootstrap Gallery" />
    <link rel="shortcut icon" href="img/favicon.png">

    <link href="css/bootstrap_mobile.css" rel="stylesheet">
    <link href="css/new_mobile.css" rel="stylesheet">
    <!-- Important. For Theming change primary-color variable in main.css  -->

    <link href="fonts/font-awesome.min.css" rel="stylesheet">

    <!-- HTML5 shiv and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="js/html5shiv.js"></script>
      <script src="js/respond.min.js"></script>
    <![endif]-->

    <script src="js/nexfidelitas/ganalytics.js"></script>
  </head>

  <body>
<%
    List<HtmlListElement> days        = ( List<HtmlListElement> ) request.getAttribute ( "days"        );
    List<HtmlListElement> months      = ( List<HtmlListElement> ) request.getAttribute ( "months"      );
    List<HtmlListElement> years       = ( List<HtmlListElement> ) request.getAttribute ( "years"       );
    List<HtmlListElement> countries   = ( List<HtmlListElement> ) request.getAttribute ( "countries"   );
    List<HtmlListElement> authonomies = ( List<HtmlListElement> ) request.getAttribute ( "authonomies" );
    List<HtmlListElement> provinces   = ( List<HtmlListElement> ) request.getAttribute ( "provinces"   );
    
    String personCode = ( request.getParameter ( "c" ) != null ) ? request.getParameter ( "c" ) : "0";
%>

    <!-- Main Container start -->
    <div class="dashboard-container">

      <div class="container">

        <!-- Dashboard Wrapper Start -->
        <div class="dashboard-wrapper">
          
          <!-- Left Sidebar Start -->
          <div>
        <!-- Row Begins -->
          <form class="form-horizontal no-margin" id="personForm" name="personForm">
          <div class="row">
            <div class="col-lg-12 col-md-12">
              <div class="widget">
                <div class="widget-header">
                    <div class="title" id="formCaptionLabel">
                        Datos Personales
                    </div>
                </div>
                <div class="widget-body">
                      <input type="hidden" id="personCode" name="personCode" value="<%= personCode %>"/>
                      <input type="hidden" id="at" name="at" value="123"/>
                      <div class="form-group">
                        <label for="firstName" class="col-sm-2 control-label">Nombres</label>
                        <div class="col-sm-4">
                          <input type="text" class="form-control" id="firstName" name="firstName" placeholder="Nombres de la Persona">
                        </div>
                      </div>
                      <div class="form-group">
                        <label for="lastName" class="col-sm-2 control-label">Apellidos</label>
                        <div class="col-sm-4">
                          <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Apellidos de la Persona">
                        </div>
                      </div>
                      <div class="form-group">
                        <label for="idNumber" class="col-sm-2 control-label">Fecha Nacimiento</label>
                        <div class="col-sm-10">
                            <div class="row">
                                <div class="col-md-4 col-sm-4 col-xs-4">
                                  <select id="birthDay" class="form-control">
                                    <option value="-1">Dia...</option>
                                    <option value="0">---------</option>
<%
    for ( int idx = 0; idx < days.size ( ); idx++ ) {
        HtmlListElement r = days.get ( idx );
%>
                                    <option value="<%= r.getKey ( ) %>"><%= r.getValue ( ) %></option>
<%
    }
%>
                                  </select>
                                </div>
                                <div class="col-md-4 col-sm-4 col-xs-4">
                                  <select id="birthMonth" class="form-control">
                                    <option value="-1">Mes...</option>
                                    <option value="0">---------</option>
<%
    for ( int idx = 0; idx < months.size ( ); idx++ ) {
        HtmlListElement r = months.get ( idx );
        System.out.println ( r.getValue ( ) );
%>
                                    <option value="<%= r.getKey ( ) %>"><%= r.getValue ( ) %></option>
<%
    }
%>
                                  </select>
                                </div>
                                <div class="col-md-4 col-sm-4 col-xs-4">
                                  <select id="birthYear" class="form-control">
                                    <option value="-1">Año...</option>
                                    <option value="0">---------</option>
<%
    for ( int idx = 0; idx < years.size ( ); idx++ ) {
        HtmlListElement r = years.get ( idx );
%>
                                    <option value="<%= r.getKey ( ) %>"><%= r.getValue ( ) %></option>
<%
    }
%>
                                  </select>
                                </div>
                            </div>
                        </div>
                      </div>
                      <div class="form-group">
                        <label for="email" class="col-sm-2 control-label">Email</label>
                        <div class="col-sm-4">
                          <input type="email" class="form-control" name="email" id="email" placeholder="Email">
                        </div>
                      </div>
                      <div class="form-group">
                        <label for="phone" class="col-sm-2 control-label">Teléfono</label>
                        <div class="col-sm-4">
                          <input type="text" class="form-control" name="phone" id="phone" placeholder="Número Telefónico">
                        </div>
                      </div>
                      <!-- Radios -->
                      <div class="form-group">
                        <label for="email" class="col-sm-2 control-label">Género</label>
                        <div class="col-sm-10">
                              <div class="radio">
                                  <label>
                                      <input type="radio" name="gender" id="female" value="1" />Femenino
                                  </label>
                              </div>
                              <div class="radio">
                                  <label>
                                      <input type="radio" name="gender" id="male" value="2" />Masculino
                                  </label>
                              </div>
                          </div>
                      </div>

                    <div class="clearfix">
                    </div>
                </div>
                <div class="widget-header">
                    <div class="title" id="formCaptionLabel">
                        Dirección Postal
                    </div>
                </div>
                <div class="widget-body">
                      <div class="form-group">
                        <label for="countryCode" class="col-sm-2 control-label">País</label>
                        <div class="col-sm-10">
                          <select id="countryCode" class="form-control">
                            <option value="-1">Seleccione un país...</option>
                            <option value="0">---------</option>
<%
    for ( int idx = 0; idx < countries.size ( ); idx++ ) {
        HtmlListElement r = countries.get ( idx );
%>
                            <option value="<%= r.getKey ( ) %>"><%= r.getValue ( ) %></option>
<%
    }
%>
                          </select>
                        </div>
                      </div>
                      <div class="form-group">
                        <label for="authonomyCode" class="col-sm-2 control-label">Comunidad Autónoma</label>
                        <div class="col-sm-10">
                          <select id="authonomyCode" class="form-control">
                            <option value="-1">Seleccione una comunidad autónoma...</option>
                            <option value="0">---------</option>
<%
    for ( int idx = 0; idx < authonomies.size ( ); idx++ ) {
        HtmlListElement r = authonomies.get ( idx );
%>
                            <option value="<%= r.getKey ( ) %>"><%= r.getValue ( ) %></option>
<%
    }
%>
                          </select>
                        </div>
                      </div>
                      <div class="form-group">
                        <label for="provinceCode" class="col-sm-2 control-label">Provincia</label>
                        <div class="col-sm-10">
                          <select id="provinceCode" class="form-control">
                            <option value="-1">Seleccione una provincia...</option>
                            <option value="0">---------</option>
<%
    for ( int idx = 0; idx < provinces.size ( ); idx++ ) {
        HtmlListElement r = provinces.get ( idx );
%>
                            <option value="<%= r.getKey ( ) %>"><%= r.getValue ( ) %></option>
<%
    }
%>
                          </select>
                        </div>
                      </div>
                      <div class="form-group">
                        <label for="firstName" class="col-sm-2 control-label">Dirección</label>
                        <div class="col-sm-10">
                          <input type="text" class="form-control" id="firstName" name="firstName" placeholder="Nombres de la Persona">
                        </div>
                      </div>
                      <div class="form-group">
                        <label for="lastName" class="col-sm-2 control-label">Zona</label>
                        <div class="col-sm-6">
                          <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Apellidos de la Persona">
                        </div>
                      </div>
                      <div class="form-group">
                        <label for="phone" class="col-sm-2 control-label">Ciudad</label>
                        <div class="col-sm-6">
                          <input type="text" class="form-control" name="phone" id="phone" placeholder="Número Telefónico">
                        </div>
                      </div>
                      <div class="form-group">
                        <label for="lastName" class="col-sm-2 control-label">Código Postal</label>
                        <div class="col-sm-3">
                          <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Apellidos de la Persona">
                        </div>
                      </div>
                    <div class="clearfix">
                    </div>
                </div>
                <div class="widget-header">
                    <div class="title" id="formCaptionLabel">
                        Preferencias
                    </div>
                </div>
                <div class="widget-body">
                      <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <div class="row">
<!--   for ( ) {   -->
                                <div class="col-md-4 col-sm-4 col-xs-4">
                                    <div class="checkbox">
                                      <label>
                                        <input type="checkbox"> Ortopedia
                                      </label>
                                    </div>
                                </div>
<!--   }   -->
                            </div>
                        </div>
                      </div>
                    <div class="clearfix">
                    </div>
                </div>
                <div class="widget-header">
                    <div class="title" id="formCaptionLabel">
                      <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                          <button type="submit" class="btn btn-info" id="btnProcessForm">Procesar</button>
                        </div>
                      </div>
                    </div>
                </div>
                          
              </div>
            </div>
          </div>
          <!-- Row Ends -->
          </form>
          </div>
          <!-- Left Sidebar End -->
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
    <script src="js/nexads/utils.js"></script>
    <script src="js/nexads/person.js"></script>

    <!-- Custom JS -->
    <script src="js/menu.js"></script>

    <script type="text/javascript">
  //Tooltip
      $('a').tooltip('hide');
  //Popover
      $('.popover-pop').popover('hide');
  //Dropdown
      $('.dropdown-toggle').dropdown();
    </script>

  </body>
</html>