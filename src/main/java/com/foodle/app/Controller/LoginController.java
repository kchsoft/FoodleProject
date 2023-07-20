package com.foodle.app.Controller;

import com.foodle.app.Domain.UserDto;
import com.foodle.app.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    final int VALID_INFO = 2; // ID , PASSWORD is valid.
    final int INVALID_INFO = 1; // ID or PASSWORD is invalid.
    final int NULL_INFO = 0; // There is no ID and PASSWORD.
    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "Auth/login";
    }
// cookie
    // session
    @PostMapping("/login") //
    private String loginStart(HttpServletResponse response, HttpServletRequest request, boolean idSave, UserDto LoginUser, Model m) {
        switch (userService.isValidLoginInfo(LoginUser)) {
            case VALID_INFO:
                if(idSave){ // add cookie
                    Cookie cookie = new Cookie("id", LoginUser.getId());
                    cookie.setMaxAge(60*60*24*7); // 7 days
                    response.addCookie(cookie);
                }else{ // remove cookie
                    Cookie cookie = new Cookie("id", "");
                    cookie.setMaxAge(0); // remove
                    response.addCookie(cookie);
                }
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("id", LoginUser.getId());
                return "main";
            case INVALID_INFO:
                m.addAttribute("msg", "잘못된 ID 또는 비밀번호입니다.");
                break;
            case NULL_INFO:
                m.addAttribute("msg", "회원정보가 없습니다.");
                break;
            default:
        }
        return "Auth/login";
    }
}
