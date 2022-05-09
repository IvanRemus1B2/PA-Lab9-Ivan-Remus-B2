package com.lab9C;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

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
        return Objects.equals( id , that.id ) && Objects.equals( name , that.name );
    }

    @Override
    public int hashCode() {
        return Objects.hash( id , name );
    }
}
