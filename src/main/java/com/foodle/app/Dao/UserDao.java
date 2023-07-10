package com.foodle.app.Dao;

import com.foodle.app.Domain.UserDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao{
    @Autowired
    private SqlSession session;
    String namespace = "com.foodle.app.Dao.UserMapper.";

    public UserDto selectOneIdPassword(UserDto userDto){
        return session.selectOne(namespace+"selectIdPassword", userDto);
    }

    public String selectOneId(String id){
        return session.selectOne(namespace+"selectId", id);
    }

    public String selectOnePassword(String password){
        return session.selectOne(namespace+"selectPassword", password);
    }

    public String getNamespace() {
        return namespace;
    }
}
