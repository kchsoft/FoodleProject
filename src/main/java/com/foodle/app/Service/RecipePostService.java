package com.foodle.app.Service;

import com.foodle.app.Dao.RecipePostDao;
import com.foodle.app.Domain.RecipePostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipePostService {
    final private RecipePostDao PostDao;

    @Autowired
    public RecipePostService(RecipePostDao dao) {
        this.PostDao = dao;
    }

    public int writePost(RecipePostDto recipePostDto) {
        return PostDao.insertPost(recipePostDto);
    }

}
