package com.lab9C;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        try{
            DatabaseObject databaseObject=new DatabaseObjectImplementation( SingletonDataSource.getSingleConnection() );

            databaseObject.createTables( false,false,false,false,false );

            databaseObject.insertContinent( new Continent(1,"North America") );
            databaseObject.insertContinent( new Continent(2,"South America") );
            databaseObject.insertContinent( new Continent(3,"Europe") );
            databaseObject.insertContinent( new Continent(4,"Africa") );
            databaseObject.insertContinent( new Continent(5,"Australia") );
            databaseObject.insertContinent( new Continent(6,"Asia") );
            databaseObject.insertContinent( new Continent(7,"Antarctica") );


            databaseObject.insertCountry( new Country(1,"USA","1021","North America") );
            databaseObject.insertCountry( new Country(3,"Mexico","12","South America") );
            databaseObject.insertCountry( new Country(2,"Germany","0","Europe") );
            databaseObject.insertCountry( new Country(4,"China","121","Asia") );

            var continent1=databaseObject.getContinentWithId( 5 );
            var continent2=databaseObject.getContinentWithName( "South America" );

            System.out.println(continent1);
            System.out.println(continent2);

            var country1=databaseObject.getCountryWithId( 2 );
            var country2=databaseObject.getCountryWithName( "USA" );
            System.out.println(country1);
            System.out.println(country2);

            System.out.println(databaseObject.getCitiesWithName( "New York" ));

            //Importer.importCityData( "data/worldcities.csv",databaseObject );

            var city1=databaseObject.getCitiesWithId( 5 );
            var city2=databaseObject.getCitiesWithName( "Iasi" );
            System.out.println(city1);
            System.out.println(city2);

            //Print the distance between these cities
            List<String> listCityNames1=new ArrayList<>();
            listCityNames1.add( "Bucharest" );
            listCityNames1.add("Iasi");
            listCityNames1.add( "Brasov" );
            Manager.printDistancesBetweenCities(databaseObject, listCityNames1 );


            List<String> listCityNames2=new ArrayList<>();
            listCityNames2.add( "Paris" );
            listCityNames2.add( "Berlin" );
            listCityNames2.add( "New York" );
            listCityNames2.add( "London" );
            Manager.printDistancesBetweenCities(databaseObject ,listCityNames2 );

            //Generator generator=new Generator( databaseObject );
            //generator.generateFakeCitiesAndSisterRelationships( 32,1000,0.01);

            Manager.printMaximalCliques( databaseObject );

            SingletonDataSource.getSingleConnection().close();
        }
        catch (SQLException | IllegalArgumentException exception) {
            exception.printStackTrace();
        }
    }
}
