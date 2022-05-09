package com.lab9C.Main;

import com.github.javafaker.Faker;

import java.sql.SQLException;
import java.util.Random;

/**
 * A class that implements the interface Runnable and represents a task that will insert into the database a city with the given id and a
 * random country name,city name and population value
 */
public class InsertFakeCityTask implements Runnable{

    private final DatabaseObject databaseObject;
    private final int id;

    /**
     * Constructor for the InsertFakeCityTask class
     * @param databaseObject the database object
     * @param id the id of the city that will be inserted
     * @throws IllegalArgumentException thrown if the database object is null
     */
    public InsertFakeCityTask( DatabaseObject databaseObject, int id) throws IllegalArgumentException{

        if(databaseObject==null){
            throw new IllegalArgumentException("Not allowed to give the database object as null");
        }

        this.databaseObject=databaseObject;
        this.id=id;
    }

    /**
     * This function represents the task that will be done by inserting the fake city into the FAKE_CITIES table
     */
    @Override
    public void run() {
        try {
            databaseObject.insertFakeCity( generateCity() );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates a fake city with the id saved in this class and a new random country name,city name and population
     * @return a city
     */
    public City generateCity(){
        Faker faker=new Faker();
        String countryName=faker.country().name();
        String cityName=faker.address().cityName();

        Random random=new Random();
        long population=random.nextLong( 0L , (long) 1e8 );


        return new City( id,countryName,cityName,population );
    }
}
