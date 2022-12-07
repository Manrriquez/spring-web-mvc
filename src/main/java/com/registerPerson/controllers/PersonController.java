package com.registerPerson.controllers;


import com.registerPerson.models.PersonModel;
import com.registerPerson.models.TelephoneModel;
import com.registerPerson.repositories.PersonRepository;
import com.registerPerson.repositories.TelephoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TelephoneRepository telephoneRepository;

    //@GetMapping(value = "registerPerson")
    @RequestMapping(value = "/registerPerson", method = RequestMethod.GET)
    public ModelAndView init() {
        ModelAndView modelAndView = new ModelAndView("register/registerPerson");
        Iterable<PersonModel> personsIt = personRepository.findAll();
        modelAndView.addObject("persons", personsIt);
        modelAndView.addObject("personobj", new PersonModel());

        return modelAndView;
    }

//    @PostMapping(value = "/savePerson")
    @RequestMapping(value = "**/savePerson", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView savePerson(@Valid PersonModel person, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            ModelAndView modelAndView = new ModelAndView("register/registerPerson");
            Iterable<PersonModel> personsIt = personRepository.findAll();
            modelAndView.addObject("persons", personsIt);
            modelAndView.addObject("personobj", person);

            List<String> msg = new ArrayList<String>();
            for(ObjectError objectError : bindingResult.getAllErrors()) {
                msg.add(objectError.getDefaultMessage());
            }
            modelAndView.addObject("msg", msg);
            return modelAndView;
        }

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

    @RequestMapping(method = RequestMethod.GET, value = "/deletePerson/{idPerson}")
    public ModelAndView deletePerson(@PathVariable("idPerson") Long idPerson) {

        personRepository.deleteById(idPerson);
        ModelAndView modelAndView = new ModelAndView("register/registerPerson");
        modelAndView.addObject("persons", personRepository.findAll());
        modelAndView.addObject("personobj", new PersonModel());

        return  modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, value = "**/searchPerson")
    public ModelAndView searchName(@RequestParam("searchName") String searchName) {
        ModelAndView modelAndView = new ModelAndView("register/registerPerson");
        modelAndView.addObject("persons", personRepository.PersonNameSearch(searchName));
        modelAndView.addObject("personobj", new PersonModel());

        return modelAndView;
    }

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "/telephone/{idPerson}")
    public ModelAndView telephone(@PathVariable("idPerson") Long idPerson) {

        Optional<PersonModel> person = personRepository.findById(idPerson);

        ModelAndView modelAndView = new ModelAndView("register/telephone");
        modelAndView.addObject("personobj", person.get());
        modelAndView.addObject("telephone", telephoneRepository.getTelephone(idPerson));

        return  modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "**/addTelephonePerson/{idPerson}")
    public ModelAndView addTelephonePerson
            (TelephoneModel telephone, @PathVariable("idPerson") Long idPerson){

        PersonModel person = personRepository.findById(idPerson).get();

        if(telephone != null && telephone.getNumberTelephone() != null
                && telephone.getNumberTelephone().isEmpty() || telephone.getNumberTelephone() == null) {

            ModelAndView modelAndView = new ModelAndView("register/telephone");
            modelAndView.addObject("personobj", person);
            modelAndView.addObject("telephone", telephoneRepository.getTelephone(idPerson));

            List<String> msg = new ArrayList<>();
            msg.add("Numero deve ser informado!");
            modelAndView.addObject("msg", msg);

            return modelAndView;

        }

        telephone.setPerson(person);
        telephoneRepository.save(telephone);

        ModelAndView modelAndView = new ModelAndView("register/telephone");
        modelAndView.addObject("personobj", person);
        modelAndView.addObject("telephone", telephoneRepository.getTelephone(idPerson));

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/deleteTelephone/{idTelephone}")
    public ModelAndView deleteTelephone(@PathVariable("idTelephone") Long idTelephone) {
        PersonModel person = telephoneRepository.findById(idTelephone).get().getPerson();
        telephoneRepository.deleteById(idTelephone);
        ModelAndView modelAndView = new ModelAndView("register/telephone");
        modelAndView.addObject("personobj", new PersonModel());
        modelAndView.addObject("telephone", telephoneRepository.getTelephone(person.getId()));

        return  modelAndView;
    }

}
