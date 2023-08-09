package com.foodle.app.Controller;

import com.foodle.app.Domain.RecipePostDto;
import com.foodle.app.Service.RecipeBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RecipeBoardController {

    final private RecipeBoardService recipeBoardService;

    @Autowired
    public RecipeBoardController(RecipeBoardService service) {
        this.recipeBoardService = service;
    }

    @GetMapping("/recipeboard")
    public String boardPage(Model m) {
        List<RecipePostDto> recipe = recipeBoardService.getPosts();
        m.addAttribute("recipe",recipe);
//        m.addAttribute(recipe); -> in JSP , name is recipePostDtoList
        return "RecipeBoard/recipeboard";
    }
}
