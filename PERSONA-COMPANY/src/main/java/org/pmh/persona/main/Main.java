package org.pmh.persona.main;

//   Standard Libraries Imports

//   Third Party Libraries Imports
import javax.sql.DataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

//   FENIX Framework Imports

//   Application Domain Imports


/**
 * Main.java<br/><br/>
 * Creation Date 2015-02-17 21:51<br/><br/>
 * <b>DESCRIPTION:</b><br/><br/>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br/>
 * Version Date: 2015-02-17 21:51<br/>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2015-02-17 21:51
 */
@EnableAutoConfiguration                                                   // This annotation tells Spring to auto-wire your application
@ComponentScan ( basePackages = { "org.pmh.persona.company.controller" } ) // This annotation tells Spring to look for controllers, etc. starting in the current package
@Configuration                                                             // This annotation tells Spring that this class contains configuration information for the application.
public class Main extends SpringBootServletInitializer {
    
    private static final String MAX_REQUEST_SIZE = "10MB";
        
    @Bean
//    @ConfigurationProperties ( prefix="datasource.persona.person" )
    public DataSource personaDataSource ( ) {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource ( );

        dataSource.setDriverClass ( com.mysql.jdbc.Driver.class                   );
        dataSource.setUsername    ( "personauser"                                 );
        dataSource.setUrl         ( "jdbc:mysql://localhost:3306/persona_company" );
        dataSource.setPassword    ( "p3r50n4"                                     );

        return dataSource;
    }

    @Override
    protected SpringApplicationBuilder configure ( SpringApplicationBuilder application ) {
        return application.sources ( Main.class );
    }
    
    public static void main ( String [ ] args ) throws Exception {
        SpringApplication.run ( Main.class, args );
    }
}
