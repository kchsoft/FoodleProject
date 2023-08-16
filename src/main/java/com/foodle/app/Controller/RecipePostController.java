package com.foodle.app.Controller;

import com.foodle.app.Domain.PageInfo;
import com.foodle.app.Domain.RecipeLikeDto;
import com.foodle.app.Domain.RecipePostDto;
import com.foodle.app.Service.RecipePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public String postShowPage(@PathVariable int bno , PageInfo pageInfo , Model m){
        RecipePostDto recipe = postService.getOnePost(bno);
        m.addAttribute("recipe", recipe);
        m.addAttribute("pi", pageInfo);
        return "RecipeBoard/recipepost";
    }

    @PostMapping(value = "/recipepost", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String writePost(@RequestBody RecipePostDto recipePostDto, HttpSession session){
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
    public ResponseEntity<String> deletePost(@PathVariable int bno, HttpSession session){
        // get user name
        String userName = (String)session.getAttribute("id");

        // set header
        HttpHeaders header = new HttpHeaders();
        header.set(HttpHeaders.CONTENT_TYPE, "text/plain; charset=utf-8");

        if(postService.isUserEqualWriter(userName, bno)) {
            if(1 == postService.deleteOnePost(bno)) // User == Writer
                return new ResponseEntity<String>("삭제가 완료되었습니다.",header, HttpStatus.OK); // Success 200
            else
                return new ResponseEntity<String>("삭제에 실패했습니다.",header, HttpStatus.INTERNAL_SERVER_ERROR); // Fail 500
        }
        else // User != Writer
            return new ResponseEntity<String>("해당 게시물을 작성한 사람만 삭제할 수 있습니다.",header, HttpStatus.FORBIDDEN); // 403
    }

    @ResponseBody
    @PutMapping("/recipepost/{bno}")
    public ResponseEntity<String> modifyPost(@PathVariable int bno , @RequestBody RecipePostDto post,HttpServletRequest request){
        // get user name
        HttpSession session = request.getSession();
        String userName = (String)session.getAttribute("id");

        // set header
        HttpHeaders header = new HttpHeaders();
        header.set(HttpHeaders.CONTENT_TYPE, "text/plain; charset=utf-8");

        if(postService.isUserEqualWriter(userName, bno)){
            if(1 == postService.modifyOnePost(post)) // User == Writer
                return new ResponseEntity<String>("수정이 완료되었습니다.", header, HttpStatus.OK); // Success 200
            else
                return new ResponseEntity<String>("수정에 실패했습니다.", header, HttpStatus.INTERNAL_SERVER_ERROR); //Fail 500
        }
        else // User != Writer 403
            return new ResponseEntity<String>("해당 게시물을 작성한 사람만 수정할 수 있습니다.", header, HttpStatus.FORBIDDEN);
    }

    @GetMapping(value = "/recipepost/{bno}/like")
    @ResponseBody
    public ResponseEntity<Integer> clickLike(@PathVariable int bno,HttpSession session){
        String user_id = session.getAttribute("id").toString();
        RecipeLikeDto likeDto = new RecipeLikeDto(bno, user_id);

        if (postService.isLikeExist(likeDto)) { // is already like?
            postService.decraseLike(likeDto); // yes -> decrease
        }
        else
            postService.increaseLike(likeDto); // no -> increase

        return new ResponseEntity<>(postService.getPostLikeCount(bno),HttpStatus.OK);
    }


}
