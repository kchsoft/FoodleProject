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
        return "register";
    }

//    @PostMapping(value = "/register/idcheck", produces = "text/plain; charset=UTF-8")
    @PostMapping("/register/idcheck")
    @ResponseBody
    public ResponseEntity<String> idDupCheck(@RequestBody String id){

        String message;
        if (userService.isIdExist(id)) {
            message = "중복된 ID가 있습니다.";
        }else{
            message = "사용 가능한 ID 입니다.";
        }
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "text/plain; charset=UTF-8");
        ResponseEntity<String> entity = new ResponseEntity<String>(message, headers,HttpStatus.OK);
        return entity;
    }

    @PostMapping("/register")
    public String registerUser(UserDto user){

        return "main";
    }

}
