package com.foodle.app.Service;

import com.foodle.app.Dao.UserDao;
import com.foodle.app.Domain.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    final int VALID_INFO = 2; // ID , PASSWORD is valid.
    final int INVALID_INFO = 1; // ID or PASSWORD is invalid.
    final int NULL_INFO = 0; // There is no ID and PASSWORD.
    final UserDao userDao;

    @Autowired
    UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public int isValidLoginInfo(UserDto LoginUser) {
//      Login Success - Valid Info
        if (userDao.selectOneIdPassword(LoginUser) != null) return VALID_INFO;

//      Login Fail - 1.Invalid Info or 2.Null info
        String dbId = userDao.selectOneId(LoginUser.getId());
        List<String> dbPassword = userDao.selectListPassword(LoginUser.getPassword());

//      1. Invalid
        if(dbId != null || dbPassword.size() > 0) return INVALID_INFO;

//      2. Null Info
        return NULL_INFO;
    }

    public boolean isIdExist(String id){
        String dbId = userDao.selectOneId(id);
        if(dbId == null) return false;
        return true;
    }
}