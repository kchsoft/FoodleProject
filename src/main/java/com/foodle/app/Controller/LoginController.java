package com.foodle.app.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginStart(String id, String password ,Model m){
        if(isIdExist(id,password)){
            m.addAttribute("msg", "The ID already exists.");
            return "login";
        }

        return "main";
    }

    private boolean isIdExist(String id,String password) {
        return true;
    }
}
