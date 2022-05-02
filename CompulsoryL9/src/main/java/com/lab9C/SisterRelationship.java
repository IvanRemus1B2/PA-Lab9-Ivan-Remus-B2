package com.lab9C;

/**
 * Represents a sister relationship formed between 2 cities
 */
public class SisterRelationship {
    private final City city1;
    private final City city2;

    /**
     * Constructor for the SisterRelationship class
     * @param city1 the first city
     * @param city2 the second city
     * @throws IllegalArgumentException thrown if city1 or city2 are null
     */
    public SisterRelationship(City city1,City city2) throws IllegalArgumentException{

        if(city1==null){
            throw new IllegalArgumentException("Not allowed to give the city1 as null");
        }

        if(city2==null){
            throw new IllegalArgumentException("Not allowed to give the city2 as null");
        }

        this.city1=city1;
        this.city2=city2;
    }

    /**
     * Getter for the first city
     * @return a city
     */
    public City getCity1() {
        return city1;
    }

    /**
     * Getter for the second city
     * @return a city
     */
    public City getCity2() {
        return city2;
    }

    /**
     * Determines if 2 sister relationships are equal.They are considered equal only if and only if the cities at the both ends are equal
     * @param o the other sister relationship
     * @return true if they are equal ,false otherwise
     */
    @Override
    public boolean equals( Object o ) {
        if (this == o) return true;
        if (!( o instanceof SisterRelationship that )) return false;
        return (city1.equals( that.city1 ) && city2.equals( that.city2 ))||(city1.equals( that.city2 )&&city2.equals( city1 ));
    }

}
