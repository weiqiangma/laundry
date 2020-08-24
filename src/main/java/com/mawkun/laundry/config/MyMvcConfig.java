package com.mawkun.laundry.config;

import com.mawkun.laundry.base.data.UserSession;
import com.mawkun.laundry.base.service.UserCacheService;
import com.mawkun.laundry.spring.interpectors.UserLoginInterceptor;
import com.mawkun.laundry.spring.resolver.LoginArgumentResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Slf4j
@Configuration
public class MyMvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    private UserCacheService userCacheService;

    /**
     * 注入获取登录解析
     *
     * @return LoginArgumentResolver
     */
    public LoginArgumentResolver createLoginArgumentResolver() {
        return new LoginArgumentResolver<UserSession>() {
            @Override
            public UserSession getUserSession(String token) {
                return userCacheService.getUserSession(token);
            }

            @Override
            public String authorizationToToken(String authorization) throws Exception {
                return authorization;
            }
        };
    }

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(createLoginArgumentResolver());
        super.addArgumentResolvers(argumentResolvers);
    }


    //解决跨域问题
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        log.info("解决跨域问题>>>>");
        registry.addMapping("/**").allowedOrigins("*").allowCredentials(true).allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE").maxAge(3600);
    }

    //创建登录拦截器并注入userCacheService
    private UserLoginInterceptor createLoginInterceptor() {
        return new UserLoginInterceptor(userCacheService);
    }

    //添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(createLoginInterceptor()).addPathPatterns("/adm/**");
        super.addInterceptors(registry);
    }
}
