package com.foodle.app.Controller;

import com.foodle.app.Domain.UserDto;
import com.foodle.app.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    final int VALID_INFO = 2; // ID , PASSWORD is valid.
    final int INVALID_INFO = 1; // ID or PASSWORD is invalid.
    final int NULL_INFO = 0; // There is no ID and PASSWORD.
    @Autowired
    UserService userService;
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    private String loginStart(String id, String password ,Model m){
        switch(isValidLoginInfo(id,password)){
            case VALID_INFO:
                return "main";
            case INVALID_INFO:
                m.addAttribute("msg", "잘못된 ID 또는 비밀번호입니다.");
                break;
            case NULL_INFO:
                m.addAttribute("msg", "회원정보가 없습니다.");
                break;
            default:
        }
        return "login";
    }

    private int isValidLoginInfo(String id,String password) {
        UserDto LoginUser = new UserDto(id, password);
        return userService.userCheck(LoginUser);
    }
}
