package com.lab9C.Main;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SingletonEntityManagerFactory {

    private static EntityManagerFactory entityManagerFactory;

    static{
        try{
            entityManagerFactory= Persistence.createEntityManagerFactory( "Unit" );
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static EntityManagerFactory getFactory(){
        return entityManagerFactory;
    }
}
