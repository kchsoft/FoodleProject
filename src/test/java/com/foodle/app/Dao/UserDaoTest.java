package com.foodle.app.Dao;

import com.foodle.app.Domain.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class UserDaoTest {
    @Autowired UserDao userDao;
    @Test
    public void selectOneIdPassword_Test() {
        userDao.deleteTestUser();

        UserDto user = new UserDto("test1","asdf", "1234");
        assertTrue("user insert fail",userDao.insertUser(user) == 1);

        assertTrue("Select Id Pwd fail",userDao.selectOneIdPassword(user) != null);
    }

    @Test
    public void selectOneId_Test() {
        userDao.deleteTestUser();
        String id = "asdf";
        UserDto user = new UserDto("test1", id, "password");
        assertTrue("Insert User fail",userDao.insertUser(user) == 1);
        assertTrue("Select Id fail",userDao.selectOneId(id) != null);
    }

    @Test
    public void selectOnePassword_Test() {
        userDao.deleteTestUser();
        String password = "1234";
        UserDto user = new UserDto("test1", "id", password);
        assertTrue("Insert User fail",userDao.insertUser(user) == 1);
        assertTrue("Select Password fail",userDao.selectListPassword(password).size() > 0);
    }

    @Test
    public void insertUser_Test() {
        userDao.deleteTestUser();
        String name = "test1";
        String id = "id";
        String password = "password한글";
        String email = "test@foodle.com";
        LocalDate date = LocalDate.now();

        UserDto user = new UserDto(name, id,password,email,date);
        assertTrue("insertUser Fail",userDao.insertUser(user) == 1);

        UserDto dbUser = new UserDto(id, password);
        dbUser = userDao.selectOneUser(dbUser);
        assertTrue("selectOneUser Fail",dbUser != null);

        assertTrue("equals Fail",user.equals(dbUser));
    }
}