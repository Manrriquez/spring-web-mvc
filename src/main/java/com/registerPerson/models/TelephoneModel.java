package com.registerPerson.models;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;

@Entity
@Table(name = "telephone")
public class TelephoneModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String numberTelephone;

    private String typeTelephone;

    @ForeignKey(name = "person_id")
    @ManyToOne
    private PersonModel person;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelephone() {
        return numberTelephone;
    }

    public void setTelephone(String telephone) {
        this.numberTelephone = telephone;
    }

    public String getTypeTelephone() {
        return typeTelephone;
    }

    public void setTypeTelephone(String typeTelephone) {
        this.typeTelephone = typeTelephone;
    }

    public PersonModel getPerson() {
        return person;
    }

    public void setPerson(PersonModel person) {
        this.person = person;
    }
}
