package com.registerPerson.services;


import com.registerPerson.models.UserModel;
import com.registerPerson.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ImplementsUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepository.findUserByLogin(username);

        if(user == null) {
            throw  new UsernameNotFoundException("Usuario não foi encontrado");
        }


        return new User(user.getLogin(), user.getPassword(), user.isEnabled(),
                true, true, true, user.getAuthorities());
    }
}
