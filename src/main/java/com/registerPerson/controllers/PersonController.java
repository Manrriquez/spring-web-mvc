package com.registerPerson.controllers;


import com.registerPerson.models.PersonModel;
import com.registerPerson.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@Controller
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    //@GetMapping(value = "registerPerson")
    @RequestMapping(value = "/registerPerson", method = RequestMethod.GET)
    public ModelAndView init() {
        ModelAndView modelAndView = new ModelAndView("register/registerPerson");
        modelAndView.addObject("personobj", new PersonModel());
        return modelAndView;
    }

//    @PostMapping(value = "/savePerson")
    @RequestMapping(value = "**/savePerson", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView savePerson(PersonModel person) {

        personRepository.save(person);
        ModelAndView andView = new ModelAndView("register/registerPerson");
        Iterable<PersonModel> personsIt = personRepository.findAll();
        andView.addObject("persons", personsIt);
        andView.addObject("personobj", new PersonModel());
        return andView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/listPerson")
    public ModelAndView persons() {
        ModelAndView andView = new ModelAndView("register/registerPerson");

        Iterable<PersonModel> personsIt = personRepository.findAll();
        andView.addObject("persons", personsIt);
        andView.addObject("personobj", new PersonModel());
        return andView;
    }
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "/editPerson/{idPerson}")
    public ModelAndView editPerson(@PathVariable("idPerson") Long idPerson) {
        Optional<PersonModel> person = personRepository.findById(idPerson);
        ModelAndView modelAndView = new ModelAndView("register/registerPerson");
        modelAndView.addObject("personobj", person.get());

        return  modelAndView;
    }


    @RequestMapping(method = RequestMethod.POST, value = "**/searchPerson")
    public ModelAndView searchName(@RequestParam("searchName") String searchName) {
        ModelAndView modelAndView = new ModelAndView("register/registerPerson");
        modelAndView.addObject("persons", personRepository.PersonNameSearch(searchName));
        modelAndView.addObject("personobj", new PersonModel());

        return modelAndView;
    }



}
