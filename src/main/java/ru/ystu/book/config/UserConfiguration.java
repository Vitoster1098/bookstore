package ru.ystu.book.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import ru.ystu.book.entity.Role;
import ru.ystu.book.entity.User;
import ru.ystu.book.repository.UserRepository;
import javax.annotation.PostConstruct;
import java.util.Collections;

@Configuration
@RequiredArgsConstructor
public class UserConfiguration {
    @Autowired
    private UserRepository userRep;

    @PostConstruct
    public void init() {
        //Создание уважаемого администратора
        User user = new User();
        user.setRoles(Collections.singleton(Role.ADMIN));
        user.setUsername("admin");
        user.setPassword("admin");
        user.setId(0L);
        userRep.save(user);
    }
}