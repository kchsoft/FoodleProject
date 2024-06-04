package com.foodle.app.Dao;

import com.foodle.app.Domain.UserDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao{
    private SqlSession dbSession;
    private String namespace = "com.foodle.app.Dao.UserMapper.";

    @Autowired
    UserDao(SqlSession dbSession) {
        this.dbSession =dbSession;
    }

    public UserDto selectOneIdPassword(UserDto userDto){
        return dbSession.selectOne(namespace+"selectOneIdPassword", userDto);
    }

    public String selectOneId(String id){
        return dbSession.selectOne(namespace+"selectId", id);
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

    public int insertUser(UserDto user) {
        return dbSession.insert(namespace + "insertUser", user);
    }

    public UserDto selectOneUser(UserDto user) {
        return dbSession.selectOne(namespace + "selectOneUser", user);
    }
}
