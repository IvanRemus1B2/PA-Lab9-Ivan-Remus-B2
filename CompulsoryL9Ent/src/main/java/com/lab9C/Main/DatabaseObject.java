package com.lab9C.Main;


import java.sql.SQLException;
import java.util.List;

/**
 * Interface to describe the functions that have to be implemented by a database object
 * The function should permit the creation of the corresponding tables(COUNTRIES,CONTINENTS,CITIES,FAKE_CITIES and SISTER_CITIES),inserting a country,city , continent,fake city or sister relationship with their respective information
 * as well as selecting all cities,fake cities and sister relationships from the tables
 */
public interface DatabaseObject {

    void createTables(boolean dropTableCountries,boolean dropTableContinents,boolean dropTableCities,boolean dropTableFakeCities,boolean dropTableSisterCities) throws SQLException;

    void insertCountry(Country country) throws SQLException;
    Country getCountryWithId(int id) throws SQLException;
    Country getCountryWithName(String countryName) throws SQLException;

    void insertContinent(Continent continent) throws SQLException;
    Continent getContinentWithId(int id) throws SQLException;
    Continent getContinentWithName(String continentName) throws SQLException;

    void insertCity(City city) throws SQLException;
    List<City> getCitiesWithId( int id) throws SQLException;
    List<City> getCitiesWithName(String cityName) throws SQLException;

    List<City> getCities(boolean includeAllCapitals,long minimumPopulationSize) throws SQLException;

    void insertFakeCity(City city) throws SQLException;
    List<City> getFakeCities() throws SQLException;

    void insertSisterCities(int idCity1,int idCity2) throws SQLException;
    List<SisterRelationship> getSisterRelationships() throws SQLException;
}
