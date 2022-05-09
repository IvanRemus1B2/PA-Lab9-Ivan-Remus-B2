package com.lab9C.Entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CONTINENTS", schema = "PA_LAB8_ACCOUNT", catalog = "")
public class ContinentsEntity {
    @Basic
    @Column(name = "ID")
    private Short id;
    @Basic
    @Column(name = "NAME")
    private String name;

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

    @Override
    public boolean equals( Object o ) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContinentsEntity that = (ContinentsEntity) o;

        if (id != null ? !id.equals( that.id ) : that.id != null) return false;
        if (name != null ? !name.equals( that.name ) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + ( name != null ? name.hashCode() : 0 );
        return result;
    }
}
