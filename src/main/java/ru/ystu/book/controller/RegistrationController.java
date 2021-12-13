package ru.ystu.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.ystu.book.entity.Role;
import ru.ystu.book.entity.User;
import ru.ystu.book.repository.UserRepository;

import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRep;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }
    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        User newUser = userRep.findByUsername(user.getUsername());
        if(newUser != null) {
            model.addAttribute("message", "Пользователь под таким именем уже существует");
            return "registration";
        }
        user.setRoles(Collections.singleton(Role.USER));
        userRep.save(user);
        return "redirect:/login";
    }
}
