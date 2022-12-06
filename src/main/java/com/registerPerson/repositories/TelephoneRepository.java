package com.registerPerson.repositories;


import com.registerPerson.models.TelephoneModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TelephoneRepository extends CrudRepository<TelephoneModel, Long> {

    @Query("select t from TelephoneModel t where t.person.id = ?1")
    public List<TelephoneModel> getTelephone(Long personid);
}
