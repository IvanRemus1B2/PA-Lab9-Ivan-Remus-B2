package com.lab9C;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Represents a generator that will load values for the tables FAKE_CITIES and SISTER_CITIES in the database
 */
public class Generator {
    private final DatabaseObject databaseObject;

    /**
     * Constructor for the Generator class
     * @param databaseObject the database object with which the insertion of data will be done
     * @throws IllegalArgumentException thrown if the database object given is null
     */
    public Generator(DatabaseObject databaseObject) throws IllegalArgumentException{

        if(databaseObject==null){
            throw new IllegalArgumentException("Not allowed to give the database object as null");
        }

        this.databaseObject=databaseObject;
    }

    /**
     * Generates a given number of fake cities and sister relationships amongst them which will be inserted using the database object and a thread pool executor
     * @param numberThreads the number of threads that the thread pool executor can use
     * @param numberCities the number of fake cities to be generated and inserted
     * @param sisterCitiesProbability the probability that for each 2 cities have a sister relationship
     * @throws InterruptedException thrown if interrupted while waiting
     */
    public void generateFakeCitiesAndSisterRelationships(int numberThreads,int numberCities,double sisterCitiesProbability) throws InterruptedException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool( numberThreads );

        for ( int i = 1 ; i <= numberCities ; ++i ) {
            InsertFakeCityTask task = new InsertFakeCityTask( databaseObject , i );

            executor.execute( task );
        }


        double probability;
        for ( int id1 = 1 ; id1 < numberCities ; ++id1 ) {
            for ( int id2 = id1 + 1 ; id2 <= numberCities ; ++id2 ) {
                probability = Math.random();

                if (probability <= sisterCitiesProbability) {

                    InsertSisterCitiesTask task = new InsertSisterCitiesTask( databaseObject , id1 , id2 );

                    executor.execute( task );
                }
            }
        }

        executor.shutdown();

        boolean done;
        do{
            done= executor.awaitTermination(10,TimeUnit.SECONDS);
        } while(!done);
    }
}