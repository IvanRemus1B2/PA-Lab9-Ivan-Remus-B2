package com.lab9C;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "COUNTRIES", schema = "PA_LAB8_ACCOUNT", catalog = "")
public class CountriesEntity {
    @Basic
    @Column(name = "ID")
    private Short id;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "CODE")
    private String code;
    @Basic
    @Column(name = "CONTINENT_NAME")
    private String continentName;

    public Short getId() {
        return id;
    }

    public void setId( Short id ) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode( String code ) {
        this.code = code;
    }

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName( String continentName ) {
        this.continentName = continentName;
    }

    @Override
    public boolean equals( Object o ) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountriesEntity that = (CountriesEntity) o;
        return Objects.equals( id , that.id ) && Objects.equals( name , that.name ) && Objects.equals( code , that.code ) && Objects.equals( continentName , that.continentName );
    }

    @Override
    public int hashCode() {
        return Objects.hash( id , name , code , continentName );
    }
}
