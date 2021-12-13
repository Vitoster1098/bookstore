package ru.ystu.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartController {
    @GetMapping("/")
    public String welcome(Model model){
        return "redirect:books/";
    }
}
