package com.example.tasela.service.impl;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.tasela.model.entity.User;
import com.example.tasela.model.entity.UserExample;
import com.example.tasela.dao.mapper.UserMapper;
import com.example.tasela.model.modelVO.UserLogin;
import com.example.tasela.service.UserService;
import com.example.tasela.utils.*;
import com.example.tasela.utils.redis.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 */
@Service
public class UserServiceImpl implements UserService {

    private Logger logger= LoggerFactory.getLogger(getClass());

    @Resource
    private UserMapper userMapper;
    @Autowired
    private RedisUtils redisUtils;

    private static final long EXPIRE_TIME_IN_MINUTES = 30;

    private static final Long REFRESHSECND=60*60*30*1000L;


    @Override
    public Map<String,Object> getUserList(User user) {
        Map<String,Object> json=new HashMap<>();
        UserExample userExample=new UserExample();
        userExample.createCriteria();
        List<User> users=userMapper.selectByExample(userExample);
        json.put("success",true);
        json.put("data",users);
        json.put("msg","查询成功");
        return json;
    }

    /**
     * 查询用户code
     * @param userLogin
     * @return
     */
    @Override
    public User findUserById(UserLogin userLogin) {
        UserExample userExample=new UserExample();
        userExample.createCriteria().andUserCodeEqualTo(userLogin.getUserCode());
       return userMapper.selectByExample(userExample).get(0);

    }

    /**
     * 用户注册接口
     * @param userLogin
     * @return
     * @throws NoSuchAlgorithmException
     */
    @Override
    public Map<String, Object> insertUser(UserLogin userLogin) throws NoSuchAlgorithmException {
        Map<String,Object> json=new HashMap<>();
        User user=new User();
        if(userLogin!=null){
            //验证用户名是否已经注册
            UserExample userExample=new UserExample();
            userExample.createCriteria().andUserNameEqualTo(userLogin.getUserName().trim());
            user.setUserName(userLogin.getUserName());
            List<User> users=userMapper.selectByExample(userExample);
            if(users.size() > 0){
                json.put("success",true);
                json.put("msg", MessageInfo.USERNAME_INTURE.getMessage());
                return json;
            }
            //生产用户唯一标识
            user.setUserCode(userLogin.getUserName()+ ReaManberCode.getCharCode()+ DateUtis.GETTIME().trim());
            user.setUserLoginName(userLogin.getUserLoginName());
            //MD5加密
            user.setUserPass(MD5utils.RequestEncryptionMD5(userLogin.getUserPass().trim()));
            userMapper.insertSelective(user);
            json.put("success",true);
            json.put("msg", MessageInfo.REGISTER_TRUE.getMessage());
            return json;
        }
        json.put("success",true);
        json.put("msg", MessageInfo.SERVER_ERR.getMessage());
        return json;
    }

    /**
     * 用户登录
     * @param userLogin
     * @return
     */
    @Override
    public Map<String, Object> userLogin(UserLogin userLogin) throws Exception {
        Map<String,Object> json=new HashMap<>();
        if(userLogin == null){
            json.put("success",false);
            json.put("msg",MessageInfo.PARAMER_NULL.getMessage());
            return json;
        }
        UserExample userExample=new UserExample();
        userExample.createCriteria().andUserNameEqualTo(userLogin.getUserName()).andUserPassEqualTo(MD5utils.RequestEncryptionMD5(userLogin.getUserPass().trim()));
        User users=userMapper.selectByExample(userExample).get(0);
        if(users == null){
            json.put("success",false);
            json.put("msg",MessageInfo.USERNAME_PASS_ERROR.getMessage());
            return json;
        }
        UserLogin login=new UserLogin();
        login.setUserPass(users.getUserPass());
        login.setUserCode(users.getUserCode());
        login.setUserLoginName(users.getUserLoginName());
        login.setUserName(users.getUserName());
        //login.setToken(ReaManberCode.randomString(16));
        try{
            /*redisUtils.set("token", ReaManberCode.randomString(16));
            Long currentTime = System.currentTimeMillis();
            redisUtils.set(login.getToken(),login.getUserCode());
            redisUtils.set(login.getToken() + login.getUserCode(), currentTime.toString());*/
            String token="";
            token= JWT.create().withAudience(login.getUserCode())
                    .sign(Algorithm.HMAC256(login.getUserPass()));
            login.setToken(token);
            json.put("data",login);
            json.put("success",true);
            json.put("msg",MessageInfo.LOGIN_TRUE.getMessage());
        }catch (Exception e){
            json.put("success",false);
            json.put("msg",MessageInfo.SERVER_ERR.getMessage());
            logger.error("错误信息:"+e.getMessage());
        }
        return json;
    }
}
