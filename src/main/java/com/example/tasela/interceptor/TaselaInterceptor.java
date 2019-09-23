package com.example.tasela.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.tasela.model.entity.User;
import com.example.tasela.model.modelVO.UserLogin;
import com.example.tasela.service.UserService;
import com.example.tasela.tokenLogin.PassTokenLogin;
import com.example.tasela.tokenLogin.TokenLogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author jinmos
 * @date 2019-09-16 14:55
 * 拦截器
 */
public class TaselaInterceptor extends HandlerInterceptorAdapter{

     Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;
    @Resource
    private JedisPool jedisPool;
    //存放登录用户模型Key的Request Key
    public static final String REQUEST_CURRENT_KEY = "REQUEST_CURRENT_KEY";

    public static final Integer CURRENT_TIME=1800;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        // 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)handler;
        Method method=handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassTokenLogin.class)) {
            PassTokenLogin passToken = method.getAnnotation(PassTokenLogin.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(TokenLogin.class)) {
            TokenLogin userLoginToken = method.getAnnotation(TokenLogin.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new RuntimeException("无token，请重新登录");
                }
                // 获取 token 中的 user id
                String userCode;
                try {
                    userCode = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException j) {
                    throw new RuntimeException("401");
                }
                UserLogin userLogin=new UserLogin();
                userLogin.setUserCode(userCode);
                User user = userService.findUserById(userLogin);
                if (user == null) {
                    throw new RuntimeException("用户不存在，请重新登录");
                }
                // 验证 token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getUserPass())).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    throw new RuntimeException("401");
                }
                return true;
            }
        }else {
                // 执行认证
                if (token == null) {
                    throw new RuntimeException("无token，请重新登录");
                }
                // 获取 token 中的 user id
                String userCode;
                try {
                    userCode = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException j) {
                    throw new RuntimeException("401");
                }
                UserLogin userLogin=new UserLogin();
                userLogin.setUserCode(userCode);
                User user = userService.findUserById(userLogin);
                if (user == null) {
                    throw new RuntimeException("用户不存在，请重新登录");
                }
                // 验证 token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getUserPass())).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    throw new RuntimeException("401");
                }
                return true;
        }
        return true;



        /*//token验证
        String token=request.getHeader("token");
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)handler;
        Method method=handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassTokenLogin.class)) {
            PassTokenLogin passtokenLogin = method.getAnnotation(PassTokenLogin.class);
            if (passtokenLogin.required()) {
                return true;
            }
        }
        // 如果打上了TokenLogin注解则需要验证token
        if (method.getAnnotation(TokenLogin.class) != null || handlerMethod.getBeanType().getAnnotation(TokenLogin.class) != null) {
            logger.info("Get token from request is {} ", token);
            String userCode = "";
            Jedis jedis=jedisPool.getResource();
            if (token != null && token.length() != 0) {
                userCode = jedis.get(token);
                logger.info("Get username from Redis is {}", userCode);
            }
            if (userCode != null && !userCode.trim().equals("")) {
                Long tokeBirthTime = Long.valueOf(jedis.get(token + userCode));
                logger.info("token Birth time is: {}", tokeBirthTime);
                Long diff = System.currentTimeMillis() - tokeBirthTime;
                logger.info("token is exist : {} ms", diff);
                if (diff >CURRENT_TIME) {
                    jedis.expire("token",CURRENT_TIME);
                    jedis.expire(token,CURRENT_TIME);
                    logger.info("Reset expire time success!");
                    Long newBirthTime = System.currentTimeMillis();
                    jedis.set(token + userCode, newBirthTime.toString());
                }

                //用完关闭
                jedis.close();
                request.setAttribute(REQUEST_CURRENT_KEY, userCode);
                return true;
            } else {
                JSONObject jsonObject = new JSONObject();
                PrintWriter out = null;
                try {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    jsonObject.put("code", ((HttpServletResponse) response).getStatus());
                    jsonObject.put("message", HttpStatus.UNAUTHORIZED);
                    out = response.getWriter();
                    out.println(jsonObject);
                    return false;
                } catch (Exception e) {
                    logger.error("错误信息:"+e.getMessage());
                    e.printStackTrace();
                } finally {
                    if (null != out) {
                        out.flush();
                        out.close();
                    }
                }
            }
        }
        request.setAttribute(REQUEST_CURRENT_KEY, null);
        return true;*/
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    }



}
