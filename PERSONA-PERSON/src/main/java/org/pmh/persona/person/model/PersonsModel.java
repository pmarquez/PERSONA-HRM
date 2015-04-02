package org.pmh.persona.person.model;

//   Standard Libraries Imports
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import java.util.List;
import org.pmh.persona.person.education.AcademiaBaseRec;

//   Third Party Libraries Imports
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

//   FENIX Framework Imports

//   Application Domain Imports
import org.pmh.persona.person.person.PersonBaseRec;
import org.pmh.persona.person.person.PersonRec;

/**
 * PersonsModel.java<br/><br/>
 * Creation Date 2015-03-23 17:45<br/><br/>
 * <b>DESCRIPTION:</b><br/><br/>
 * <p>Manage all the interactions with persistent Items data.</p>
 *
 *<PRE>
 *<table width="90%" border="1" cellpadding="3" cellspacing="2">
 *<tr><th colspan="2">   History   </th></tr>
 *
 *<tr>
 *<td width="20%">Version 1.0<br/>
 * Version Date: 2015-03-23 17:45<br/>
 * Version Creator: Paulo Márquez</td>
 *<td width="80%"><p>Creation</p></td>
 *</tr>
 *</table>
 *</PRE>
 * @author Paulo Márquez Herrero
 * @version 1.0 - 2015-03-23 17:45
 */
public class PersonsModel {
    
    static public List<PersonBaseRec> retrieveBasePersons ( DataSource ds ) {
        
        String SQLQuery = "SELECT per_personentity.personCode, "                                                           +
                                 "IFNULL(per_personentity.lastName,'') AS LAST_NAME, "                                     +
                                 "IFNULL(per_personentity.firstName,'') AS FIRST_NAME, "                                   +
                                 "IFNULL(per_personentity.middleName,'') AS MIDDLE_NAME, "                                 +
                                 "per_personentity.idTypeCode, "                                                           +
                                 "IFNULL(per_idtypeentity.idTypeName,'') AS ID_TYPE_NAME, "                                +
                                 "IFNULL(per_personentity.idNumber,'') AS ID_NUMBER, "                                     +
                                 "per_personentity.genderCode, "                                                           +
                                 "IFNULL(per_genderentity.gender,'') AS GENDER, "                                          +
                                 "IFNULL(per_personentity.socialSecurityNumber,'') AS SOCIAL_SECURITY_NUMBER, "            +
                                 "IFNULL(per_personentity.birthDate,'') AS BIRTH_DATE, "                                   +
                                 "IFNULL(per_personentity.creationDate,'') AS CREATION_DATE, "                             +
                                 "per_personentity.active "                                                                +

                          "FROM per_personentity "                                                                         +

                          "LEFT OUTER JOIN per_idtypeentity ON per_idtypeentity.idTypeCode = per_personentity.idTypeCode " +
                          "LEFT OUTER JOIN per_genderentity ON per_genderentity.genderCode = per_personentity.genderCode " +
                          
                          "ORDER BY LAST_NAME, FIRST_NAME, MIDDLE_NAME";
        
        JdbcTemplate jdbcTemplate = new JdbcTemplate ( ds );

        List<PersonBaseRec> l = new ArrayList<> ( );

        try {
            l = jdbcTemplate.query ( SQLQuery,
                                        new RowMapper<PersonBaseRec> ( ) {
                                            @Override
                                            public PersonBaseRec mapRow ( ResultSet rs, int rowNum ) throws SQLException {

                                                PersonBaseRec r = new PersonBaseRec ( );

                                                   r.setPersonCode              ( rs.getInt     ( "personCode"             ) );
                                                   r.setFirstName               ( rs.getString  ( "FIRST_NAME"             ) );
                                                   r.setMiddleName              ( rs.getString  ( "MIDDLE_NAME"            ) );
                                                   r.setLastName                ( rs.getString  ( "LAST_NAME"              ) );
                                                   r.setIdTypeCode              ( rs.getInt     ( "idTypeCode"             ) );
                                                   r.setIdType                  ( rs.getString  ( "ID_TYPE_NAME"           ) );
                                                   r.setIdNumber                ( rs.getString  ( "ID_NUMBER"              ) );
                                                   r.setGenderCode              ( rs.getInt     ( "genderCode"             ) );
                                                   r.setGender                  ( rs.getString  ( "GENDER"                 ) );
                                                   r.setSocialSecurityNumber    ( rs.getString  ( "SOCIAL_SECURITY_NUMBER" ) );
                                                   r.setBirthDate               ( rs.getString  ( "BIRTH_DATE"             ) );
                                                   r.setCreationDate            ( rs.getString  ( "CREATION_DATE"          ) );
                                                   r.setActive                  ( rs.getBoolean ( "active"                 ) );

                                                return r;
                                            }
                                        } );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ PersonsModel.retrieveBasePersons: " + ex.getMessage ( ) );
        }

        return l;
    }

    static public PersonRec retrievePerson ( int personCode, final DataSource ds ) {
        
        String SQLQuery = "SELECT per_personentity.personCode, "                                                           +
                                 "IFNULL(per_personentity.lastName,'') AS LAST_NAME, "                                     +
                                 "IFNULL(per_personentity.firstName,'') AS FIRST_NAME, "                                   +
                                 "IFNULL(per_personentity.middleName,'') AS MIDDLE_NAME, "                                 +
                                 "per_personentity.idTypeCode, "                                                           +
                                 "IFNULL(per_idtypeentity.idTypeName,'') AS ID_TYPE_NAME, "                                +
                                 "IFNULL(per_personentity.idNumber,'') AS ID_NUMBER, "                                     +
                                 "per_personentity.genderCode, "                                                           +
                                 "IFNULL(per_genderentity.gender,'') AS GENDER, "                                          +
                                 "IFNULL(per_personentity.socialSecurityNumber,'') AS SOCIAL_SECURITY_NUMBER, "            +
                                 "IFNULL(per_personentity.birthDate,'') AS BIRTH_DATE, "                                   +
                                 "IFNULL(per_personentity.creationDate,'') AS CREATION_DATE, "                             +
                                 "per_personentity.active "                                                                +

                          "FROM per_personentity "                                                                         +

                          "LEFT OUTER JOIN per_idtypeentity ON per_idtypeentity.idTypeCode = per_personentity.idTypeCode " +
                          "LEFT OUTER JOIN per_genderentity ON per_genderentity.genderCode = per_personentity.genderCode " +
                          
                          "WHERE per_personentity.personCode = " + personCode;
        
        JdbcTemplate jdbcTemplate = new JdbcTemplate ( ds );

        PersonRec r = new PersonRec ( );

        try {
            r  =  jdbcTemplate.queryForObject (SQLQuery,
                                                 new RowMapper<PersonRec> ( ) {
                                                    @Override
                                                    public PersonRec mapRow ( ResultSet rs, int rowNum ) throws SQLException {

                                                        PersonRec r = new PersonRec ( );

                                                            r.setPersonCode              ( rs.getInt     ( "personCode"             ) );
                                                            r.setFirstName               ( rs.getString  ( "FIRST_NAME"             ) );
                                                            r.setMiddleName              ( rs.getString  ( "MIDDLE_NAME"            ) );
                                                            r.setLastName                ( rs.getString  ( "LAST_NAME"              ) );
                                                            r.setIdTypeCode              ( rs.getInt     ( "idTypeCode"             ) );
                                                            r.setIdType                  ( rs.getString  ( "ID_TYPE_NAME"           ) );
                                                            r.setIdNumber                ( rs.getString  ( "ID_NUMBER"              ) );
                                                            r.setGenderCode              ( rs.getInt     ( "genderCode"             ) );
                                                            r.setGender                  ( rs.getString  ( "GENDER"                 ) );
                                                            r.setSocialSecurityNumber    ( rs.getString  ( "SOCIAL_SECURITY_NUMBER" ) );
                                                            r.setBirthDate               ( rs.getString  ( "BIRTH_DATE"             ) );
                                                            r.setCreationDate            ( rs.getString  ( "CREATION_DATE"          ) );
                                                            r.setActive                  ( rs.getBoolean ( "active"                 ) );
                                                           
                                                            r.setAcademia ( PersonsModel.retrievePersonAcademia ( personCode, ds ) );
                                                            
                                                        return r;
                                                    }
                                                } );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ PersonsModel.retrievePerson: " + ex.getMessage ( ) );
        }

        return r;
    }

    static public List<AcademiaBaseRec> retrievePersonAcademia( int personCode, DataSource ds ) {
        
        String SQLQuery = "SELECT per_academiaentity.academiaCode, "                                         +
                                 "per_academiaentity.personCode, "                                           +
                                 "IFNULL(per_academiaentity.startDate,'') AS START_DATE, "                   +
                                 "IFNULL(per_academiaentity.endDate,'') AS END_DATE, "                       +
                                 "per_academiaentity.ongoing, "                                              +
                                 "IFNULL(per_academiaentity.degreeName,'') AS DEGREE_NAME, "                 +
                                 "IFNULL(per_academiaentity.institution,'') AS INSTITUTION_NAME, "           +
                                 "IFNULL(per_academiaentity.institutionCity,'') AS INSTITUTION_CITY, "       +
                                 "IFNULL(per_academiaentity.institutionState,'') AS INSTITUTION_STATE, "     +
                                 "IFNULL(per_academiaentity.institutionCountry,'') AS INSTITUTION_COUNTRY, " +
                                 "IFNULL(per_academiaentity.creationDate,'') AS CREATION_DATE "              +

                          "FROM per_academiaentity "                                                         +

                          "WHERE personCode = " + personCode + " "                                           +

                          "ORDER BY per_academiaentity.startDate DESC";
        
        
        System.out.println ( SQLQuery );        
        
        
        JdbcTemplate jdbcTemplate = new JdbcTemplate ( ds );

        List<AcademiaBaseRec> l = new ArrayList<> ( );

        try {
            l = jdbcTemplate.query ( SQLQuery,
                                        new RowMapper<AcademiaBaseRec> ( ) {
                                            @Override
                                            public AcademiaBaseRec mapRow ( ResultSet rs, int rowNum ) throws SQLException {

                                                AcademiaBaseRec r = new AcademiaBaseRec ( );

                                                   r.setAcademiaCode            ( rs.getInt     ( "academiaCode"        ) );
                                                   r.setPersonCode              ( rs.getInt     ( "personCode"          ) );
                                                   r.setDegreeName              ( rs.getString  ( "DEGREE_NAME"         ) );
                                                   r.setInstitution             ( rs.getString  ( "INSTITUTION_NAME"    ) );
                                                   r.setInstitutionCity         ( rs.getString  ( "INSTITUTION_CITY"    ) );
                                                   r.setInstitutionState        ( rs.getString  ( "INSTITUTION_STATE"   ) );
                                                   r.setInstitutionCountry      ( rs.getString  ( "INSTITUTION_COUNTRY" ) );
                                                   r.setStartDate               ( rs.getString  ( "START_DATE"          ) );
                                                   r.setEndDate                 ( rs.getString  ( "END_DATE"            ) );
                                                   r.setOngoing                 ( rs.getBoolean ( "ongoing"             ) );
                                                   r.setCreationDate            ( rs.getString  ( "CREATION_DATE"       ) );

                                                return r;
                                            }
                                        } );
        } catch ( DataAccessException ex ) {
            System.err.println ( "DataAccessException @ PersonsModel.retrievePersonAcademia: " + ex.getMessage ( ) );
        }

        return l;
    }

}
