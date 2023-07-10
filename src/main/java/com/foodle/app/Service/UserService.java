package com.foodle.app.Service;

import com.foodle.app.Dao.UserDao;
import com.foodle.app.Domain.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    final int VALID_INFO = 2; // ID , PASSWORD is valid.
    final int INVALID_INFO = 1; // ID or PASSWORD is invalid.
    final int NULL_INFO = 0; // There is no ID and PASSWORD.
    @Autowired
    UserDao userDao;

    public int userCheck(UserDto LoginUser) {
//      Login Success - Valid Info
        if (userDao.selectOneIdPassword(LoginUser) != null) return VALID_INFO;

//      Login Fail - 1.Invalid Info or 2.Null info
        String dbId = userDao.selectOneId(LoginUser.getId());
        String dbPassword = userDao.selectOnePassword(LoginUser.getPassword());

//      1. Invalid
        if(dbId != null || dbPassword != null) return INVALID_INFO;

//      2. Null Info
        return NULL_INFO;
    }
}
