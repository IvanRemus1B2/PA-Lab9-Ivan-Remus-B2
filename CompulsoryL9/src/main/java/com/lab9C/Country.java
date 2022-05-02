package com.lab9C;


/**
 * Represents a country that has an identifier(int),name(string),code(string) and the name of the continent where this city is located(as a string)
 */
public class Country {
    private final int id;
    private final String name;
    private final String code;
    private final String continentName;

    /**
     * Constructor for the Country class
     * @param id the identifier for the country(as an int)
     * @param countryName the name of the country(string)
     * @param code the code of the country(string)
     * @param continentName the name of the continent where the country is located(string)
     * @throws IllegalArgumentException thrown if any of parameters(countryName,code and continentName) is null or empty
     */
    public Country(int id,String countryName,String code,String continentName) throws IllegalArgumentException {

        if(countryName==null) {
            throw new IllegalArgumentException( "Not allowed to give the name of the country as null" );
        }

        if(countryName.equals( "" )) {
            throw new IllegalArgumentException( "Not allowed to give the name of the country as empty" );
        }

        if(code==null) {
            throw new IllegalArgumentException( "Not allowed to give the code of the country as null" );
        }

        if(code.equals( "" )) {
            throw new IllegalArgumentException( "Not allowed to give the code of the country as empty" );
        }

        if(continentName==null) {
            throw new IllegalArgumentException( "Not allowed to give the name of the continent for this country as null" );
        }

        if(continentName.equals( "" )) {
            throw new IllegalArgumentException( "Not allowed to give the name of the continent for this country as empty" );
        }

        this.id=id;
        this.name=countryName;
        this.code=code;
        this.continentName=continentName;
    }

    /**
     * Getters for the id of a country
     * @return the id as an int
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for the name for the country
     * @return the name as a string
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the code of the country
     * @return the code as a string
     */
    public String getCode() {
        return code;
    }

    /**
     * Getter for the name of the continent where the country is located
     * @return the name of the continent as a string
     */
    public String getContinentName() {
        return continentName;
    }

    /**
     * Computes the information about a city as a string
     * @return a string
     */
    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", continentName='" + continentName + '\'' +
                '}';
    }
}
