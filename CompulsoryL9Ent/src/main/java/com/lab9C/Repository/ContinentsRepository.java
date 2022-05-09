package com.lab9C.Repository;

import com.lab9C.Entities.ContinentsEntity;

public interface ContinentsRepository {
    void create( ContinentsEntity continentsEntity );
    ContinentsEntity findById(int id);
    ContinentsEntity findByName(String name);
}
