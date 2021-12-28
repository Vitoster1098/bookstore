package ru.ystu.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ystu.book.entity.Cart;
import ru.ystu.book.entity.Role;
import ru.ystu.book.entity.User;
import ru.ystu.book.repository.CartRepository;
import ru.ystu.book.repository.UserRepository;

import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/panel")
public class AdminPanelController {
    @Autowired
    private UserRepository userRep;
    @Autowired
    private CartRepository cartRep;

    @GetMapping("/")
    public String panelview(Model model, Principal user){
        User curUser = userRep.findByUsername(user.getName());
        Set<Role> role = curUser.getRoles();
        if(role.toArray()[0] == Role.ADMIN){
            if(role.toArray()[0] == Role.USER){
                model.addAttribute("user_role", "USER");
            } else {
                model.addAttribute("user_role", "ADMIN");
            }

            List<User> userList = userRep.findAll();
            List<Integer> cartList = new ArrayList<>();
            List<Integer> cart_count = new ArrayList<>();

            int count = 0;
            for (User usr : userList){
                List<Cart> tempCart = cartRep.findCartByUser(usr.getId());
                cartList.add(tempCart.size());
                cart_count.add(cartRep.countAllByUser(usr.getId()));
                count++;
            }

            model.addAttribute("users", userList);
            model.addAttribute("cart_counts", cart_count);
            model.addAttribute("cart_count", cartRep.countAllByUser(curUser.getId()));
            model.addAttribute("carts", cartList);

            return "admin_panel";
        } else {
            return "redirect:/";
        }
    }
    @GetMapping("/removeuser/{id}")
    public String remUser(@PathVariable Long id, Principal user, Model model) {
        User curUser = userRep.findByUsername(user.getName());
        Set<Role> role = curUser.getRoles();
        if(role.toArray()[0] == Role.ADMIN) {
            if (role.toArray()[0] == Role.USER) {
                model.addAttribute("user_role", "USER");
            } else {
                model.addAttribute("user_role", "ADMIN");
            }

            Optional<User> usr = userRep.findById(id);
            if (usr.isEmpty()) {
                model.addAttribute("message", "Ошибка при удалении пользователя");
                return "admin_panel";
            }
            else {
                userRep.deleteById(id);
                model.addAttribute("message", "Пользователь удален");
                return "admin_panel";
            }
        }
        else {
            return "redirect:/";
        }
    }
    @GetMapping("/edituser/{id}")
    public String showEditUser(@PathVariable Long id, Model model, Principal user) {
        User curUser = userRep.findByUsername(user.getName());
        Set<Role> role = curUser.getRoles();
        if(role.toArray()[0] == Role.ADMIN) {
            Optional<User> usr = userRep.findById(id);
            if (usr.isEmpty()) {
                model.addAttribute("message", "Пользователь не существует");
                return "admin_panel";
            }
            else {
                model.addAttribute("user", usr.get());
                return "user_edit";
            }
        } else {
            return "redirect:/";
        }
    }
    @PostMapping("/edituser")
    public String editUser(User usr, Model model, Principal user) {
        User curUser = userRep.findByUsername(user.getName());
        Set<Role> role = curUser.getRoles();

        if(role.toArray()[0] == Role.ADMIN) {
            userRep.save(usr);
            model.addAttribute("message", "Пользователь " + usr.getUsername() + " затронут.");
        } else {
            model.addAttribute("message", "Редактировать пользователей могут только с статусом Администратор.");
        }
        return "admin_panel";
    }
}