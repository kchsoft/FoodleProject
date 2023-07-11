package com.foodle.app.Dao;

import com.foodle.app.Domain.UserDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao{
    @Autowired
    private SqlSession dbSession;
    String namespace = "com.foodle.app.Dao.UserMapper.";


    public UserDto selectOneIdPassword(UserDto userDto){
        return dbSession.selectOne(namespace+"selectOneIdPassword", userDto);
    }

    public String selectOneId(String id){
        return dbSession.selectOne(namespace+"selectOneId", id);
    }

    public List<String> selectListPassword(String password){
        return dbSession.selectList(namespace+"selectListPassword", password);
    }

    public int deleteOneUser(UserDto user){
        return dbSession.delete(namespace + "deleteOneUser",user);
    }

    public void deleteTestUser(){ // delete All name = "test%"
        dbSession.delete(namespace + "deleteTestUser");
    }

    public int insertUserInfo(UserDto user) {
        return dbSession.insert(namespace + "insertUserInfo", user);
    }
}
