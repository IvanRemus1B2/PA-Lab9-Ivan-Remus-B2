package com.lab9C.Repository;

import com.lab9C.Entities.CitiesEntity;
import com.lab9C.Main.SingletonEntityManagerFactory;

import javax.persistence.EntityManager;

public class CitiesRepositoryImp implements CitiesRepository{
    @Override
    public void create( CitiesEntity citiesEntity ) {
        EntityManager entityManager= SingletonEntityManagerFactory.getFactory().createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist( citiesEntity );
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public CitiesEntity findById( int id ) {
        return null;
    }

    @Override
    public CitiesEntity findByName( String name ) {
        return null;
    }
}
