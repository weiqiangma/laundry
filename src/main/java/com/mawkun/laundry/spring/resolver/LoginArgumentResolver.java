package com.mawkun.laundry.spring.resolver;

import cn.pertech.common.auth.AbsAdminSession;
import cn.pertech.common.auth.AbsUserSession;
import cn.pertech.common.constants.Constants;
import cn.pertech.common.exception.SysException;
import cn.pertech.common.spring.annotation.LoginAuth;
import cn.pertech.common.spring.annotation.LoginedAuth;
import com.mawkun.laundry.base.data.UserSession;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;

/**
 * 登陆解析器
 * 功能说明：<br>
 * 模块名称：<br>
 * 功能描述：LoginedArgumentResolver<br>
 * 文件名称: LoginedArgumentResolver.java<br>
 * 系统名称：ICELOVE<br>
 * 软件著作权：ICELOVE 版权所有<br>
 * 开发人员：lujun <br>
 * 开发时间：2016-8-29 下午9:56:54<br>
 * 系统版本：1.0.0<br>
 */
public abstract class LoginArgumentResolver<T extends UserSession> implements HandlerMethodArgumentResolver {
    private Log logger = LogFactory.getLog(getClass());
    private final static String USER_OPEN_ID_KEY    = "USER_OPEN_ID";
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        logger.debug("开始拦截参数:resolveArgument");
        if (!parameter.hasParameterAnnotations()) return false;
        boolean find = false;
        Annotation[] anns = parameter.getParameterAnnotations();
        for (Annotation ann : anns) {
            if (ann instanceof LoginAuth || ann instanceof LoginedAuth) {
                find = true;
                break;
            }
        }
        if (!find) return false;
        Class<?> type = parameter.getParameterType();
        return UserSession.class.isAssignableFrom(type) || UserSession.class.isAssignableFrom(type);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        logger.debug("拦截参数解析:resolveArgument");
        Class<?> type = parameter.getParameterType();
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        if (UserSession.class.isAssignableFrom(type)) {
            UserSession userCache = (UserSession)request.getAttribute(Constants.USER_SESSION_KEY);
            //1、第一步从attribute里面获取对象
            if(userCache!=null){
                return userCache;
            }
            String openId = (String)request.getAttribute(USER_OPEN_ID_KEY);
            //2、第三步从attribute里面获取openId对象
            if(StringUtils.isNotEmpty(openId)){
                userCache = getUserSession(openId);
            }else{
                //3、第二部解析auth编码
                String authorization = request.getHeader("Authorization");
                if(StringUtils.isNotEmpty(authorization)){
                    try {
                        openId = this.authorizationToToken(authorization);
                    }catch (Exception e){
                        logger.error("解析Authorization异常:"+e.getMessage());
                    }
                }
                if(StringUtils.isNotEmpty(openId)){
                    userCache = getUserSession(openId);
                }
            }
            //4、判断当前是否已经登录
            if (userCache==null) {
                if (parameter.hasParameterAnnotation(LoginedAuth.class)) {
                    throw new SysException("请登录");
                }
            }
            request.setAttribute(Constants.USER_SESSION_KEY,userCache);
            return userCache;
        } else if (AbsAdminSession.class.isAssignableFrom(type)) {
            AbsAdminSession adminSession = (AbsAdminSession)request.getAttribute(Constants.ADMIN_SESSION_KEY);
            if (parameter.hasParameterAnnotation(LoginedAuth.class) && adminSession == null) {
                throw new SysException("请登录");
            }
            return adminSession;
        }
        return null;
    }

    /**
     * 获取当前用户session接口
     * @param token 明文token
     * @return
     */
    public abstract T getUserSession(String token);
    /**
     * 把前端传递过来的解密
     * @param authorization token=密文的openId
     * @return
     * @throws Exception
     */
    public abstract String authorizationToToken(String authorization) throws Exception;
}
