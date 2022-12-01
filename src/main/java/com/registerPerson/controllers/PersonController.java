package com.registerPerson.controllers;


import com.registerPerson.models.PersonModel;
import com.registerPerson.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping(value = "/registerPerson")
    public String index(){
        return "register/registerPerson";
    }

    @PostMapping(value = "/savePerson")
    public String savePerson(PersonModel person) {

        personRepository.save(person);
        return "register/registerPerson";
    }
}
