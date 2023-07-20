package com.foodle.app.Service;

import com.foodle.app.Dao.RecipePostDao;
import com.foodle.app.Domain.RecipePostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeBoardService {
    final private RecipePostDao PostDao;

    @Autowired
    public RecipeBoardService(RecipePostDao dao){
        this.PostDao = dao;
    }

    public List<RecipePostDto> getPosts(){
        return PostDao.selectAllPost();
    }
}
