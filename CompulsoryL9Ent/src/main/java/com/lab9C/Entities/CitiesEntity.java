package com.lab9C.Entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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

        if (id != null ? !id.equals( that.id ) : that.id != null) return false;
        if (countryName != null ? !countryName.equals( that.countryName ) : that.countryName != null) return false;
        if (cityName != null ? !cityName.equals( that.cityName ) : that.cityName != null) return false;
        if (capital != null ? !capital.equals( that.capital ) : that.capital != null) return false;
        if (latitude != null ? !latitude.equals( that.latitude ) : that.latitude != null) return false;
        if (longitude != null ? !longitude.equals( that.longitude ) : that.longitude != null) return false;
        if (population != null ? !population.equals( that.population ) : that.population != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + ( countryName != null ? countryName.hashCode() : 0 );
        result = 31 * result + ( cityName != null ? cityName.hashCode() : 0 );
        result = 31 * result + ( capital != null ? capital.hashCode() : 0 );
        result = 31 * result + ( latitude != null ? latitude.hashCode() : 0 );
        result = 31 * result + ( longitude != null ? longitude.hashCode() : 0 );
        result = 31 * result + ( population != null ? population.hashCode() : 0 );
        return result;
    }
}
