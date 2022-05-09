package com.lab9C.Main;


import org.jgrapht.Graph;
import org.jgrapht.alg.clique.BronKerboschCliqueFinder;
import org.jgrapht.alg.interfaces.MaximalCliqueEnumerationAlgorithm;
import org.jgrapht.graph.SimpleGraph;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Represents a manager of the data from the database that given features using a databaseObject
 */
public class Manager {

    /**
     * Prints the air distance(the shortest distance) between 2 cities from the given list that are found in the database.In case more cities with the same name is found,they
     * are taken as separate cities and the distance between them is printed as well
     * @param databaseObject the database object used to verify and get the information about a city
     * @param listCityNames the list of city names
     * @throws SQLException thrown if any sql exceptions occur
     * @throws IllegalArgumentException thrown if the databaseObject is null or the list of city names is null
     */
    public static void printDistancesBetweenCities(DatabaseObject databaseObject , List<String> listCityNames) throws SQLException,IllegalArgumentException {

        if(databaseObject==null){
            throw new IllegalArgumentException("Not allowed to give the database object as null");
        }

        if(listCityNames==null){
            throw new IllegalArgumentException("Not allowed to give the list of city names as null");
        }

        Set<City> setCities=new HashSet<>();
        for(String cityName:listCityNames){
            setCities.addAll( databaseObject.getCitiesWithName( cityName ) );
        }

        if(setCities.size()>1){

            List<City> listCities=setCities.stream().toList();

            System.out.println("\nDistances between cities that exist in the database are:");

            int nrCities=listCities.size();
            for(int i=0;i<nrCities-1;++i) {

                City city1=listCities.get( i );

                for ( int j = i + 1 ; j < nrCities ; ++j ) {
                    City city2=listCities.get( j );

                    var distance=computeDistanceBetweenCities( city1,city2 );
                    System.out.println("Distance between "+city1.getCityName()+","+city1.getCountryName()+" and "+city2.getCityName()+","+city2.getCountryName()+" is : "+distance+" km");
                }
            }
        }
        else if(setCities.size()==1) {
            System.out.println("\nOnly 1 city was found in the database with the corresponding name");
        }
        else{
            System.out.println("\nNo city identified by their name has been found in the database");
        }
    }

    /**
     * Computes the distance between 2 cities using the Haversine formula
     * @param city1 the first city
     * @param city2 the second city
     * @return the distance between them given as a double value
     * @throws IllegalArgumentException thrown if the first or second city is null
     */
    public static Double computeDistanceBetweenCities(City city1,City city2) throws IllegalArgumentException{

        if(city1==null){
            throw new IllegalArgumentException("Not allowed to give the first city as null");
        }

        if(city2==null){
            throw new IllegalArgumentException("Not allowed to give the second city as null");
        }

        double radius=6371;

        double latitude1=Math.toRadians( city1.getLatitude() );
        double longitude1=Math.toRadians( city1.getLongitude() );

        double latitude2=Math.toRadians( city2.getLatitude() );
        double longitude2=Math.toRadians( city2.getLongitude() );

        double distanceLong=longitude2-longitude1;
        double distanceLat=latitude2-latitude1;

        double distance=Math.pow( Math.sin( distanceLat/2 ),2 )+Math.cos( latitude1 )*Math.cos( latitude2 )*Math.pow( Math.sin( distanceLong/2 ),2 );

        distance=2*Math.asin( Math.sqrt( distance ) );
        distance*=radius;

        return distance;
    }

    /**
     * Computes an iterator with set containing cities for the graph made by the fake cities and the sister relationships between them using the BronKerboschCliqueFinder class from JGraphT
     * @param databaseObject the database object
     * @return an iterator containing a set of cities representing a clique
     * @throws SQLException thrown if any sql exceptions occur
     * @throws IllegalArgumentException thrown if the database object is null
     */
    public static Iterator<Set<City>> computeMaximalCliques( DatabaseObject databaseObject) throws SQLException,IllegalArgumentException {

        if(databaseObject==null){
            throw new IllegalArgumentException("Not allowed to give the database object as null");
        }

        Graph<City,SisterRelationship> graph=new SimpleGraph<>( SisterRelationship.class );

        var listCities=databaseObject.getFakeCities();

        for(City city:listCities){
            graph.addVertex(city);
        }

        var listSisterRelationships=databaseObject.getSisterRelationships();
        for(SisterRelationship relation:listSisterRelationships){

            graph.addEdge( relation.getCity1(),relation.getCity2(),relation );

        }

        MaximalCliqueEnumerationAlgorithm<City,SisterRelationship> instance=new BronKerboschCliqueFinder<>( graph );
        return instance.iterator();
    }

    /**
     * Prints on the screen all the cliques in the iterator given by computeMaximalCliques method and prints the cliques that have at least 3 cities.If no cliques are found,a proper message will be displayed
     * @param databaseObject the database object
     * @throws SQLException thrown if any sql exceptions occur
     * @throws IllegalArgumentException thrown if the database object given is null
     */
    public static void printMaximalCliques(DatabaseObject databaseObject) throws SQLException,IllegalArgumentException {

        if(databaseObject==null){
            throw new IllegalArgumentException("Not allowed to give the database object value as null");
        }

        var listCliques=computeMaximalCliques( databaseObject );

        System.out.println("\nThe cliques found are: ");

        int countCliques=0;
        while (listCliques.hasNext()) {
            Set<City> setCities = listCliques.next();

            int numberCities=setCities.size();
            if(numberCities>2) {
                System.out.println( "\nClique " + ( countCliques + 1 + " has " + numberCities + " cities :" ) );

                for ( City city : setCities ) {
                    System.out.println( city.getStringRepresentationForFakeCity() );
                }

                ++countCliques;
            }
        }

        if(countCliques==0){
            System.out.println("No cliques found");
        }
    }
}
