# Spring Boot 配置Filter

**一.配置Filter两种实现方式**

- **@WebFilter实现配置**

```
@Component
@WebFilter(urlPatterns = "/home/*", filterName = "home")
public class HomeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("HomeFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("HomeFilter doFilter");
    }

    @Override
    public void destroy() {
        System.out.println("HomeFilter destory");
    }
}
```

```
@Component
@WebFilter(urlPatterns = "/index", filterName = "index")
public class IndexFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("IndexFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("IndexFilter doFilter");
    }

    @Override
    public void destroy() {
        System.out.println("IndexFilter destroy");
    }
}
```



> 分析：
>
> 1.添加注解@Component标注为Spring容器可识别类，@WebFilter注解表明该类是一个过滤器
>
> 过滤器属性，urlPatterns指定过滤的路径，filterName指定名称

- 通过@Bean方式注册filter

```
/**
     * 添加过滤器
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
```

> 分析：
>
> 1.将该@Bean方法配置在@Configuration或@SpringBootApplication下，在HomeFilter和IndexFilter类中，不需要任何注解，通过@Bean方式注册到SpringIOC容器中
>
> 2.对于多个Filter加载，可以通过registration.setOrder(2)指定加载顺序，值越小，越靠前，@Order指定顺序只是针对于AOP切面拦截起作用，@Bean不起作用