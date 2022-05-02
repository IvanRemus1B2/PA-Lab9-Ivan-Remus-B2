package com.lab9C;

public abstract class AbstractRepository {

    private DatabaseObject databaseObject;

    abstract void create();
    abstract void findById(int id);
    abstract void findByName(String name);
}
