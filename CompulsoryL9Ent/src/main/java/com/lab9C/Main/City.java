package com.lab9C.Main;

import java.util.Objects;

/**
 * Represents a city
 */
public class City {
    private final int id;
    private final String countryName;
    private final String cityName;
    private final boolean isCapital;
    private final double latitude;
    private final double longitude;
    private final long population;

    /**
     * Constructor for the City class
     * @param id the id given to the city by the user
     * @param countryName the name of the country where the city is located
     * @param cityName the name of the city
     * @param isCapital boolean value,true meaning that this city is the capital of its country,false otherwise
     * @param latitude the latitude of the city
     * @param longitude the longitude of the city
     * @param population the population of the city
     * @throws IllegalArgumentException thrown if: countryName or cityName is null or empty,latitude isn't a value in [-90,90],longitude isn't a value between [-180,180],population is a negative value
     */
    public City( int id , String countryName , String cityName , boolean isCapital , double latitude , double longitude , long population ) throws IllegalArgumentException {

        if(countryName==null) {
            throw new IllegalArgumentException( "The country name for the city cannot be null" );
        }

        if(countryName.equals( "" )) {
            throw new IllegalArgumentException( "The country name for the city cannot be empty" );
        }

        if(cityName==null) {
            throw new IllegalArgumentException( "The city name for the city cannot be null" );
        }

        if(cityName.equals( "" )) {
            throw new IllegalArgumentException( "The city name for the city cannot be empty" );
        }

        if(latitude<-90||latitude>90){
            throw new IllegalArgumentException("The latitude for the city cannot be strictly smaller than -90 or strictly bigger than 90");
        }

        if(longitude<-180||longitude>180){
            throw new IllegalArgumentException("The longitude for the city cannot be strictly smaller than -180 or strictly bigger than 180");
        }

        if(population<0){
            throw new IllegalArgumentException("The city population cannot be negative");
        }

        this.id = id;
        this.countryName = countryName;
        this.cityName = cityName;
        this.isCapital = isCapital;
        this.latitude = latitude;
        this.longitude = longitude;
        this.population = population;
    }

    /**
     * Constructor for the City class that only requires the id,country name,city name and population
     * @param id the id
     * @param countryName the name of the country where the city is located
     * @param cityName the city name
     * @param population the population of the city
     * @throws IllegalArgumentException thrown if: countryName or cityName is null or empty or if population is a negative value
     */
    public City( int id , String countryName , String cityName , long population ) throws IllegalArgumentException {

        if(countryName==null) {
            throw new IllegalArgumentException( "The country name for the city cannot be null" );
        }

        if(countryName.equals( "" )) {
            throw new IllegalArgumentException( "The country name for the city cannot be empty" );
        }

        if(cityName==null) {
            throw new IllegalArgumentException( "The city name for the city cannot be null" );
        }

        if(cityName.equals( "" )) {
            throw new IllegalArgumentException( "The city name for the city cannot be empty" );
        }

        if(population<0){
            throw new IllegalArgumentException("The city population cannot be negative");
        }

        this.id = id;
        this.countryName = countryName;
        this.cityName = cityName;
        this.population = population;

        this.isCapital = false;
        this.latitude = 0;
        this.longitude = 0;
    }

    /**
     * Gets the id of the city
     * @return id as an int value
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the name of the country where the city is located
     * @return a string for the name of the country
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Gets the name of the city
     * @return a string for the city name
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Gets a boolean value referring to whether the city is a capital or not
     * @return a boolean value,true if this city is the capital of the country,false otherwise
     */
    public boolean isCapital() {
        return isCapital;
    }

    /**
     * Gets the latitude of the city
     * @return a double value
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Gets the longitude of the city
     * @return a double value
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Gets the number of people living(population) of the city
     * @return an int value referring to the number of people that live in the city
     */
    public long getPopulation() {
        return population;
    }

    /**
     * Gets the information about the city as a string
     * @return a string
     */
    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", countryName='" + countryName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", isCapital=" + isCapital +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", population=" + population +
                '}';
    }

    /**
     * Checks whether this object and the o object are equal.2 cities are considered equal if they have the same name and the same country name
     * @param o the other city
     * @return a boolean value,true if they are equal,false otherwise
     */
    @Override
    public boolean equals( Object o ) {
        if (this == o) return true;
        if (!( o instanceof City city )) return false;
        return getId()==city.getId()&&getCountryName().equals( city.getCountryName() ) && getCityName().equals( city.getCityName() );
    }

    /**
     * Computes a haso code based on the id,country name and city name
     * @return a hash code as an int value
     */
    @Override
    public int hashCode() {
        return Objects.hash( getId() , getCountryName() , getCityName() );
    }

    /**
     * Returns a string representation of the city ignoring the latitude,longitude and capital fields
     * @return a string
     */
    public String getStringRepresentationForFakeCity(){
        return "City{" +
                "id=" + id +
                ", countryName='" + countryName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", population=" + population +
                '}';
    }
}
