package com.foodle.app.Service;

import com.foodle.app.Dao.UserDao;
import com.foodle.app.Domain.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class UserServiceTest {

    @Autowired
    UserDao userDao;
    @Test
    public void isValidUserInfo() {
        userDao.deleteTestUser();
        String id = "test1";
        String password = "1234";
        UserDto user = new UserDto("test1",id, password);
        assertTrue("user insert fail",userDao.insertUserInfo(user) == 1);

        String id2 = "test2";
        String password2 = "1234";
        UserDto user2 = new UserDto("test2",id2, password2);
        assertTrue("user2 insert fail",userDao.insertUserInfo(user2) == 1);


        assertTrue("VALID_INFO Test fail",userDao.selectOneIdPassword(user) != null);

        assertTrue("INVALID_INFO ID Test fail",
                (userDao.selectOneId("test99") != null || userDao.selectListPassword(password).size() > 0));

        assertTrue("INVALID_INFO PWD Test fail",
                (userDao.selectOneId(id) != null || userDao.selectListPassword("5678").size() == 0));

        assertTrue("NULL_INFO Test fail",
                (userDao.selectOneId("test99") == null && userDao.selectListPassword("5678").size() == 0));

    }
}