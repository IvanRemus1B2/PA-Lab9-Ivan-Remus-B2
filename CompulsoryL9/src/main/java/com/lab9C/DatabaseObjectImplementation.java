package com.lab9C;


import java.sql.*;
import java.util.*;

/**
 * Implementation for a database object that will implement the function asked by the interface
 */
public class DatabaseObjectImplementation implements DatabaseObject{

    private final Connection databaseConnection;


    private final PreparedStatement insertCountry;
    private final PreparedStatement insertContinent;
    private final PreparedStatement insertCity;

    private final PreparedStatement selectCountryWithId;
    private final PreparedStatement selectContinentWithId;
    private final PreparedStatement selectCityWithId;

    private final PreparedStatement selectCountryWithName;
    private final PreparedStatement selectContinentWithName;
    private final PreparedStatement selectCityWithName;

    private final PreparedStatement selectCitiesWithCriteria;

    private enum selectType{
        COUNTRY_BY_NAME,COUNTRY_BY_ID,CONTINENT_BY_NAME,CONTINENT_BY_ID,CITY_BY_ID,CITY_BY_NAME
    }

    /**
     * Constructor for the database object that will instantiate the database connection and prepare the statements to be used later on
     * @param databaseConnection the database connection
     * @throws SQLException thrown if any sql exceptions occur
     * @throws IllegalArgumentException thrown if the databaseConnection parameter is null
     */
    public DatabaseObjectImplementation(Connection databaseConnection) throws SQLException,IllegalArgumentException {

        if(databaseConnection==null) {
            throw new IllegalArgumentException("Not allowed to give the database connection as null");
        }

        this.databaseConnection=databaseConnection;

        String query;

        query="INSERT INTO COUNTRIES VALUES(?,?,?,?)";
        insertCountry=databaseConnection.prepareStatement( query );

        query="INSERT INTO CONTINENTS VALUES(?,?)";
        insertContinent=databaseConnection.prepareStatement( query );

        //id number(8,0),country_name varchar2(100),city_name varchar2(100),capital number(1,0),latitude float,longitude float,population number(10,0)
        query="INSERT INTO CITIES VALUES(?,?,?,?,?,?,?)";
        insertCity=databaseConnection.prepareStatement( query );

        query="SELECT * FROM COUNTRIES WHERE id=?";
        selectCountryWithId=databaseConnection.prepareStatement( query );

        query="SELECT * FROM CONTINENTS WHERE id=?";
        selectContinentWithId=databaseConnection.prepareStatement( query );

        query="SELECT * FROM CITIES WHERE id=?";
        selectCityWithId=databaseConnection.prepareStatement( query );

        query="SELECT * FROM COUNTRIES WHERE upper(name)=?";
        selectCountryWithName=databaseConnection.prepareStatement( query );

        query="SELECT * FROM CONTINENTS WHERE upper(name)=?";
        selectContinentWithName=databaseConnection.prepareStatement( query );

        query="SELECT * FROM CITIES WHERE upper(city_name)=?";
        selectCityWithName=databaseConnection.prepareStatement( query );

        query="SELECT * FROM CITIES WHERE capital=? or population>=?";
        selectCitiesWithCriteria=databaseConnection.prepareStatement( query );


    }

    /**
     * Creates tables CITIES,CONTINENTS and COUNTRIES using the help of the methods tableExists and createTableCountries,createTableCities,createTableContinents
     * @param dropTableCountries if true,then the table COUNTRIES will be dropped from the database if it already exists and created,else the table will be created if it doesn't exist
     * @param dropTableContinents if true,then the table CONTINENTS will be dropped from the database if it already exists and recreated,else the table will be created if it doesn't exist
     * @param dropTableCities if true,then the table CITIES will be dropped from the database if it already exists and created,else the table will be created if it doesn't exist
     * @param dropTableFakeCities if true,then the table FAKE_CITIES will be dropped from the database if it already exists and created,else the table will be created if it doesn't exist
     * @param dropTableSisterCities if true,then the table SISTER_CITIES will be dropped from the database if it already exists and created,else the table will be created if it doesn't exist
     * @throws SQLException thrown if any sql exceptions occur
     */
    @Override
    public void createTables(boolean dropTableCountries,boolean dropTableContinents,boolean dropTableCities,boolean dropTableFakeCities,boolean dropTableSisterCities) throws SQLException {

        //Drop if it already exists and create table countries
        boolean existsTableCountries=tableExists( "COUNTRIES" );
        if(existsTableCountries){
            if(dropTableCountries){
                Statement statement = databaseConnection.createStatement();
                statement.execute( "DROP TABLE COUNTRIES" );

                createTableCountries();
            }
        }
        else {
            createTableCountries();
        }

        //Drop if it already exists and create table continents
        boolean existsTableContinents=tableExists( "CONTINENTS" );
        if(existsTableContinents){
            if(dropTableContinents){
                Statement statement = databaseConnection.createStatement();
                statement.execute( "DROP TABLE CONTINENTS" );

                createTableContinents();
            }
        }
        else {
            createTableContinents();
        }

        //Drop if it already exists and create table cities
        boolean existsTableCities=tableExists( "CITIES" );
        if(existsTableCities){
            if(dropTableCities){
                Statement statement = databaseConnection.createStatement();
                statement.execute( "DROP TABLE CITIES" );

                createTableCities();
            }
        }
        else {
            createTableCities();
        }

        //Drop if it already exists and create table random cities
        boolean existsTableFakeCities=tableExists( "FAKE_CITIES" );
        if(existsTableFakeCities){
            if(dropTableFakeCities){
                Statement statement=databaseConnection.createStatement();
                statement.execute("DROP TABLE FAKE_CITIES");

                createTableFakeCities();
            }
        }
        else {
            createTableFakeCities();
        }

        //Drop if it already exists and create table sister cities

        boolean existsTableSisterCities=tableExists( "SISTER_CITIES" );
        if(existsTableSisterCities){
            if(dropTableSisterCities){
                Statement statement=databaseConnection.createStatement();
                statement.execute( "DROP TABLE SISTER_CITIES" );

                createTableSisterCities();
            }
        }
        else{
            createTableSisterCities();
        }

        //Finish
        databaseConnection.commit();
    }


    /**
     * Checks whether the table with the given name exists in the database
     * @param tableName the name of the table
     * @return a boolean value,true if the table exists in the database,false otherwise
     * @throws SQLException thrown if any sql exceptions occur
     * @throws IllegalArgumentException thrown if the table name is null or empty
     */
    private boolean tableExists(String tableName) throws SQLException,IllegalArgumentException {

        if(tableName==null){
            throw new IllegalArgumentException("The table name cannot be null");
        }

        if(tableName.equals( "" )) {
            throw new IllegalArgumentException("The table name cannot be empty");
        }

        Statement statement=databaseConnection.createStatement();
        ResultSet results=statement.executeQuery( "SELECT TABLE_NAME FROM USER_TABLES" );

        while(results.next()) {
            String name=results.getString( 1 );
            if(tableName.toUpperCase(Locale.ENGLISH).equals( name)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Creates a table with the name COUNTRIES,having an id,name,code,and continent_name
     * @throws SQLException thrown if any sql exceptions occur
     */
    private void createTableCountries() throws SQLException {
        Statement statement=databaseConnection.createStatement();

        String sqlCreateTableCountries="CREATE TABLE COUNTRIES(id number(4,0),name varchar2(100),code varchar2(10),continent_name varchar2(20))";
        statement.executeUpdate(sqlCreateTableCountries);
    }

    /**
     * Creates a table with the name CONTINENTS,having an id and name
     * @throws SQLException thrown if any sql exceptions occur
     */
    private void createTableContinents() throws SQLException {
        Statement statement=databaseConnection.createStatement();

        String sqlCreateTableContinents="CREATE TABLE CONTINENTS(id number(4,0),name varchar2(20))";
        statement.executeUpdate( sqlCreateTableContinents );

    }

    /**
     * Creates a table with the name CITIES having an id,country_name,city_name,capital,latitude,longitude and population
     * @throws SQLException thrown if any sql exceptions occur
     */
    private void createTableCities() throws SQLException {
        Statement statement=databaseConnection.createStatement();

        String sqlCreateTableCities="CREATE TABLE CITIES(id number(8,0),country_name varchar2(100),city_name varchar2(100),capital number(1,0),latitude float,longitude float,population number(10,0))";
        statement.executeUpdate( sqlCreateTableCities );
    }

    /**
     * Creates a table with the name FAKE_CITIES having an id,country_name,city_name and population
     * @throws SQLException thrown if any sql exceptions occur
     */
    private void createTableFakeCities() throws SQLException {
        Statement statement=databaseConnection.createStatement();

        String sqlCreateTableRandomCities="CREATE TABLE FAKE_CITIES(id number(8,0),country_name varchar2(100),city_name varchar2(100),population number(10,0))";
        statement.executeUpdate( sqlCreateTableRandomCities );
    }

    private void createTableSisterCities() throws SQLException {
        Statement statement=databaseConnection.createStatement();

        String sqlCreateTableSisterCities="CREATE TABLE SISTER_CITIES(id_city1 number(8,0),id_city2 number(8,0))";
        statement.executeUpdate( sqlCreateTableSisterCities );
    }

    /**
     * Inserts into the database the country given as an instance of the Country class
     * @param country the country given
     * @throws SQLException thrown if any sql exceptions occur
     * @throws IllegalArgumentException thrown if the country parameter is null
     */
    @Override
    public void insertCountry( Country country ) throws SQLException,IllegalArgumentException {

        if(country==null) {
            throw new IllegalArgumentException("Not allowed to give the country as null");
        }

        insertCountry.setInt( 1,country.getId() );
        insertCountry.setString( 2,country.getName() );
        insertCountry.setString( 3,country.getCode() );
        insertCountry.setString( 4, country.getContinentName());

        insertCountry.executeUpdate();

        databaseConnection.commit();
    }

    /**
     * Inserts a continent into the database described by an instance of the class Continent
     * @param continent the continent to be inserted
     * @throws SQLException thrown if any sql exceptions occur
     * @throws IllegalArgumentException thrown if the continent given is null
     */
    @Override
    public void insertContinent( Continent continent ) throws SQLException,IllegalArgumentException {

        if(continent==null) {
            throw new IllegalArgumentException("Not allowed to give the continent as null");
        }

        insertContinent.setInt( 1,continent.getId() );
        insertContinent.setString( 2,continent.getName() );

        insertContinent.executeUpdate();

        databaseConnection.commit();
    }


    /**
     * Inserts into the database a given city
     * @param city the city whose information will be inserted into the database
     * @throws SQLException thrown if any sql exceptions occur
     * @throws IllegalArgumentException thrown if the city parameter si null
     */
    @Override
    public void insertCity( City city ) throws SQLException,IllegalArgumentException {

        if(city==null){
            throw new IllegalArgumentException("Not allowed to give the city as null");
        }

        //id number(8,0),country_name varchar2(100),city_name varchar2(100),capital number(1,0),latitude float,longitude float,population number(10,0)

        insertCity.setInt( 1,city.getId() );
        insertCity.setString( 2,city.getCountryName() );
        insertCity.setString( 3,city.getCityName() );
        insertCity.setInt( 4, ( city.isCapital() )?1:0 );
        insertCity.setDouble( 5, city.getLatitude() );
        insertCity.setDouble( 6, city.getLongitude() );
        insertCity.setLong( 7, city.getPopulation() );

        insertCity.executeUpdate();

        databaseConnection.commit();
    }

    /**
     * Helper function that will get the ResultSet corresponding to the select statement type and the information given(similar to SELECT FROM COUNTRIES/CONTINENTS WHERE id/name=info)
     * @param selectedType the type of select statement to be used
     * @param info the information used in the select statement as a string
     * @return the tuples that contain
     * @throws SQLException thrown if any sql exceptions occur
     * @throws IllegalArgumentException thrown if info is  null
     */
    private ResultSet getResultsForSelect(selectType selectedType,String info) throws SQLException,IllegalArgumentException {

        if(info==null) {
            throw new IllegalArgumentException("Not allowed to give the information searched for by the select statement as null");
        }

        if(info.equals( "" )) {
            throw new IllegalArgumentException("Not allowed to give the information searched for by the select statement as null");
        }

        ResultSet results=null;

        if(selectedType==selectType.COUNTRY_BY_ID) {
            selectCountryWithId.setInt( 1,Integer.parseInt( info ));
            results=selectCountryWithId.executeQuery();
        }
        else if(selectedType==selectType.CONTINENT_BY_ID) {
            selectContinentWithId.setInt( 1,Integer.parseInt( info ) );
            results=selectContinentWithId.executeQuery();
        }
        else if(selectedType==selectType.CITY_BY_ID){
            selectCityWithId.setInt(1,Integer.parseInt( info ));
            results=selectCityWithId.executeQuery();
        }
        else if(selectedType==selectType.COUNTRY_BY_NAME) {
            selectCountryWithName.setString( 1, info.toUpperCase( Locale.ENGLISH ) );
            results=selectCountryWithName.executeQuery();
        }
        else if(selectedType==selectType.CONTINENT_BY_NAME) {
            selectContinentWithName.setString( 1 , info.toUpperCase( Locale.ENGLISH ) );
            results = selectContinentWithName.executeQuery();
        }
        else if(selectedType==selectType.CITY_BY_NAME){
            selectCityWithName.setString( 1,info.toUpperCase( Locale.ENGLISH ) );
            results=selectCityWithName.executeQuery();
        }

        return results;
    }

    /**
     * Gets a country that has the corresponding id
     * @param id the id given as an int
     * @return an instance of the class Country which will have the id requested,or null if no such country was found
     * @throws SQLException thrown if any sql exceptions occur
     */
    @Override
    public Country getCountryWithId( int id ) throws SQLException {

        var results=getResultsForSelect( selectType.COUNTRY_BY_ID,Integer.toString( id ) );

        if(results.next()) {
            return new Country(results.getInt( "id" ),results.getString( "name" ),results.getString( "code" ),results.getString( "continent_name" ));
        }

        return null;
    }

    /**
     * Gets a country that has the corresponding name
     * @param countryName the name of the country as a string
     * @return an instance of the class Country which will have the countryName requested,or null if no such country was found
     * @throws SQLException thrown if any sql exceptions occur
     */
    @Override
    public Country getCountryWithName( String countryName ) throws SQLException,IllegalArgumentException {

        if(countryName==null) {
            throw new IllegalArgumentException("Not allowed to give the country name as null");
        }

        if(countryName.equals( "" )) {
            throw new IllegalArgumentException("Not allowed to give the country name as empty");
        }

        var results=getResultsForSelect( selectType.COUNTRY_BY_NAME,countryName );

        if(results.next()) {
            return new Country(results.getInt( "id" ),results.getString( "name" ),results.getString( "code" ),results.getString( "continent_name" ));
        }

        return null;
    }

    /**
     * Helper function that will extract the first continent from a resultSet
     * @param resultSet the result set
     * @return the continent from the set
     * @throws SQLException thrown if any sql exceptions occur
     * @throws IllegalArgumentException thrown if the result set given is null
     */
    private Continent extractContinentFromResultSet(ResultSet resultSet) throws SQLException,IllegalArgumentException {

        if(resultSet==null){
            throw new IllegalArgumentException("Not allowed to give the resultSet as null");
        }

        return new Continent(resultSet.getInt( "id" ),resultSet.getString( "name" ));
    }

    /**
     * Gets the continent that has the corresponding id
     * @param id the id as an int
     * @return an instance of the class Continent that has the given id,or null if no such continent exists
     * @throws SQLException thrown if any sql exceptions occur
     */
    @Override
    public Continent getContinentWithId( int id ) throws SQLException {

        var results=getResultsForSelect( selectType.CONTINENT_BY_ID,Integer.toString( id ) );

        if(results.next()) {
            return extractContinentFromResultSet( results );
        }

        return null;
    }

    /**
     * Gets the continent that has the corresponding name
     * @param continentName the name of the continent as a string
     * @return an instance of the class Continent that has the given name,or null if no such continent exists
     * @throws SQLException thrown if any sql exceptions occur
     */
    @Override
    public Continent getContinentWithName( String continentName ) throws SQLException,IllegalArgumentException {

        if(continentName==null) {
            throw new IllegalArgumentException("Not allowed to give the continent name as null");
        }

        if(continentName.equals( "" )) {
            throw new IllegalArgumentException("Not allowed to give the continent name as empty");
        }

        var results=getResultsForSelect( selectType.CONTINENT_BY_NAME,continentName );

        if(results.next()) {
            return extractContinentFromResultSet( results );
        }

        return null;
    }

    /**
     * Helper method that will extract 1 city from the result set
     * @param resultSet the result set
     * @return the city extracted
     * @throws SQLException thrown if any sql exceptions occur
     * @throws IllegalArgumentException thrown if the result set given is null
     */
    private City extractCityFromResultSet(ResultSet resultSet) throws SQLException,IllegalArgumentException {

        if(resultSet==null){
            throw new IllegalArgumentException("Not allowed to give the result set as null");
        }

        boolean isCapital=resultSet.getInt( "capital" ) != 0;
        return new City( resultSet.getInt( "id" ) , resultSet.getString( "country_name" ) , resultSet.getString( "city_name" ) , isCapital , resultSet.getDouble( "latitude" ) , resultSet.getDouble( "longitude" ) , resultSet.getLong( "population" ) );
    }

    /**
     * Extracts from a given resultSet a list of cities(if there are any)
     * @param resultSet the result set
     * @return List<City> being the list of cities extracted from the result set
     * @throws SQLException thrown if any sql exceptions occur
     * @throws IllegalArgumentException thrown if the result set is null
     */
    private List<City> createListOfCities(ResultSet resultSet) throws SQLException,IllegalArgumentException {

        if(resultSet==null){
            throw new IllegalArgumentException("Not allowed to give the result set as null");
        }

        List<City> listCities=new ArrayList<>();
        while(resultSet.next()){
            listCities.add( extractCityFromResultSet( resultSet ) );
        }

        return listCities;
    }

    /**
     * Gets a list of cities that have this id in the database
     * @param id the id value
     * @return the list of cites that have the corresponding id
     * @throws SQLException thrown if any sql exceptions occur
     */
    @Override
    public List<City> getCitiesWithId( int id ) throws SQLException {

        //id number(8,0),country_name varchar2(100),city_name varchar2(100),capital number(1,0),latitude float,longitude float,population number(10,0)
        var results=getResultsForSelect( selectType.CITY_BY_ID,Integer.toString( id ) );

        return createListOfCities(results);
    }

    /**
     * Gets the list of cities with the corresponding city name
     * @param cityName the city name
     * @return the list of cities
     * @throws SQLException thrown if any sql exceptions occur
     * @throws IllegalArgumentException thrown if the city name is null or empty
     */
    @Override
    public List<City> getCitiesWithName( String cityName ) throws SQLException,IllegalArgumentException {

        if(cityName==null){
            throw new IllegalArgumentException("Not allowed to give the city name as null");
        }

        if(cityName.equals( "" )){
            throw new IllegalArgumentException("Not allowed to give the city name as empty");
        }

        var results=getResultsForSelect( selectType.CITY_BY_NAME,cityName );

        return createListOfCities(results);
    }

    /**
     * Computes a list of cities from the database
     * @param includeAllCapitals boolean value,if true,any city that is a capital(primary) will be added to the list of cities,else it's not taken into consideration
     * @param minimumPopulationSize the minimum population size for the city to have to be allowed into the list(has to be greater or equal)
     * @return the list of cities
     * @throws SQLException thrown if any sql exceptions occur
     * @throws IllegalArgumentException thrown if the minimum population size is negative
     */
    @Override
    public List<City> getCities(boolean includeAllCapitals,long minimumPopulationSize) throws SQLException,IllegalArgumentException{

        if(minimumPopulationSize<0){
            throw new IllegalArgumentException("The minimum population size cannot be negative");
        }

        List<City> listCities=new ArrayList<>();

        if(includeAllCapitals){
            selectCitiesWithCriteria.setInt( 1,1 );
        }
        else {
            selectCitiesWithCriteria.setInt( 1,0 );
        }

        selectCitiesWithCriteria.setLong( 2,minimumPopulationSize );

        var results=selectCitiesWithCriteria.executeQuery();

        while(results.next()){
            listCities.add( extractCityFromResultSet( results ) );
        }

        return listCities;
    }

    /**
     * Inserts into the table FAKE_CITIES a new city
     * @param city the city to be inserted
     * @throws SQLException thrown if any sql exceptions occur
     * @throws IllegalArgumentException thrown if city is null
     */
    @Override
    public void insertFakeCity( City city ) throws SQLException,IllegalArgumentException {

        if(city==null){
            throw new IllegalArgumentException("Not allowed to give the city as null");
        }

        //RANDOM_CITIES(id number(8,0),country_name varchar2(100),city_name varchar2(100),population number(10,0)

        String sql="INSERT INTO FAKE_CITIES VALUES(?,?,?,?)";
        PreparedStatement preparedStatement=databaseConnection.prepareStatement( sql );

        preparedStatement.setInt( 1,city.getId() );
        preparedStatement.setString( 2,city.getCountryName() );
        preparedStatement.setString( 3,city.getCityName() );
        preparedStatement.setLong( 4,city.getPopulation() );

        preparedStatement.executeUpdate();

        databaseConnection.commit();
    }

    /**
     * Extracts a city that is considered fake(latitude,longitude and isCapital field will not be inserted into the database)
     * @param resultSet the result set
     * @return the city extracted having only the field's id,country name,city name and population as values from the database
     * @throws SQLException thrown if any sql exceptions occur
     * @throws IllegalArgumentException thrown if the result set given is null
     */
    private City extractFakeCityFromResultSet(ResultSet resultSet) throws SQLException,IllegalArgumentException {

        if(resultSet==null){
            throw new IllegalArgumentException("Not allowed to give the result set as null");
        }

        return new City(resultSet.getInt( "id" ),resultSet.getString( "country_name" ),resultSet.getString( "city_name" ),resultSet.getLong( "population" ));
    }

    /**
     * Select from the database using the connection all the cities from the table FAKE_CITIES
     * @return a list of cities
     * @throws SQLException thrown if any sql exceptions occur
     */
    @Override
    public List<City> getFakeCities() throws SQLException {
        List<City> listCities=new ArrayList<>();

        Statement statement=databaseConnection.createStatement();

        String sql="SELECT * FROM FAKE_CITIES";
        ResultSet resultSet=statement.executeQuery( sql );

        while(resultSet.next()){
            listCities.add( extractFakeCityFromResultSet( resultSet ) );
        }
        return listCities;
    }

    /**
     * Inserts into the table SISTER_CITIES 2 values representing the id for the first city and the id for the second city which have a sister relationship
     * @param idCity1 id of the first city
     * @param idCity2 id of the second city
     * @throws SQLException thrown if any sql exceptions occur
     */
    @Override
    public void insertSisterCities( int idCity1 , int idCity2 ) throws SQLException {
        //id_city1 number(8,0),id_city2 number(8,0)

        String sql="INSERT INTO SISTER_CITIES VALUES(?,?)";
        PreparedStatement preparedStatement=databaseConnection.prepareStatement( sql );

        preparedStatement.setInt( 1,idCity1 );
        preparedStatement.setInt( 2,idCity2 );

        preparedStatement.executeUpdate();

        databaseConnection.commit();
    }

    /**
     * Select from the database ,from the table SISTER_CITIES all the sister relationship as a list of instances of the class SisterRelationships
     * @return a list of sister relationships
     * @throws SQLException thrown if any sql exceptions occur
     */
    @Override
    public List<SisterRelationship> getSisterRelationships() throws SQLException {
        List<SisterRelationship> listSisterRelationships=new ArrayList<>();

        Statement statement=databaseConnection.createStatement();
        String sql="SELECT * FROM SISTER_CITIES";
        ResultSet resultSet=statement.executeQuery( sql );

        Map<Integer,City> cityWithId=new HashMap<>();
        var listFakeCities=this.getFakeCities();
        for(City city:listFakeCities){
            cityWithId.put( city.getId(),city );
        }

        while(resultSet.next()){
            int idCity1=resultSet.getInt( "id_city1" );
            int idCity2=resultSet.getInt( "id_city2" );

            listSisterRelationships.add( new SisterRelationship( cityWithId.get( idCity1 ),cityWithId.get( idCity2 ) ) );
        }

        return listSisterRelationships;
    }
}
