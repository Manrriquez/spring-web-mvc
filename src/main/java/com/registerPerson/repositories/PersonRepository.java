package com.registerPerson.repositories;

import com.registerPerson.models.PersonModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;




@Repository
@Transactional
public interface PersonRepository extends CrudRepository<PersonModel, Long> {
}
