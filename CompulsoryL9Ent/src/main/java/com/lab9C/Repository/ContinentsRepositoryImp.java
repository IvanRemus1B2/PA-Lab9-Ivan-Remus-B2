package com.lab9C.Repository;

import com.lab9C.Entities.ContinentsEntity;
import com.lab9C.Main.SingletonEntityManagerFactory;

import javax.persistence.EntityManager;

public class ContinentsRepositoryImp implements ContinentsRepository {
    @Override
    public void create( ContinentsEntity continentsEntity ) {
        EntityManager entityManager= SingletonEntityManagerFactory.getFactory().createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist( continentsEntity );
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public ContinentsEntity findById( int id ) {
        return null;
    }

    @Override
    public ContinentsEntity findByName( String name ) {
        return null;
    }
}
