package ru.ystu.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ystu.book.entity.Cart;
import ru.ystu.book.entity.Role;
import ru.ystu.book.entity.User;
import ru.ystu.book.repository.CartRepository;
import ru.ystu.book.repository.UserRepository;

import java.security.Principal;
import java.util.*;

@Controller
public class AdminPanelController {
    @Autowired
    private UserRepository userRep;
    @Autowired
    private CartRepository cartRep;

    @GetMapping("/panel")
    public String panelview(Model model, Principal user){
        User curUser = userRep.findByUsername(user.getName());
        Set<Role> role = curUser.getRoles();
        if(role.toArray()[0] == Role.ADMIN){
            if(role.toArray()[0] == Role.USER){
                model.addAttribute("user_role", "USER");
            } else {
                model.addAttribute("user_role", "ADMIN");
            }
            model.addAttribute("cart_count", cartRep.countAllByUser(curUser.getId()));

            List<User> userList = userRep.findAll();
            List<Cart> cartList = cartRep.findAll();
            int[] cart_count = new int[userList.size()];

            cartList.clear();

            int count = 0;
            for (Cart cart : cartRep.findAll()){
                cartList.add(cartRep.findCartByUser(userList.get(count).getId()));
            }
            model.addAttribute("users", userList);
            model.addAttribute("cart_count", cart_count);
            model.addAttribute("carts", cartList);

            return "admin_panel";
        } else {
            return "redirect:/";
        }
    }
}