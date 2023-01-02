package com.uva.users.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.*;



@Entity
@Table(name = "Vino")
public class Vino implements Serializable {
    @Id
    @GeneratedValue
    private Integer Id;
    @Size(max = 50) // si no se pone esta anotación lo creo por defecto con size=255
    @Column(name = "nombre_comercial")
    private String nombreComercial;
    @Size(max = 30)
    private String denominacion;
    @Size(max = 30)
    private String categoria;
    @Column(nullable = false)
    private Float precio;
    private Integer bodega_id;
    
    public String getDenominacion() {
        return this.denominacion;
    }

    public String getNombre_comercial() {
        return this.nombreComercial;
    }

    public void setNombre_comercial(String nombre_comercial) {
        this.nombreComercial = nombre_comercial;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }


    public Float getPrecio() {
        return this.precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Integer getBodega_id() {
        return this.bodega_id;
    }

    public void setBodega_id(Integer bodega_id) {
        this.bodega_id = bodega_id;
    }
    public Integer getId() {
        return this.Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    Vino() { }
    Vino(String nombre_comercial, String denominacion, String categoria, Float
    precio, Integer bodega) {
    this.nombreComercial = nombre_comercial;
    this.denominacion = denominacion;
    this.categoria = categoria;
    this.precio = precio;
    this.bodega_id = bodega;
    }

    


}
