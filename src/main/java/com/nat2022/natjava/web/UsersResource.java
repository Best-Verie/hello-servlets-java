package com.nat2022.natjava.web;

import com.nat2022.natjava.dao.User;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("users")
public class UsersResource {

    @GetMapping
    public String getAllUsers(Model model, HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();

//        HttpRequest request1 = new

        ResponseEntity<User[]> users = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/users", User[].class);

        model.addAttribute("users", users.getBody());

        String token = request.getSession().getAttribute("token").toString();

        return "users/all";
    }

    @GetMapping("/new")
    public String newUser() {
        return "users/newUser";
    }

    @PostMapping
    public String createUser(String title, String description, HttpServletRequest request) {
        System.out.println(title + " - " + description);

        request.getSession().setAttribute("user_token", "123");

        return "redirect:/users";
    }
}
