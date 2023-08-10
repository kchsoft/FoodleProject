package com.foodle.app.Controller;

import com.foodle.app.Domain.RecipePostDto;
import com.foodle.app.Service.RecipePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @ResponseBody
    @DeleteMapping(value = "/recipepost/{bno}",produces = "text/plain; charset=utf-8")
    public ResponseEntity<String> deletePost(@PathVariable int bno, HttpServletRequest request, RedirectAttributes rattr){
        // get user name
        HttpSession session = request.getSession();
        String userName = (String)session.getAttribute("id");

        // set header
        HttpHeaders header = new HttpHeaders();
        header.set(HttpHeaders.CONTENT_TYPE, "text/plain; charset=utf-8");

        String message;
        if(postService.isUserEqualWriter(userName, bno)) {
            if(1 == postService.deleteOnePost(bno)) // User == Writer
                return new ResponseEntity<String>("삭제가 완료되었습니다.",header, HttpStatus.OK); // Success 200
            else
                return new ResponseEntity<String>("삭제에 실패했습니다.",header, HttpStatus.INTERNAL_SERVER_ERROR); // Fail 500
        }
        else // User != Writer
            return new ResponseEntity<String>("해당 게시물을 작성한 사람만 삭제할 수 있습니다.",header, HttpStatus.FORBIDDEN); // 403
    }
}
