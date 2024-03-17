package com.foodle.app.Service;

import com.foodle.app.Dao.RecipePostDao;
import com.foodle.app.Domain.PageInfo;
import com.foodle.app.Domain.RecipePostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeBoardService {
    final private RecipePostDao postDao;

    @Autowired
    public RecipeBoardService(RecipePostDao dao) {
        this.postDao = dao;
    }

    public List<RecipePostDto> getPosts(PageInfo pageInfo) {
        return postDao.selectPosts(pageInfo);
    }

    public int getTotalPageCnt() {
        return postDao.selectTotalPageCnt();
    }
}
