package com.registerPerson.repositories;

import com.registerPerson.models.PersonModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface PersonRepository extends CrudRepository<PersonModel, Long> {

    @Query("select p from PersonModel p where p.firstName like %?1%")
    List<PersonModel> PersonNameSearch(String firstName);
}
