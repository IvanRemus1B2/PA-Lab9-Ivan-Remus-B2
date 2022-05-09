package com.lab9C.Main;

import com.lab9C.Entities.CitiesEntity;
import com.lab9C.Repository.CitiesRepository;
import com.lab9C.Repository.CitiesRepositoryImp;

import java.sql.*;

public class Main {
    public static void main(String[] args){
        try{
            CitiesEntity city1=new CitiesEntity();
            city1.setCityName( "Iasi" );
            city1.setCapital( false );
            city1.setLatitude( 28.20202 );
            city1.setLongitude( -20.099 );
            city1.setPopulation( 350_000 );

            CitiesRepository citiesRepository=new CitiesRepositoryImp();
            citiesRepository.create( city1 );

        }
        catch (IllegalArgumentException exception) {
            exception.printStackTrace();
        }
    }
}
