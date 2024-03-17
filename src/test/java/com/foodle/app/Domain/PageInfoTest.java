package com.foodle.app.Domain;

import com.foodle.app.Dao.RecipePostDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class PageInfoTest {

    @Autowired
    private RecipePostDao postDao;

    @Test
    public void calPageInfo() {
        int currentPage = 1;
        int pageBundle = 10;
        int totalPage = postDao.selectTotalPageCnt();
        PageInfo pi = new PageInfo(totalPage, currentPage, pageBundle);

        assertTrue("firstPage is "+ pi.getFirstPageNavi() ,pi.getFirstPageNavi() == 1);
    }
}