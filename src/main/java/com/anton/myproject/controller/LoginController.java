package com.anton.myproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        System.out.println("uuuuu" );
        return "login";
}

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("message", "Вы успешно вошли в систему!"); // Добавляем сообщение для отображения
        return "home"; // Возвращает представление home.html
}

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        System.out.println("yyyyyyy" );
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        return "home"; // Вернуть страницу логина для наглядности
    }

}