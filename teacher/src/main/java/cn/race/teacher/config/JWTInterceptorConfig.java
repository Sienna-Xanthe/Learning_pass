package cn.race.teacher.config;



import cn.race.teacher.interceptor.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * jwt拦截器配置：那些方法放行或者不放行
 */
@Configuration
public class JWTInterceptorConfig implements WebMvcConfigurer {
@Override
    public void addInterceptors(InterceptorRegistry registry){
    registry.addInterceptor(new JWTInterceptor())
            .addPathPatterns("/teacher/**")
            .excludePathPatterns("/teacher/login");
}
}
