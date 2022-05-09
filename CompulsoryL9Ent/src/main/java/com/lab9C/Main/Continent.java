package com.lab9C.Main;

/**
 * Represents a continent that has an identifier represented as an integer and a name stored as a string
 */
public class Continent {
    private final int id;
    private final String name;

    /**
     * Constructor for the Continent class
     * @param id the id(as an int) for the continent
     * @param name the name(as a string) of the continent
     * @throws IllegalArgumentException thrown if the name is null or empty
     */
    public Continent(int id,String name) throws IllegalArgumentException {

        if(name==null) {
            throw new IllegalArgumentException( "Not allowed to give the name of the continent as null" );
        }

        if(name.equals( "" )) {
            throw new IllegalArgumentException( "Not allowed to give the name of the continent as null" );
        }

        this.id=id;
        this.name=name;
    }

    /**
     * Getter for the id
     * @return the value of id as an int
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for the name
     * @return the name of the continent as a string
     */
    public String getName() {
        return name;
    }

    /**
     * Computes the continent as a string
     * @return a string that contains the information describing an instance of the Continent class
     */
    @Override
    public String toString() {
        return "Continent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
