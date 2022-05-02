package com.lab9C;

import java.sql.SQLException;

/**
 * A class that implements the interface Runnable and represents a task that will insert into the database using the database object the sister relationship
 * between the cities with idCity1 and idCity2 given when constructor was called
 */
public class InsertSisterCitiesTask implements Runnable{

    private final DatabaseObject databaseObject;
    private final int idCity1;
    private final int idCity2;

    /**
     * Constructor for the InsertSisterCitiesTask class
     * @param databaseObject the database object
     * @param idCity1 the id of the first city
     * @param idCity2 the id of the second city
     * @throws IllegalArgumentException thrown if the database object is null
     */
    public InsertSisterCitiesTask(DatabaseObject databaseObject,int idCity1,int idCity2) throws IllegalArgumentException{

        if(databaseObject==null){
            throw new IllegalArgumentException("Not allowed to give the database object as null");
        }

        this.databaseObject=databaseObject;
        this.idCity1 =idCity1;
        this.idCity2 =idCity2;
    }

    /**
     * Represents the task run that is inserting into the table SISTER_CITIES the sister relationship between idCity1 and idCity2
     */
    @Override
    public void run() {
        try {
            databaseObject.insertSisterCities( idCity1,idCity2 );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
