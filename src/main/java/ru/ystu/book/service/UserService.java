package ru.ystu.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.ystu.book.config.SelfUserDetails;
import ru.ystu.book.entity.User;
import ru.ystu.book.repository.UserRepository;

public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRep;

    public UserService(User user){}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRep.findByUsername(username);
        return new SelfUserDetails(user);
    }
}