
package org.pmh.heimdall.model;


//   Standard Libraries Imports
import java.text.*;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//   Third Party Libraries Imports
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

//   FENIX Framework Imports
import com.fxt.address.PostCodeRulesRec;
import com.fxt.util.HtmlListElement;
import com.fxt.validations.IssueRec;

//   Domain Imports


/**
 * UtilsModel.java<br/><br/>
 * Created on 2015-07-14 16:44<br/><br/>
 * <b>DESCRIPTION:</b><br/><br/>
 * <p></p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br/>
 * 2015-07-14 16:44<br/>
 *Paulo Márquez </td>
 *<td width="80%"><p>Creación</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez
 * @version 1.0 - 2015-07-14 16:44
 */
public class UtilsModel {

    public static final String LOCALE_SPANISH                = "es-ES";
    public static final String LOCALE_ENGLISH                = "en-GB";
    public static final String LOCALE_RUSSIAN                = "ru-RU";
    public static final String LOCALE_FRENCH                 = "fr-FR";
    public static final String LOCALE_ITALIAN                = "it-IT";

    public static final int    COMPLETE_MONTH_NAME           = 1;
    public static final int    ABBREVIATED_MONTH_NAME        = 2;
    public static final int    INITIAL_MONTH_NAME            = 3;

    public static final String MONTH_NAME_TABLE              = "fxt_monthnameentity";
    public static final String LENGTH_COMPLETE_MONTH_NAME    = "name";
    public static final String LENGTH_ABBREVIATED_MONTH_NAME = "shortName";
    public static final String LENGTH_INITIAL_MONTH_NAME     = "initial";

    public static final int    MIN_AGE                       = 14;
    public static final int    MAX_AGE                       = 120;
    public static final String YEAR_MASK                     = "0000";

   /**
     * Retrieves a list of the active ID Document Types (HtmlListElement) from storage.
     * @param locale
     * @param ds The Data Source to use
     * @return ShuttleRec with an instance of List<HtmlListElement>
     */
    public static List<HtmlListElement> retrieveIDDocTypes ( String locale, DataSource ds ) {

        String SQLQuery =   "SELECT fxt_idtypeentity.idTypeCode AS _KEY, "             +
                                   "IFNULL(fxt_idtypeentity.idTypeName,'') AS _VALUE " +

                            "FROM fxt_idtypeentity "                                   +

                            "WHERE fxt_idtypeentity.active = 1 AND "                   +
                                  "fxt_idtypeentity.locale = '" + locale + "'"         +

                            "ORDER BY _VALUE";
        
        JdbcTemplate jdbcTemplate = new JdbcTemplate ( ds );
        
        List<HtmlListElement> l = new ArrayList<> ( );
        try {
            l = jdbcTemplate.query ( SQLQuery,
                                       new RowMapper<HtmlListElement> ( ) {
                                           @Override
                                           public HtmlListElement mapRow ( ResultSet rs, int rowNum ) throws SQLException {
                                               HtmlListElement le = new HtmlListElement ( );

                                               le.setKey   ( rs.getString ( "_KEY"   ) );
                                               le.setValue ( rs.getString ( "_VALUE" ) );

                                              return le;
                                           }
                                       } );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ UtilsModel.retrieveIDDocTypes: " + ex.getMessage ( ) );
        }

        return l;

    }
    
    /**
     * Retrieves a list of the month names (HtmlListElement) from storage.
     * @param locale The locale of the month names to return (e.g.en-GB)
     * @param length The length of the names [COMPLETE_MONTH_NAME|ABBREVIATED_MONTH_NAME|INITIAL_MONTH_NAME]
     * @param ds The Data Source to use
     * @return ShuttleRec with an instance of List<HtmlListElement>
     */
    public static List<HtmlListElement> retrieveMonthNames ( String locale, int length, DataSource ds ) {

        String monthName = UtilsModel.MONTH_NAME_TABLE + "." + UtilsModel.LENGTH_COMPLETE_MONTH_NAME;
        
        switch ( length ) {
            case UtilsModel.COMPLETE_MONTH_NAME:
                monthName = UtilsModel.MONTH_NAME_TABLE + "." + UtilsModel.LENGTH_COMPLETE_MONTH_NAME;
                break;
                
            case UtilsModel.ABBREVIATED_MONTH_NAME:
                monthName = UtilsModel.MONTH_NAME_TABLE + "." + UtilsModel.LENGTH_ABBREVIATED_MONTH_NAME;
                break;

            case UtilsModel.INITIAL_MONTH_NAME:
                monthName = UtilsModel.MONTH_NAME_TABLE + "." + UtilsModel.LENGTH_INITIAL_MONTH_NAME;
                break;

            default:
                break;

        }
        
        String SQLQuery =   "SELECT fxt_monthnameentity.cardinality AS _KEY, "    +
                                   "IFNULL(" + monthName + ",'') AS _VALUE "      +

                            "FROM fxt_monthnameentity "                           +

                            "WHERE fxt_monthnameentity.locale = '" + locale + "'" +

                            "ORDER BY _KEY";

        JdbcTemplate jdbcTemplate = new JdbcTemplate ( ds );

        List<HtmlListElement> l = new ArrayList<> ( );

        try {
            l = jdbcTemplate.query ( SQLQuery,
                                       new RowMapper<HtmlListElement> ( ) {
                                           @Override
                                           public HtmlListElement mapRow ( ResultSet rs, int rowNum ) throws SQLException {
                                               HtmlListElement le = new HtmlListElement ( );

                                               le.setKey   ( rs.getString ( "_KEY"   ) );
                                               le.setValue ( rs.getString ( "_VALUE" ) );

                                              return le;
                                           }
                                       } );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ UtilsModel.retrieveMonthNames: " + ex.getMessage ( ) );
        }

        return l;

    }

    public static List<HtmlListElement> retrieveNumberSeries ( int startValue, int endValue, String mask ) {
        
        int                   from;
        int                   to;
        List<HtmlListElement> l           = new ArrayList<>   ( );
        DecimalFormat         myFormatter = new DecimalFormat ( mask );

        if ( startValue <= endValue ) {
            for ( int i = startValue; i <= endValue; i++ ) {
                HtmlListElement r = new HtmlListElement ( );
                                r.setKey   ( String.valueOf     ( i ) );
                                r.setValue ( myFormatter.format ( i ) );
                l.add ( r );
            }

        } else {
            for ( int i = startValue; i >= endValue; i-- ) {
                HtmlListElement r = new HtmlListElement ( );
                                r.setKey   ( String.valueOf     ( i ) );
                                r.setValue ( myFormatter.format ( i ) );
                l.add ( r );
            }

        }

        return l;

    }

   /**
     * Retrieves a list of country names (HtmlListElement) from storage.
     * @param locale The locale of the country names to return (e.g.en-GB)
     * @param ds The Data Source to use
     * @return ShuttleRec with an instance of List<HtmlListElement>
     */
    public static List<HtmlListElement> retrieveCountriesList ( String locale, DataSource ds ) {

        String SQLQuery =   "SELECT fxt_countryentity.countryId AS _KEY, "              +
                                   "IFNULL(fxt_countryentity.countryName,'') AS _VALUE, " +
                                   "fxt_countryentity.default, "                          +
                                   "fxt_countryentity.block "                             +

                           "FROM fxt_countryentity "                                      +

                           "WHERE fxt_countryentity.active = 1 AND "                      +
                                 "fxt_countryentity.nameLocale = '" + locale + "' "       +

                           "ORDER BY fxt_countryentity.block, _VALUE";

        JdbcTemplate jdbcTemplate = new JdbcTemplate ( ds );

        List<HtmlListElement> l = new ArrayList<> ( );
        
        int currentBlock = 0;
        
        try {
            l = jdbcTemplate.query ( SQLQuery,
                                       new RowMapper<HtmlListElement> ( ) {
                                           @Override
                                           public HtmlListElement mapRow ( ResultSet rs, int rowNum ) throws SQLException {
                                               HtmlListElement le = new HtmlListElement ( );

                                               le.setKey          ( rs.getString ( "_KEY"     ) );
                                               le.setValue        ( rs.getString ( "_VALUE"   ) );
                                               le.setDefaultValue ( rs.getBoolean ( "default" ) );

                                              return le;
                                           }
                                       } );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ UtilsModel.retrieveCountriesList: " + ex.getMessage ( ) );
        }

        
        
        return l;

    }

   /**
     * Retrieves a list of authonomic community names (Spain) (HtmlListElement) from storage.
     * @param countryCode The code of the country for which the authonomies are to be queried.
     * @param locale The locale of the country names to return (e.g.en-GB)
     * @param ds The Data Source to use
     * @return ShuttleRec with an instance of List<HtmlListElement>
     */
    public static List<HtmlListElement> retrieveAuthonomiesList ( int countryCode, String locale, DataSource ds ) {

        String SQLQuery =   "SELECT fxt_autonomiccommunityentity.acCode AS _KEY, "              +
                                   "IFNULL(fxt_autonomiccommunityentity.acName,'') AS _VALUE, " +
                                   "fxt_autonomiccommunityentity.default "                      +

                            "FROM fxt_autonomiccommunityentity "                                +

                            "WHERE countryCode = " + countryCode + " "                          +

                            "ORDER BY _VALUE";

        JdbcTemplate jdbcTemplate = new JdbcTemplate ( ds );

        List<HtmlListElement> l = new ArrayList<> ( );

        try {
            l = jdbcTemplate.query ( SQLQuery,
                                       new RowMapper<HtmlListElement> ( ) {
                                           @Override
                                           public HtmlListElement mapRow ( ResultSet rs, int rowNum ) throws SQLException {
                                               HtmlListElement le = new HtmlListElement ( );

                                               le.setKey          ( rs.getString ( "_KEY"     ) );
                                               le.setValue        ( rs.getString ( "_VALUE"   ) );
                                               le.setDefaultValue ( rs.getBoolean ( "default" ) );

                                              return le;
                                           }
                                       } );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ UtilsModel.retrieveAuthonomiesList: " + ex.getMessage ( ) );
        }

        return l;

    }
    
    

   /**
     * Retrieves a list of provinces/states/dapartments (HtmlListElement) from storage.
     * @param acCode The code of the authonomy for which the provinces are to be queried.
     * @param ds The Data Source to use
     * @return ShuttleRec with an instance of List<HtmlListElement>
     */
    public static List<HtmlListElement> retrieveProvincesList ( int acCode, DataSource ds ) {

        String SQLQuery =   "SELECT fxt_provinceentity.provinceCode AS _KEY, "              +
                                   "IFNULL(fxt_provinceentity.provinceName,'') AS _VALUE, " +
                                   "fxt_provinceentity.default "                            +

                            "FROM fxt_provinceentity "                                      +

                            "WHERE fxt_provinceentity.acCode = " + acCode + " "             +

                            "ORDER BY _VALUE";

        JdbcTemplate jdbcTemplate = new JdbcTemplate ( ds );

        List<HtmlListElement> l = new ArrayList<> ( );

        try {
            l = jdbcTemplate.query ( SQLQuery,
                                       new RowMapper<HtmlListElement> ( ) {
                                           @Override
                                           public HtmlListElement mapRow ( ResultSet rs, int rowNum ) throws SQLException {
                                               HtmlListElement le = new HtmlListElement ( );

                                               le.setKey          ( rs.getString  ( "_KEY"    ) );
                                               le.setValue        ( rs.getString  ( "_VALUE"  ) );
                                               le.setDefaultValue ( rs.getBoolean ( "default" ) );

                                              return le;
                                           }
                                       } );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ UtilsModel.retrieveProvincesList: " + ex.getMessage ( ) );
        }

        return l;

    }

    /**
     * Retrieves a list of client preferences (HtmlListElement) from storage.
     * @param locale
     * @param clientCode The code of the client for which the preferences are to be queried.
     * @param ds The Data Source to use
     * @return ShuttleRec with an instance of List<HtmlListElement>
     */
    public static List<HtmlListElement> retrieveClientPrefsList ( String locale, int clientCode, DataSource ds ) {

        String SQLQuery =   "SELECT nf_clientpreferenceentity.clientPreferenceCode AS _KEY, "      +
                                   "IFNULL(nf_clientpreferenceentity.preference,'') AS _VALUE "    +
                
                            "FROM nf_clientpreferenceentity "                                      +
                
                            "WHERE nf_clientpreferenceentity.clientCode = " + clientCode + " AND " +
                                  "nf_clientpreferenceentity.locale = '" + locale + "' AND "       +
                                  "nf_clientpreferenceentity.active = 1 "                          +

                            "ORDER BY _VALUE";

        JdbcTemplate jdbcTemplate = new JdbcTemplate ( ds );

        List<HtmlListElement> l = new ArrayList<> ( );

        try {
            l = jdbcTemplate.query ( SQLQuery,
                                       new RowMapper<HtmlListElement> ( ) {
                                           @Override
                                           public HtmlListElement mapRow ( ResultSet rs, int rowNum ) throws SQLException {
                                               HtmlListElement le = new HtmlListElement ( );

                                               le.setKey   ( rs.getString ( "_KEY"   ) );
                                               le.setValue ( rs.getString ( "_VALUE" ) );

                                              return le;
                                           }
                                       } );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ UtilsModel.retrieveClientPrefsList: " + ex.getMessage ( ) );
        }

        return l;

    }
    
   /**
     * Retrieves a list of i18n key-value pairs for the form identified by "formCode" and the locale in "locale".
     * @param formCode The code of the form for which the strings are to be queried.
     * @param locale The locale of the strings.
     * @param ds The Data Source to use
     * @return ShuttleRec with an instance of List<HtmlListElement>
     */
    public static List<HtmlListElement> retrieveI18NStringsList ( int formCode, String locale, DataSource ds ) {

        String SQLQuery =   "SELECT fxt_i18nstringsentity.i18nStringKey as _KEY, "               +
                                   "IFNULL(fxt_i18nstringsentity.i18nStringValue,'') AS _VALUE " +

                            "FROM fxt_i18nstringsentity "                                        +

                            "WHERE fxt_i18nstringsentity.formCode = " + formCode + " AND "       +
                                  "fxt_i18nstringsentity.i18nLocale = '" + locale + "' AND "     +
                                  "fxt_i18nstringsentity.active = 1";

        JdbcTemplate jdbcTemplate = new JdbcTemplate ( ds );

        List<HtmlListElement> l = new ArrayList<> ( );

        try {
            l = jdbcTemplate.query ( SQLQuery,
                                       new RowMapper<HtmlListElement> ( ) {
                                           @Override
                                           public HtmlListElement mapRow ( ResultSet rs, int rowNum ) throws SQLException {
                                               HtmlListElement r = new HtmlListElement ( );

                                               r.setKey   ( rs.getString ( "_KEY"   ) );
                                               r.setValue ( rs.getString ( "_VALUE" ) );

                                              return r;
                                           }
                                       } );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ UtilsModel.retrieveI18NStringsList: " + ex.getMessage ( ) );
        }

        return l;

    }
    
   /**
     * Retrieves an i18n value in the given locale.
     * @param key
     * @param sir
     * @param ds The Data Source to use
     * @return The requested i18n String
     */
    public static String retrieveI18NString ( String key, DataSource ds ) {
        
        String SQLQuery = "SELECT fxt_i18nstringsentity.i18nStringValue AS _VALUE "        +
                          "FROM fxt_i18nstringsentity "                                    +
                          "WHERE fxt_i18nstringsentity.i18nStringKey = '" + key + "' AND " +
                                "fxt_i18nstringsentity.i18nLocale = 'es-ES'";   //   Jack Sparrow was here - LOCALE CANNO BE HARDWIRED
        
        JdbcTemplate jdbcTemplate = new JdbcTemplate ( ds );
        
        String i18nStr = "";
        
        try {
            i18nStr = jdbcTemplate.queryForObject ( SQLQuery,
                                                    new RowMapper<String> ( ) {
                                                       @Override
                                                       public String mapRow ( ResultSet rs, int rowNum ) throws SQLException {
                                                              String s = rs.getString ( "_VALUE" );
                                                              return s;
                                                       }
                                                    } );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ UtilsModel.retrieveI18NString: " + ex.getMessage ( ) );
        }
        
        return i18nStr;

    }
    
    /**
     * Retrieves an Issue Message (IssueRec) from storage.
     * @param issueCode
     * @param locale
     * @param ds
     * @return an instance of IssueRec
     */
    public static IssueRec retrieveIssueData ( String issueCode, String locale, DataSource ds ) {
        
        String SQLQuery = "SELECT fxt_issuemessageentity.issueCode, "    +
                                 "fxt_issuemessageentity.issueID, "      +
                                 "fxt_issuemessageentity.issueMessage, " +
                                 "fxt_issuemessageentity.explanation "   +

                          "FROM fxt_issuemessageentity "                 +

                          "WHERE issueCode = '" + issueCode + "' AND "   +
                                "locale = '" + locale + "'";
        
        System.out.println ( "retrieveIssueData.SQLQuery: " + SQLQuery );

        JdbcTemplate jdbcTemplate = new JdbcTemplate ( ds );
        
        IssueRec r = new IssueRec ( );
        
        try {
            r = jdbcTemplate.queryForObject ( SQLQuery,
                                       new RowMapper<IssueRec> ( ) {
                                           @Override
                                           public IssueRec mapRow ( ResultSet rs, int rowNum ) throws SQLException {
                                               IssueRec r = new IssueRec ( );

                                               r.setIssueCode    ( rs.getString  ( "issueCode"    ) );
                                               r.setIssueID      ( rs.getString  ( "issueID"      ) );
                                               r.setIssueMessage ( rs.getString  ( "issueMessage" ) );
                                               r.setExplanation  ( rs.getString  ( "explanation"  ) );

                                               return r;
                                           }
                                       } );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ UtilsModel.retrieveIssueData: " + ex.getMessage ( ) );
//            System.out.println ( "***retrieveIssueData.SQLQuery: " + SQLQuery );
        }

        return r;
    }

    public static boolean emailExists ( String email, DataSource ds ) {
        
        String SQLQuery = "SELECT COUNT(nf_personentity.email) AS OCCURRENCES " +
                                 "" +
                          "FROM nf_personentity "                               +
                          "WHERE nf_personentity.email = '" + email + "'";
        
        System.out.println ( "emailExists.SQLQuery: " + SQLQuery );

        JdbcTemplate jdbcTemplate = new JdbcTemplate ( ds );
        
        int numOccurrences = 0;
        
        try {
            numOccurrences = jdbcTemplate.queryForObject ( SQLQuery,
                                                           new RowMapper<Integer> ( ) {
                                                           @Override
                                                               public Integer mapRow ( ResultSet rs, int rowNum ) throws SQLException {
                                                                   Integer i = rs.getInt ( "OCCURRENCES" );
                                                                   return i;
                                                               }
                                                           } );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ UtilsModel.emailExists: " + ex.getMessage ( ) );
        }

        return ( numOccurrences > 0 );

    }

    public static PostCodeRulesRec retrievePostCodeRules ( int provinceCode, DataSource ds ) {
        
        String SQLQuery = "SELECT fxt_postcoderulesentity.lineCode, "                                                                     +
                                 "fxt_postcoderulesentity.provinceCode, "                                                                 +
                                 "IFNULL(fxt_provinceentity.provinceName,'') AS PROVINCE_NAME, "                                          +
                                 "IFNULL(fxt_postcoderulesentity.postCodePrefix,'') AS POST_CODE_PREFIX, "                                +
                                 "IFNULL(fxt_postcoderulesentity.postCodePatterns,'') AS POST_CODE_PATTERNS, "                            +
                                 "fxt_postcoderulesentity.active "                                                                        +

                          "FROM fxt_postcoderulesentity "                                                                                 +

                          "LEFT OUTER JOIN fxt_provinceentity ON fxt_provinceentity.provinceCode = fxt_postcoderulesentity.provinceCode " +

                          "WHERE fxt_postcoderulesentity.provinceCode = " + provinceCode;

        JdbcTemplate jdbcTemplate = new JdbcTemplate ( ds );
        
        PostCodeRulesRec r = new PostCodeRulesRec ( );
        
        try {
            r = jdbcTemplate.queryForObject ( SQLQuery,
                                              new RowMapper<PostCodeRulesRec> ( ) {
                                                  @Override
                                                  public PostCodeRulesRec mapRow ( ResultSet rs, int rowNum ) throws SQLException {
                                                      PostCodeRulesRec r = new PostCodeRulesRec ( );
                                                      
                                                      r.setLineCode         ( rs.getInt     ( "lineCode"           ) );
                                                      r.setProvinceCode     ( rs.getInt     ( "provinceCode"       ) );
                                                      r.setProvinceName     ( rs.getString  ( "PROVINCE_NAME"      ) );
                                                      r.setPostCodePrefix   ( rs.getString  ( "POST_CODE_PREFIX"   ) );
                                                      r.setPostCodePatterns ( rs.getString  ( "POST_CODE_PATTERNS" ) );
                                                      r.setActive           ( rs.getBoolean ( "active"             ) );

                                                      return r;
                                                  }
                                            } );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ UtilsModel.retrievePostCodeRules: " + ex.getMessage ( ) );
        }

        return r;
    }

}