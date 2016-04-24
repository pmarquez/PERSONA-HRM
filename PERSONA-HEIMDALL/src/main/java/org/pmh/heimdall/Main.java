
package org.pmh.heimdall;

//   Standard Libraries Imports
import javax.servlet.MultipartConfigElement;
import javax.sql.DataSource;

//   Third Party Libraries Imports
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

//   FENIX Framework Imports


//   Domain Imports


/**
 * Main.java<br><br>
 * Creation Date 2016-04-24 11:44<br><br>
 * <b>DESCRIPTION:</b><br><br>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br>
 * Version Date: 2016-04-24 11:44<br>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2016-06-04-24 11:44
 */
@EnableAutoConfiguration                                            // This annotation tells Spring to auto-wire your application
@ComponentScan(basePackages = { "org.pmh.heimdall.controller" } )   // This annotation tells Spring to look for controllers, etc. starting in the current package
@Configuration                                                      // This annotation tells Spring that this class contains configuration information for the application.
public class Main extends SpringBootServletInitializer {
    
    private static final String MAX_REQUEST_SIZE = "10MB";
    
    @Bean
//    @ConfigurationProperties(prefix="datasource.heimdall")
    public DataSource aexandriaDataSource ( ) {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource ( );

        dataSource.setDriverClass ( com.mysql.jdbc.Driver.class                    );
        dataSource.setUsername    ( "heimdalluser"                                 );
        dataSource.setUrl         ( "jdbc:mysql://localhost:3306/persona_heimdall" );
        dataSource.setPassword    ( "h31md4ll"                                     );

        return dataSource;
    }
    
    @Override
    protected SpringApplicationBuilder configure ( SpringApplicationBuilder application ) {
        return application.sources ( Main.class );
    }
        
    public static void main(String[] args) throws Exception {
        SpringApplication.run ( Main.class, args );
    }

}
