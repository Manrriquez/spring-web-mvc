package com.registerPerson.repositories;


import com.registerPerson.models.UserModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<UserModel, Long> {

    @Query("select u from UserModel u where u.login = ?1")
    UserModel findUserByLogin(String login);
}
