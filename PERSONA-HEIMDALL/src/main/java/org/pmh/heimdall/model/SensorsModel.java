
package org.pmh.heimdall.model;


//   Standard Libraries Imports
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import java.time.LocalDateTime;

//   Third Party Libraries Imports
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

//   FENIX Framework Imports
import java.util.ArrayList;
import java.util.List;

//   Domain Imports
import org.pmh.heimdall.process.EventRec;
import org.pmh.heimdall.sensor.SensorRec;

/**
 * SensorsModel.java<br><br>
 * Created on 2016-05-02 11:39<br><br>
 * <b>DESCRIPTION:</b><br><br>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br>
 * 2016-05-02 11:39<br>
 *Paulo Márquez </td>
 *<td width="80%"><p>Creación</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2016-05-02 11:39
 */
public class SensorsModel {

    /**
     * Retrieves a list of events (EventRec) identified by "companyCode" from storage.
     * @param ds
     * @param sensorTagCode The sensor identifier.
     * @return an instance of SensorRec
     */
    public static SensorRec retrieveSensorData ( String sensorTagCode, DataSource ds ) {

        String SQLQuery = "SELECT hei_sensorentity.sensorCode, "                                                                           +
                                 "IFNULL(hei_sensorentity.sensorName,'') AS SENSOR_NAME, "                                                 +
                                 "IFNULL(hei_sensorentity.sensorID,'') AS SENSOR_ID, "                                                     +
                                 "IFNULL(hei_sensorentity.sensorNamespace,'') AS SENSOR_NAMESPACE, "                                       +
                                 "IFNULL(hei_sensorentity.sensorTagCode,'') AS SENSOR_TAG_CODE, "                                          +
                                 "hei_sensorentity.sensorTypeCode, "                                                                       +
                                 "IFNULL(hei_sensortypeentity.sensorTypeName,'') AS SENSOR_TYPE_NAME, "                                    +
                                 "hei_sensorentity.active "                                                                                +

                          "FROM hei_sensorentity "                                                                                         +

                          "LEFT OUTER JOIN hei_sensortypeentity ON hei_sensortypeentity.sensorTypeCode = hei_sensorentity.sensorTypeCode " +

                          "WHERE  hei_sensorentity.sensorTagCode = '" + sensorTagCode + "' AND "                                           +
                                 "hei_sensorentity.active = 1";
      
        System.out.println ( "retrieveCompanyEvents.SQLQuery: " + SQLQuery );

        JdbcTemplate jdbcTemplate = new JdbcTemplate ( ds );

        SensorRec r = new SensorRec ( );

        try {
            r = jdbcTemplate.queryForObject ( SQLQuery,
                                        new RowMapper<SensorRec> ( ) {
                                            @Override
                                            public SensorRec mapRow ( ResultSet rs, int rowNum ) throws SQLException {

                                                SensorRec r = new SensorRec ( );

                                                   r.setSensorCode                  ( rs.getInt     ( "sensorCode"       ) );
                                                   r.setSensorName                  ( rs.getString  ( "SENSOR_NAME"      ) );
                                                   r.setSensorID                    ( rs.getString  ( "SENSOR_ID"        ) );
                                                   r.setSensorNamespace             ( rs.getString  ( "SENSOR_NAMESPACE" ) );
                                                   r.setSensorTagCode               ( rs.getString  ( "SENSOR_TAG_CODE"  ) );
                                                   r.setSensorTypeCode              ( rs.getInt     ( "sensorTypeCode"   ) );
                                                   r.setSensorTypeName              ( rs.getString  ( "SENSOR_TYPE_NAME" ) );

                                                return r;
                                            }
                                        } );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ SensorsModel.retrieveSensorData: " + ex.getMessage ( ) );
        }

        return r;
    }

    /**
     * Retrieves a list of events (EventRec) related to "personCode" in "companyCode" from storage.
     * @param ds
     * @param personCode The code of the person we want events from
     * @param companyCode The code of the company we want events from (MULTITENANCY)
     * @return an instance of PersonShortRec
     */
    public static List<EventRec> retrievePersonEvents ( int companyCode, int personCode, DataSource ds ) {

        String SQLQuery = "SELECT hei_evententity.eventCode, "                                                                             +
                                 "hei_evententity.companyCode, "                                                                           +
                                 "hei_evententity.personCode, "                                                                            +
                                 "hei_evententity.sensorCode, "                                                                            +
                                 "IFNULL(hei_sensorentity.sensorName,'') AS SENSOR_NAME, "                                                 +
                                 "IFNULL(hei_evententity.sensorTagCode,'') AS SENSOR_TAG_CODE, "                                           +
                                 "hei_sensorentity.sensorTypeCode, "                                                                       +
                                 "IFNULL(hei_sensortypeentity.sensorTypeName,'') AS SENSOR_TYPE_NAME, "                                    +
                                 "hei_evententity.timestamp "                                                                              +

                          "FROM hei_evententity "                                                                                          +

                          "LEFT OUTER JOIN hei_sensorentity ON hei_sensorentity.sensorCode = hei_evententity.eventCode "                   +
                          "LEFT OUTER JOIN hei_sensortypeentity ON hei_sensortypeentity.sensorTypeCode = hei_sensorentity.sensorTypeCode " +

                          "WHERE hei_evententity.companyCode = " + companyCode + " AND "                                                   + 
                          "WHERE hei_evententity.personCode = "  + personCode;
      
        System.out.println ( "retrievePersonEvents.SQLQuery: " + SQLQuery );

        JdbcTemplate jdbcTemplate = new JdbcTemplate ( ds );

        List<EventRec> l = new ArrayList<> ( );

        try {
            l = jdbcTemplate.query ( SQLQuery,
                                        new RowMapper<EventRec> ( ) {
                                            @Override
                                            public EventRec mapRow ( ResultSet rs, int rowNum ) throws SQLException {

                                                EventRec r = new EventRec ( );

//                                                   r.setEventCode                   ( rs.getInt     ( "eventCode"        ) );
//                                                   r.setCompanyCode                 ( rs.getInt     ( "companyCode"      ) );
//                                                   r.setPersonCode                  ( rs.getInt     ( "personCode"       ) );
                                                   r.setSensorCode                  ( rs.getInt     ( "sensorCode"       ) );
                                                   r.setSensorName                  ( rs.getString  ( "SENSOR_NAME"      ) );
//                                                   r.setSensorTagCode               ( rs.getString  ( "SENSOR_TAG_CODE"  ) );
                                                   r.setSensorTypeCode              ( rs.getInt     ( "sensorTypeCode"   ) );
                                                   r.setSensorTypeName              ( rs.getString  ( "SENSOR_TYPE_NAME" ) );
//                                                   r.setTimestamp                   ( rs.getString  ( "timestamp"        ) );

                                                return r;
                                            }
                                        } );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ EventsModel.retrievePersonEvents: " + ex.getMessage ( ) );
        }

        return l;
    }

    static String SQLEventInsertQuery = "INSERT INTO hei_evententity ( eventCode, "                         +
                                                                       "personCode, "                       +
                                                                       "sensorCode, "                       +
                                                                       "timestamp ) VALUES ( :eventCode, "  +
                                                                                            ":personCode, " +
                                                                                            ":sensorCode, " +
                                                                                            ":timestamp );";
    
    /**
     * Persists an EventRec to storage
     * @param ds The Data Source to use
     * @param r The EventRec to persist 
     */
    public static void persistEvent ( EventRec r, DataSource ds ) {

        String SQLQuery = null;
        
        SQLQuery = SQLEventInsertQuery;

        Map<String,Object> bind = new HashMap<>( );

//                           bind.put ( "eventCode",  r.getEventCode    ( ) );
//                           bind.put ( "personCode", r.getPersonCode   ( ) );
                           bind.put ( "sensorCode", r.getSensorCode   ( ) );
                           bind.put ( "timestamp",  LocalDateTime.now ( ) );

        SqlParameterSource paramSource = new MapSqlParameterSource ( bind );
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate ( ds );

        try {
            jdbcTemplate.update ( SQLQuery, paramSource );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ EventsModel.persistEvent: " + ex.getMessage ( ) );
        }
        
    }

}
