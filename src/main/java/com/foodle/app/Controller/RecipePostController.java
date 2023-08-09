package com.foodle.app.Controller;

import com.foodle.app.Domain.RecipePostDto;
import com.foodle.app.Service.RecipePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Controller
public class RecipePostController {

    final private RecipePostService PostService;

    @Autowired
    public RecipePostController(RecipePostService service) {
        this.PostService = service;
    }

    @GetMapping("/recipepost")
    public String postWritePage(){
        return "/RecipeBoard/recipewrite";
    }

    @PostMapping(value = "/recipepost", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String writePost(@RequestBody RecipePostDto recipePostDto, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        recipePostDto.setWriter((String)session.getAttribute("id"));
        recipePostDto.setRegister_date(LocalDateTime.now());

        String message;
        if(1 == PostService.writePost(recipePostDto)) // Success
            message = "게시물 작성이 완료되었습니다.";
        else
            message = "게시물 작성에 실패했습니다.";
        return message;
    }
}
