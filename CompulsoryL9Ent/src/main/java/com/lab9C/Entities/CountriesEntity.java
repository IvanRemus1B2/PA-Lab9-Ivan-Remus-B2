package com.lab9C.Entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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

        if (id != null ? !id.equals( that.id ) : that.id != null) return false;
        if (name != null ? !name.equals( that.name ) : that.name != null) return false;
        if (code != null ? !code.equals( that.code ) : that.code != null) return false;
        if (continentName != null ? !continentName.equals( that.continentName ) : that.continentName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + ( name != null ? name.hashCode() : 0 );
        result = 31 * result + ( code != null ? code.hashCode() : 0 );
        result = 31 * result + ( continentName != null ? continentName.hashCode() : 0 );
        return result;
    }
}
