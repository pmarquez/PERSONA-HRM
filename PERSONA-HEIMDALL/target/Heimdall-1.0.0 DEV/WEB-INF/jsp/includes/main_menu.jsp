<%@page import="com.fxt.navigation.NavSectionRec"%>
<%@page import="java.util.List"%>
<%@page import="com.fxt.navigation.NavigationRec"%>
<%
   NavigationRec nav = ( NavigationRec ) session.getAttribute ( "nav" );
%>
        <div id="cssmenu">
          <ul>
<% 
   List<NavSectionRec> lnsr = nav.getSections ( );
   for( int i = 0; i < lnsr.size ( ); i++ ) {
       NavSectionRec nsr = lnsr.get( i );
%>              
            <li <%= ( nsr == nav.getCurrentSection ( ) ) ? "class=\"active\"" : "" %> >
              <a href="sections?s=<%= nsr.getControllerCommand ( ) %>">
                <i class="<%= nsr.getSectionClass ( ) %>"></i>
                <%= nsr.getSectionName ( ) %>
              </a>
            </li>
<% } %>            
          </ul>
        </div>