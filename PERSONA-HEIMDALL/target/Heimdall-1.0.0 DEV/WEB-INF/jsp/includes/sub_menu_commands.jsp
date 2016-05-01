<%@page import="com.fxt.navigation.NavCommandRec"%>
<%@page import="com.fxt.navigation.NavSectionRec"%>
<%@page import="java.util.List"%>
<%@page import="com.fxt.navigation.NavigationRec"%>
<%
   NavigationRec nav = ( NavigationRec ) session.getAttribute ( "nav" );
%>
        <div class="sub-nav hidden-sm hidden-xs">
          <ul>
            <li><a href="" class="heading"><%= nav.getCurrentSection ( ).getSubMenuLabel ( ) %></a></li>
<% 
   List<NavCommandRec> lncr = nav.getCurrentSection ( ).getCommands ( );
   for( int i = 0; i < lncr.size ( ); i++ ) {
       NavCommandRec ncr = lncr.get( i );
%>              
            <li class="<%= ncr.getCommandClass ( ) %>">
              <a href="commands?c=<%= ncr.getControllerCommand ( ) %>" <%= ( ncr == nav.getCurrentCommand ( ) ) ? "class=\"selected\"" : "" %>><%= ncr.getCommandName ( ) %></a>
            </li>
<% 
   } 
%>            
          </ul>
        </div>
