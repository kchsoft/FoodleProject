package com.foodle.app.Dao;

import com.foodle.app.Domain.UserDto;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class UserDaoImpleTest {

    @Autowired UserDao userDao;
    @Autowired SqlSession session;
    @Test
    public void selectOneIdPassword() {
        session.delete(userDao.getNamespace()+"deleteAll");

        String id = "asdf";
        String password = "1234";
        UserDto user = userDao.selectOneIdPassword(new UserDto(id, password));
        if( user == null ) {
            System.out.println("Id and Password is Null");
            assertTrue(user == null);
        }
        else{
            System.out.println("User Exist");
            assertTrue(true);
        }
    }

    @Test
    public void selectOneId() {
        String id = "awesdf";
        String userId = userDao.selectOneId(id);
        if( userId == null ) {
            System.out.println("Id is Null");
            assertTrue(userId == null);
        }
        else{
            System.out.println("Id Exist");
            assertTrue(true);
        }
    }

    @Test
    public void selectOnePassword() {
    }
}