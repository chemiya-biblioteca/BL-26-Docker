package com.uva.users.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;




@Entity
@Table(name = "UsuarioNuevo")
public class Usuario {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique=true,name="name")
    @Size(max=45)
    private String name;

    @Column(name="first_name")
    @Size(max=45)
    private String firstName;

    @Column(name="last_name")
    @Size(max=45)
    private String lastName;

    

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

   

    

    Usuario(){

    }

    Usuario(int id, String name,String firstName,String lastName){
        this.id=id;
        this.name=name;
        this.firstName=firstName;
        this.lastName=lastName;
        
    }

}


/*
{
"name":"cdhes22dd222223",
  "firstName":"chema",
   "lastName":"lozano",
   "email":"csshss1322@gmail.com",
   "password":"cont1234",
  "role":0,
  "enabled":true,
  "createdAt":"2017-04-03",
  "updatedAt":"2017-03-03"
  
  
}




*/ 
