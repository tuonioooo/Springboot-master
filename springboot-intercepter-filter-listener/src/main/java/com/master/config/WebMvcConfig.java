package com.master.config;


import com.master.filter.HomeFilter;
import com.master.filter.IndexFilter;
import com.master.interceptor.HomeInterceptor;
import com.master.interceptor.IndexInterceptor;
import com.master.listener.MyServletContextListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


/**
 * Created by daizhao.
 * User: tony
 * Date: 2018-11-8
 * Time: 10:38
 * info:
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    private IndexInterceptor indexInterceptor;

    @Autowired
    private HomeInterceptor homeInterceptor;

    /**
     * 添加拦截器
     * @param registry
     */

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(indexInterceptor);
        registry.addInterceptor(homeInterceptor)
                .addPathPatterns("/**").excludePathPatterns("/404");
    }

    /**
     * 注册过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean homeFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new HomeFilter());
        registration.addUrlPatterns("/home/*"); //
        registration.addInitParameter("paramName", "paramValue"); //
        registration.setName("home");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public FilterRegistrationBean indexFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new IndexFilter());
        registration.addUrlPatterns("/index/*"); //
        registration.addInitParameter("paramName", "paramValue"); //
        registration.setName("index");
        registration.setOrder(2);
        return registration;
    }

    /**
     * 注册监听器
     * @return
     */
    @Bean
    public ServletListenerRegistrationBean<MyServletContextListener> serssionListenerBean(){
        ServletListenerRegistrationBean<MyServletContextListener>
                servletListenerRegistrationBean = new ServletListenerRegistrationBean<MyServletContextListener>(new MyServletContextListener());
        return servletListenerRegistrationBean;
    }

}
