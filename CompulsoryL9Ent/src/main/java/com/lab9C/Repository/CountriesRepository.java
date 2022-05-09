package com.lab9C.Repository;

import com.lab9C.Entities.CountriesEntity;

public interface CountriesRepository {
    void create( CountriesEntity countriesEntity );
    CountriesEntity findById(int id);
    CountriesEntity findByName(String name);
}
