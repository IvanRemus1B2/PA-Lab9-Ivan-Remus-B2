package com.lab9C;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "CITIES", schema = "PA_LAB8_ACCOUNT", catalog = "")
public class CitiesEntity {
    @Basic
    @Column(name = "ID")
    private Integer id;
    @Basic
    @Column(name = "COUNTRY_NAME")
    private String countryName;
    @Basic
    @Column(name = "CITY_NAME")
    private String cityName;
    @Basic
    @Column(name = "CAPITAL")
    private Boolean capital;
    @Basic
    @Column(name = "LATITUDE")
    private Double latitude;
    @Basic
    @Column(name = "LONGITUDE")
    private Double longitude;
    @Basic
    @Column(name = "POPULATION")
    private Integer population;

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName( String countryName ) {
        this.countryName = countryName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName( String cityName ) {
        this.cityName = cityName;
    }

    public Boolean getCapital() {
        return capital;
    }

    public void setCapital( Boolean capital ) {
        this.capital = capital;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude( Double latitude ) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude( Double longitude ) {
        this.longitude = longitude;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation( Integer population ) {
        this.population = population;
    }

    @Override
    public boolean equals( Object o ) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CitiesEntity that = (CitiesEntity) o;
        return Objects.equals( id , that.id ) && Objects.equals( countryName , that.countryName ) && Objects.equals( cityName , that.cityName ) && Objects.equals( capital , that.capital ) && Objects.equals( latitude , that.latitude ) && Objects.equals( longitude , that.longitude ) && Objects.equals( population , that.population );
    }

    @Override
    public int hashCode() {
        return Objects.hash( id , countryName , cityName , capital , latitude , longitude , population );
    }
}
