package com.foodle.app.Service;

import com.foodle.app.Dao.RecipePostDao;
import com.foodle.app.Domain.PageInfo;
import com.foodle.app.Domain.RecipePostDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class RecipeBoardServiceTest {

    @Autowired
    private RecipePostDao postDao;

    @Test
    public void getPosts() {
        int totalCnt = postDao.selectTotalPageCnt();
        int page = 4;
        int bundle = 10;

        PageInfo pageInfo = new PageInfo(totalCnt, page, bundle);
        List<RecipePostDto> posts = postDao.selectPosts(pageInfo);
        for (RecipePostDto post : posts) {
            System.out.println("post.getBno() = " + post.getBno());
        }
        assertTrue(posts.size() == bundle);
    }
}