package com.foodle.app.Controller;

import com.foodle.app.Domain.RecipePostDto;
import com.foodle.app.Service.RecipePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Controller
public class RecipePostController {

    final private RecipePostService postService;

    @Autowired
    public RecipePostController(RecipePostService service) {
        this.postService = service;
    }

    @GetMapping("/recipepost")
    public String postWritePage(){
        return "RecipeBoard/recipewrite";
    }

    @GetMapping("/recipepost/{bno}")
    public String postShowPage(@PathVariable int bno, Model m){
        RecipePostDto recipe = postService.getOnePost(bno);
        m.addAttribute("recipe", recipe);
        System.out.println("recipe = " + recipe);
        return "RecipeBoard/recipepost";
    }

    @PostMapping(value = "/recipepost", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String writePost(@RequestBody RecipePostDto recipePostDto, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        recipePostDto.setWriter((String)session.getAttribute("id"));
        recipePostDto.setRegister_date(LocalDateTime.now());

        String message;
        if(1 == postService.writePost(recipePostDto)) // Success
            message = "게시물 작성이 완료되었습니다.";
        else
            message = "게시물 작성에 실패했습니다.";
        return message;
    }
}
