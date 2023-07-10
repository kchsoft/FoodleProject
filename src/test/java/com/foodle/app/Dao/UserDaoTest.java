package com.foodle.app.Dao;

import com.foodle.app.Domain.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class UserDaoTest {
    @Autowired UserDao userDao;
    @Test
    public void selectOneIdPassword() {
        userDao.deleteTestUser();

        UserDto user = new UserDto("test1","asdf", "1234");
        assertTrue("user insert fail",userDao.insertUserInfo(user) == 1);

        assertTrue("Select Id Pwd fail",userDao.selectOneIdPassword(user) != null);
    }

    @Test
    public void selectOneId() {
        userDao.deleteTestUser();
        String id = "asdf";
        UserDto user = new UserDto("test1", id, "password");
        assertTrue("Insert User fail",userDao.insertUserInfo(user) == 1);
        assertTrue("Select Id fail",userDao.selectOneId(id) != null);
    }

    @Test
    public void selectOnePassword() {
        userDao.deleteTestUser();
        String password = "1234";
        UserDto user = new UserDto("test1", "id", password);
        assertTrue("Insert User fail",userDao.insertUserInfo(user) == 1);
        assertTrue("Select Password fail",userDao.selectListPassword(password).size() > 0);
    }
}