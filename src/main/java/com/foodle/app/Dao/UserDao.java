package com.foodle.app.Dao;

import com.foodle.app.Domain.UserDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao{
    @Autowired
    private SqlSession session;
    String namespace = "com.foodle.app.Dao.UserMapper.";


    public UserDto selectOneIdPassword(UserDto userDto){
        return session.selectOne(namespace+"selectOneIdPassword", userDto);
    }

    public String selectOneId(String id){
        return session.selectOne(namespace+"selectOneId", id);
    }

    public List<String> selectListPassword(String password){
        return session.selectList(namespace+"selectListPassword", password);
    }

    public int deleteOneUser(UserDto user){
        return session.delete(namespace + "deleteOneUser",user);
    }

    public void deleteTestUser(){ // delete All name = "test%"
        session.delete(namespace + "deleteTestUser");
    }

    public int insertUserInfo(UserDto user) {
        return session.insert(namespace + "insertUserInfo", user);
    }
}
