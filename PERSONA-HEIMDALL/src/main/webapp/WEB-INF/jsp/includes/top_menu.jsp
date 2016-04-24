<%@page import="com.ng.nexfi.auth.LoginRec"%>
<%
    LoginRec lr = ( LoginRec ) session.getAttribute ( "loginData" );
%>
    <header>
      <a href="index.html" class="logo">
        <img src="images/logo.png" class="logoImage" alt="Logo"/>
      </a>
      <div class="pull-right">
        <ul id="mini-nav" class="clearfix">
          <li class="list-box user-profile">
            <a id="drop7" href="#" role="button" class="dropdown-toggle user-avtar" data-toggle="dropdown">
                <img src="img/user<%= lr.getUserCode ( ) %>.png" alt="<%= lr.getFirstName() + "  " + lr.getLastName ( ) %>">
            </a>
            <ul class="dropdown-menu server-activity">
              <li>
                <p><i class="fa fa-cog text-info"></i> Configuración de Cuenta</p>
              </li>
              <li>
                  <form name="logoutForm" action="signout">
                      <div class="demo-btn-group clearfix">
                          <button type="submit" class="btn btn-danger">
                          Salir
                        </button>
                      </div>
                  </form>  
              </li>
            </ul>
          </li>
        </ul>
      </div>
    </header>