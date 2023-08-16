package com.foodle.app.Controller;

import com.foodle.app.Domain.PageInfo;
import com.foodle.app.Domain.RecipePostDto;
import com.foodle.app.Service.RecipeBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RecipeBoardController {

    final private RecipeBoardService boardService;

    @Autowired
    public RecipeBoardController(RecipeBoardService service) {
        this.boardService = service;
    }

    @GetMapping("/recipeboard")
    public String boardPage(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageBundle, Model m) {
        int totalPageCnt = boardService.getTotalPageCnt();
        PageInfo pageInfo = new PageInfo(totalPageCnt, page, pageBundle);
        List<RecipePostDto> recipe = boardService.getPosts(pageInfo);
        m.addAttribute("recipe", recipe);
        m.addAttribute("pi", pageInfo);

//        m.addAttribute(recipe); -> in JSP , name is recipePostDtoList
        return "RecipeBoard/recipeboard";
    }
}
