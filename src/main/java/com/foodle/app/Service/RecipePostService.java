package com.foodle.app.Service;

import com.foodle.app.Dao.RecipePostDao;
import com.foodle.app.Domain.RecipePostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipePostService {
    final private RecipePostDao postDao;

    @Autowired
    public RecipePostService(RecipePostDao dao) {
        this.postDao = dao;
    }

    public int writePost(RecipePostDto recipePostDto) {
        return postDao.insertPost(recipePostDto);
    }

    public RecipePostDto getOnePost(int bno) {
        return postDao.selectOnePost(bno);
    }

    public boolean isUserEqualWriter(String userName, int bno){
        RecipePostDto post = postDao.selectOnePost(bno);
        return userName.equals(post.getWriter());
    }

    public int deleteOnePost(int bno) {
        return postDao.deleteOnePost(bno);
    }
}
