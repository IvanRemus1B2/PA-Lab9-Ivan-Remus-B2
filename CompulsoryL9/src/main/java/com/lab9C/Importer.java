package com.lab9C;


import java.io.*;
import java.sql.SQLException;
import java.util.*;

/**
 * The importer class offer a method to import data about cities from a file
 */
public class Importer {

    /**
     * Imports data from a file at the given path using a database object
     * @param pathCitiesData the path to the file where the city data is stored
     * @param databaseObject the database object with which the data will be inserted into the database
     * @throws IOException thrown if any Input/Output exceptions occur
     * @throws SQLException thrown if any sql exceptions occur
     * @throws IllegalArgumentException thrown if the path to the file is null or empty or if the databaseObject is null
     */
    public static void importCityData( String pathCitiesData, DatabaseObject databaseObject ) throws IOException, SQLException,IllegalArgumentException {

        if(pathCitiesData==null){
            throw new IllegalArgumentException("Not allowed to give the path data as null");
        }

        if(pathCitiesData.equals( "" )){
            throw new IllegalArgumentException("Not allowed to give the path data as empty");
        }

        if(databaseObject==null){
            throw new IllegalArgumentException("Not allowed to give the database object as null");
        }

        var listCities=getCitiesFromFile(pathCitiesData);

        for(City city:listCities){
            databaseObject.insertCity( city );
        }
    }

    /**
     * Extracts tokens from a string that are separated by ,(comma) .If a string obtained has length>2,then it will take as a token the substring from position [1,length-2]
     * otherwise it will add an empty string
     * @param line the line of text from which values are to be extracted
     * @return a list of string being the token values
     * @throws IllegalArgumentException thrown if the line given is null
     */
    private static List<String> extractValuesFromString(String line) throws IllegalArgumentException {

        if(line==null){
            throw new IllegalArgumentException("Not allowed to give the string for the line data as null");
        }

        List<String> listStrings=Arrays.stream( line.split( ",",0) ).toList();

        List<String> listValues=new ArrayList<>();
        for(String text:listStrings) {
            int length=text.length();
            if(length>2) {
                listValues.add( text.substring( 1,length-1 ) );
            }
            else {
                listValues.add( "" );
            }
        }

        return listValues;
    }

    /**
     * Helper method that verifies if the given list of attributes is valid.A list of attributes values is considered valid if each field is not empty
     * @param listAttributesValues the list of attributes with values
     * @param position the position of an attribute identified by the attribute name in the attribute list
     * @return a boolean value,true if each field is not empty(and not null) ,and false is such a field is empty.In case the size of the list of attributes values and the size of
     * @throws IllegalArgumentException thrown if the list of attributes is null or position is null
     */
    private static boolean isListAttributesValid(List<String> listAttributesValues,Map<String,Integer> position) throws IllegalArgumentException{

        if(listAttributesValues==null){
            throw new IllegalArgumentException("Not allowed to give the list of attribute values as null");
        }

        if(position==null){
            throw new IllegalArgumentException("Not allowed to give the positions of each attribute as null");
        }

        //TODO better way to include files that have an , in their name
        if(listAttributesValues.size()!=position.size()) {
            return false;
        }

        boolean hasCityNameInAscii=true;
        String cityName=listAttributesValues.get( position.get( "city_ascii" ) );
        if(cityName.length()==0) {
            hasCityNameInAscii=false;
        }

        boolean hasLatitude=true;
        String latitude=listAttributesValues.get( position.get( "lat" ) );
        if(latitude.length()==0){
            hasLatitude=false;
        }

        boolean hasLongitude=true;
        String longitude=listAttributesValues.get( position.get( "lng" ) );
        if(longitude.length()==0){
            hasLongitude=false;
        }


        boolean hasCountryName=true;
        String countryName=listAttributesValues.get( position.get( "country" ) );
        if(countryName.length()==0){
            hasCountryName=false;
        }

        boolean hasPopulation=true;
        String population=listAttributesValues.get( position.get( "population" ) );
        if(population.length()==0){
            hasPopulation=false;
        }

        return hasCityNameInAscii&&hasCountryName&&hasLatitude&&hasLongitude&&hasPopulation;
    }

    /**
     * Opens a file and extract from the file the list of cities that are considered valid by the method isListOfAttributesValid
     * @param pathFile the path to the file
     * @return a list of cities that are valid
     * @throws IOException thrown if any Input/Output exceptions occur
     * @throws IllegalArgumentException thrown if the path file is null or empty
     */
    private static List<City> getCitiesFromFile( String pathFile) throws IOException,IllegalArgumentException {

        if(pathFile==null){
            throw new IllegalArgumentException("Not allowed to give the path file as null");
        }

        if(pathFile.equals( "" )){
            throw new IllegalArgumentException("Not allowed to give the path file as empty");
        }

        List<City> listCities=new ArrayList<>();

        FileReader fileReader=new FileReader( pathFile );
        BufferedReader file=new BufferedReader( fileReader );

        String textLine=file.readLine();
        if(textLine!=null) {
            //Extract column names
            var listAttributesNames=extractValuesFromString( textLine );

            //Associate column name with position in string
            Map<String,Integer> position=new HashMap<>();
            int i=0;
            for(String columnName:listAttributesNames) {
                position.put( columnName,i++);
            }

            //Extract values from each line of text
            //id number(8,0),country_name varchar2(100),city_name varchar2(100),capital number(1,0),latitude float,longitude float,population number(10,0)
            int id=0;
            while((textLine=file.readLine())!=null) {
                var listAttributesValues=extractValuesFromString( textLine );

                //Check if the city is valid(passes criteria for being added to the database)
                if(isListAttributesValid(listAttributesValues,position)) {
                    String countryName=listAttributesValues.get( position.get( "country" ) );
                    String cityName=listAttributesValues.get( position.get( "city_ascii" ) );
                    boolean isCapital=listAttributesValues.get( position.get( "capital" ) ).equals( "primary" );
                    double latitude=Double.parseDouble(listAttributesValues.get( position.get( "lat" ) ));
                    double longitude=Double.parseDouble( listAttributesValues.get( position.get( "lng" ) ) );
                    long population=(long)Double.parseDouble( listAttributesValues.get( position.get( "population" ) ) );

                    listCities.add( new City( id , countryName,cityName,isCapital,latitude,longitude,population ) );

                    ++id;
                }
            }
        }

        return listCities;
    }


}
