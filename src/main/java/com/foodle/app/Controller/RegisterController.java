package com.foodle.app.Controller;

import com.foodle.app.Domain.UserDto;
import com.foodle.app.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegisterController {
    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String registerPage(){
        return "Auth/register";
    }

//    @PostMapping(value = "/register/idcheck", produces = "text/plain; charset=UTF-8")
    @PostMapping("/register/idcheck")
    @ResponseBody
    public ResponseEntity<String> idDupCheck(@RequestBody String id){
        String message;
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "text/plain; charset=utf-8");

        if (userService.isIdExist(id)) {
            message = "중복된 ID가 있습니다.";
        }else{
            message = "사용 가능한 ID 입니다.";
        }
        return new ResponseEntity<String>(message,headers,HttpStatus.OK);
    }

    @PostMapping(value = "/register", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String registerUser(@RequestBody UserDto user){
        String message;

        System.out.println("user = " + user);
        if (userService.insertUserInfo(user)) {
            message = "회원가입에 성공하였습니다.";
            return message;
        } else{
            message = "알 수 없는 이유로 회원가입에 실패하였습니다.";
            return message;
        }
    }

}
