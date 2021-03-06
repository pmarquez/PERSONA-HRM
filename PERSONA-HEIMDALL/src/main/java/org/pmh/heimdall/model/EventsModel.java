
package org.pmh.heimdall.model;


//   Standard Libraries Imports
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
import org.pmh.heimdall.process.DashboardUsageDataRec;

//   Domain Imports
import org.pmh.heimdall.process.EventRec;
import org.pmh.heimdall.process.EventShortRec;

/**
 * EventsModel.java<br><br>
 * Created on 2016-04-24 20:32<br><br>
 * <b>DESCRIPTION:</b><br><br>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br>
 * 2016-04-24 20:32<br>
 *Paulo Márquez </td>
 *<td width="80%"><p>Creación</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2016-04-24 20:32
 */
public class EventsModel {

    /**
     * Retrieves the names of the sensors with reported activity within the required <code>hoursInterval</code>.
     * @param hoursInterval
     * @param ds
     * @return List<String>
     */
    public static List<String> retrieveSensorsUsedInTimeRange ( int hoursInterval, DataSource ds ) {

        String SQLQuery = "SELECT IFNULL(hei_sensorentity.sensorName,'') AS SENSOR_NAME "                                       +

                          "FROM hei_evententity "                                                                               +

                          "LEFT OUTER JOIN hei_sensorentity ON hei_sensorentity.sensorTagCode = hei_evententity.sensorTagCode " +

                          "WHERE TIMESTAMP >= DATE_SUB(NOW(), INTERVAL " + hoursInterval + " HOUR) "                            +

                          "GROUP BY SENSOR_NAME "                                                                               +

                          "ORDER BY SENSOR_NAME";
        
        JdbcTemplate jdbcTemplate = new JdbcTemplate ( ds );

        List<String> sensors = new ArrayList<> ( );

        try {
            sensors = jdbcTemplate.query ( SQLQuery,
                                        new RowMapper<String> ( ) {
                                            @Override
                                            public String mapRow ( ResultSet rs, int rowNum ) throws SQLException {

                                                return rs.getString ( "SENSOR_NAME" );
                                            }
                                        } );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ EventsModel.retrieveSensorsUsedInTimeRange: " + ex.getMessage ( ) );
        }

        return sensors;
    }

    /**
     * Retrieves the Global Sensor Usage Count.
     * @param hoursInterval
     * @param ds
     * @return List<DashboardUsageDataRec>
     */
    public static List<DashboardUsageDataRec> retrieveGlobalSensorUsage ( int hoursInterval, DataSource ds ) {

        String SQLQuery = "SELECT IFNULL(hei_sensorentity.sensorName,'') AS SENSOR_NAME, "                                      +
                                 "COUNT(hei_evententity.sensorTagCode) AS USE_COUNT "                                           +

                          "FROM hei_evententity "                                                                               +

                          "LEFT OUTER JOIN hei_sensorentity ON hei_sensorentity.sensorTagCode = hei_evententity.sensorTagCode " +

                          "WHERE TIMESTAMP >= DATE_SUB(NOW(), INTERVAL " + hoursInterval + " HOUR) "                            +

                          "GROUP BY hei_evententity.sensorTagCode "                                                             +

                          "ORDER BY SENSOR_NAME";

        JdbcTemplate jdbcTemplate = new JdbcTemplate ( ds );

        List<DashboardUsageDataRec> l = new ArrayList<> ( );

        try {
            l = jdbcTemplate.query ( SQLQuery,
                                        new RowMapper<DashboardUsageDataRec> ( ) {
                                            @Override
                                            public DashboardUsageDataRec mapRow ( ResultSet rs, int rowNum ) throws SQLException {

                                                DashboardUsageDataRec r = new DashboardUsageDataRec ( );

                                                   r.setSensorName              ( rs.getString ( "SENSOR_NAME" ) );
                                                   r.setUseCount                ( rs.getInt    ( "USE_COUNT"   ) );

                                                return r;
                                            }
                                        } );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ EventsModel.retrieveGlobalSensorUsage: " + ex.getMessage ( ) );
        }

        return l;
    }

    /**
     * Retrieves the Global Sensor Usage Count.
     * @param hoursInterval
     * @param ds
     * @return List<DashboardUsageDataRec>
     */
    public static List<DashboardUsageDataRec> retrieveHourlySensorUsage ( int hoursInterval, DataSource ds ) {

        String SQLQuery = "SELECT IFNULL(hei_sensorentity.sensorName,'') AS SENSOR_NAME, "                                      +
                                 "COUNT(*) AS USE_COUNT, "                                                                      +
                                 "HOUR(TIMESTAMP) AS HOUR, "                                                                    +
                                 "DATE_FORMAT(TIMESTAMP,'%Y-%m_%d %H') AS HOURLY_GAP "                                          +

                          "FROM hei_evententity "                                                                               +

                          "LEFT OUTER JOIN hei_sensorentity ON hei_sensorentity.sensorTagCode = hei_evententity.sensorTagCode " +

                          "WHERE TIMESTAMP >= DATE_SUB(NOW(), INTERVAL " + hoursInterval + " HOUR) "                            +

                          "GROUP BY HOUR, hei_sensorentity.sensorName "                                                         +

                          "ORDER BY SENSOR_NAME, HOURLY_GAP";

        JdbcTemplate jdbcTemplate = new JdbcTemplate ( ds );

        List<DashboardUsageDataRec> l = new ArrayList<> ( );

        try {
            l = jdbcTemplate.query ( SQLQuery,
                                        new RowMapper<DashboardUsageDataRec> ( ) {
                                            @Override
                                            public DashboardUsageDataRec mapRow ( ResultSet rs, int rowNum ) throws SQLException {

                                                DashboardUsageDataRec r = new DashboardUsageDataRec ( );

                                                   r.setSensorName              ( rs.getString ( "SENSOR_NAME" ) );
                                                   r.setUseCount                ( rs.getInt    ( "USE_COUNT"   ) );
                                                   r.setHour                    ( rs.getString ( "HOUR"        ) );

                                                return r;
                                            }
                                        } );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ EventsModel.retrieveHourlySensorUsage: " + ex.getMessage ( ) );
        }

        return l;
    }

    /**
     * Retrieves a list of events (EventRec) identified by "companyCode" from storage.
     * @param ds
     * @param companyCode The code of the company we want events from (MULTITENANCY)
     * @return an instance of EventRec
     */
    public static List<EventRec> retrieveCompanyEvents ( int companyCode, DataSource ds ) {

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

                          "WHERE hei_evententity.companyCode = " + companyCode;
      
        System.out.println ( "retrieveCompanyEvents.SQLQuery: " + SQLQuery );

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
            System.err.println ( "DataAccessException @ EventsModel.retrieveCompanyEvents: " + ex.getMessage ( ) );
        }

        return l;
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

    static String SQLEventInsertQuery = "INSERT INTO hei_evententity ( personCode, "                           +
                                                                       "sensorTagCode, "                       +
                                                                       "timestamp ) VALUES ( :personCode, "    +
                                                                                            ":sensorTagCode, " +
                                                                                            ":timestamp );";
    
    /**
     * Persists an EventRec to storage
     * @param ds The Data Source to use
     * @param r The EventRec to persist 
     */
    public static void persistEvent ( EventShortRec r, DataSource ds ) {

        String SQLQuery = null;
        
        SQLQuery = SQLEventInsertQuery;

        Map<String,Object> bind = new HashMap<>( );

//                           bind.put ( "eventCode",  r.getEventCode    ( ) );
                           bind.put ( "personCode",    "1"                    );
                           bind.put ( "sensorTagCode", r.getSensorTagCode ( ) );
                           bind.put ( "timestamp",     LocalDateTime.now   ( ).format ( DateTimeFormatter.ofPattern ( "yyyy-MM-dd HH:mm:ss" ) ) );

        SqlParameterSource paramSource = new MapSqlParameterSource ( bind );
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate ( ds );

        try {
            jdbcTemplate.update ( SQLQuery, paramSource );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ EventsModel.persistEvent: " + ex.getMessage ( ) );
        }
        
    }

}
