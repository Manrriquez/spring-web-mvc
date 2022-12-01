package com.registerPerson.controllers;


import com.registerPerson.models.PersonModel;
import com.registerPerson.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    //@GetMapping(value = "registerPerson")
    @RequestMapping(value = "/registerPerson", method = RequestMethod.GET)
    public String init() {
        return "register/registerPerson";
    }

    //    @PostMapping(value = "savePerson")
    @RequestMapping(value = "/savePerson", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView savePerson(PersonModel person) {

        personRepository.save(person);
        ModelAndView andView = new ModelAndView("register/registerPerson");
        Iterable<PersonModel> personsIt = personRepository.findAll();
        andView.addObject("persons", personsIt);
        return andView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "listPerson")
    public ModelAndView persons() {
        ModelAndView andView = new ModelAndView("register/registerPerson");

        Iterable<PersonModel> personsIt = personRepository.findAll();
        andView.addObject("persons", personsIt);
        return andView;
    }


}
