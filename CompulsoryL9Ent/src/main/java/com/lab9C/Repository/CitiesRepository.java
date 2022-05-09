package com.lab9C.Repository;

import com.lab9C.Entities.CitiesEntity;

public interface CitiesRepository {
    void create( CitiesEntity citiesEntity );
    CitiesEntity findById(int id);
    CitiesEntity findByName(String name);
}
