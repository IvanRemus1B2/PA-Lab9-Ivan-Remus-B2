package com.lab9C.Repository;

import com.lab9C.Entities.CountriesEntity;
import com.lab9C.Main.SingletonEntityManagerFactory;

import javax.persistence.EntityManager;

public class CountriesRepositoryImp implements CountriesRepository{
    @Override
    public void create( CountriesEntity continentsEntity ) {
        EntityManager entityManager= SingletonEntityManagerFactory.getFactory().createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist( continentsEntity );
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public CountriesEntity findById( int id ) {
        return null;
    }

    @Override
    public CountriesEntity findByName( String name ) {
        return null;
    }
}
