package com.registerPerson.models;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Person")
public class PersonModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    private int years;

    @OneToMany(mappedBy = "person", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<TelephoneModel> telephone;

    public List<TelephoneModel> getTelephone() {
        return telephone;
    }

    public void setTelephone(List<TelephoneModel> telephone) {
        this.telephone = telephone;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
