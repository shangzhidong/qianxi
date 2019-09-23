package com.example.tasela.service;

import com.example.tasela.model.entity.User;
import com.example.tasela.model.modelVO.UserLogin;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @author  shangzhidong
 */
public interface UserService {

    Map<String,Object> getUserList(User user);

    User findUserById(UserLogin userLogin);

    Map<String, Object> insertUser(UserLogin userLogin) throws NoSuchAlgorithmException;

    Map<String, Object> userLogin(UserLogin userLogin) throws Exception;
}
