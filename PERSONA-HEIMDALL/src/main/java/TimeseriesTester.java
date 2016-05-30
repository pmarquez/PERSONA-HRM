
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pmarquez
 */
public class TimeseriesTester {

    /**
     * @param args the command line arguments
     */
    public static void _main_(String[] args) {
        
        LocalTime lt = LocalTime.now ( );
        lt = lt.minusHours ( 24 - 1 );
        
        for ( int i = 0; i < 24; i++ ) {
            System.out.println ( lt.format ( DateTimeFormatter.ofPattern ( "HH" ) ) );
            
            lt = lt.plusHours ( 1L );
        }
    }
    
}
