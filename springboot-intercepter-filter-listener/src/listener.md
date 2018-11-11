# Spring Boot 配置 Listener

**一.配置Listener两种实现方式**

- **@WebListener注解配置方式**

```
@Component
@WebListener
public class MyServletContextListener implements ServletContextListener {
 
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("MyServletContextListener ==================init");
	}
 
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("MyServletContextListener ==================destroy");
		
	}
}
```

联合@Component将MyServletContextListener注册到SpringIOC容器中

- **通过@Bean注入方式配置**

```
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
public class WebMvcConfig{

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

```

