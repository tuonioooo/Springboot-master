# SpringBoot 自定义拦截器Interceptor使用

**一.继承HandleInterceptor**

```
@Configuration
public class IndexInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        System.out.println("自定义拦截器 = preHandle");
//        response.setContentType("text/json");
//        response.setCharacterEncoding("UTF-8");
//        PrintWriter writer = response.getWriter();
//        writer.print("拦截成功!!!");
//        writer.flush();
//        writer.close();

//        response.sendRedirect("/404");
          return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{
        System.out.println("自定义拦截器 = postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("自定义拦截器 = afterCompletion");
    }
}
```

> 注意：
>
> 1.IndexInterceptor拦截器内部方法执行的顺序：preHandle——>postHandle——>afterCompletion
>
> 2.顺序调用的前提是preHandle返回的结果是true，如果是false，后面的方法和HandlerMapping都不会访问

**二.继承WebMvcConfigurationSupport添加对WebMvc自定义扩展，扩展自定义拦截器方法addInterceptors(InterceptorRegistry registry)**

```
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    private IndexInterceptor indexInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(indexInterceptor).addPathPatterns("/**").excludePathPatterns("/404");

    }

}
```

> note：
>
> 1.SpringBoot 2.0 中WebMvc自定义扩展，WebMvcConfigurerAdapter已经过时，现在可以通过两种方式实现对WebMvc扩展：
>
> - public class WebMvcConfig extends WebMvcConfigurationSupport
>
> - public class WebMvcConfig implements WebMvcConfigurer
>
> 2.addPathPatterns添加拦截路径，excludePathPatterns排除拦截路径
>
> 3.如果想全部接管SpringMVC，需要配置类加上@EnableWebMvc

**三.添加多个拦截器以及多个拦截器执行的顺序控制**

在上面代码基础上加入如下代码：

```
package com.master.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by daizhao.
 * User: tony
 * Date: 2018-11-8
 * Time: 10:29
 * info:
 */
@Configuration
@Order(2)
public class HomeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        System.out.println("HomeInterceptor自定义拦截器 = preHandle");

//        response.setContentType("text/json");
//        response.setCharacterEncoding("UTF-8");
//        PrintWriter writer = response.getWriter();
//        writer.print("拦截成功!!!");
//        writer.flush();
//        writer.close();

//        response.sendRedirect("/404");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{
        System.out.println("HomeInterceptor自定义拦截器 = postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("HomeInterceptor自定义拦截器 = afterCompletion");
    }
}

```

```
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    private IndexInterceptor indexInterceptor;

    @Autowired
    private HomeInterceptor homeInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(indexInterceptor);
        registry.addInterceptor(homeInterceptor)
                .addPathPatterns("/**").excludePathPatterns("/404");

    }
}
```

通过@Order注解来控制执行顺序，数字越小，越靠前执行

> @Order只是针对于AOP切面拦截才有效

**四.HandleInterceptor源码剖析**

查看DispatcherServlet中的doDispatch方法

Springboot 2.X的doDispatch方法

```
protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpServletRequest processedRequest = request;
        HandlerExecutionChain mappedHandler = null;
        boolean multipartRequestParsed = false;
        WebAsyncManager asyncManager = WebAsyncUtils.getAsyncManager(request);

        try {
            ModelAndView mv = null;
            Object dispatchException = null;

            try {
                processedRequest = this.checkMultipart(request);
                multipartRequestParsed = processedRequest != request;
                mappedHandler = this.getHandler(processedRequest);
                if (mappedHandler == null) {
                    this.noHandlerFound(processedRequest, response);
                    return;
                }

                HandlerAdapter ha = this.getHandlerAdapter(mappedHandler.getHandler());
                String method = request.getMethod();
                boolean isGet = "GET".equals(method);
                if (isGet || "HEAD".equals(method)) {
                    long lastModified = ha.getLastModified(request, mappedHandler.getHandler());
                    if ((new ServletWebRequest(request, response)).checkNotModified(lastModified) && isGet) {
                        return;
                    }
                }

                if (!mappedHandler.applyPreHandle(processedRequest, response)) {
                    return;
                }

                mv = ha.handle(processedRequest, response, mappedHandler.getHandler());
                if (asyncManager.isConcurrentHandlingStarted()) {
                    return;
                }

                this.applyDefaultViewName(processedRequest, mv);
                mappedHandler.applyPostHandle(processedRequest, response, mv);
            } catch (Exception var20) {
                dispatchException = var20;
            } catch (Throwable var21) {
                dispatchException = new NestedServletException("Handler dispatch failed", var21);
            }

            this.processDispatchResult(processedRequest, response, mappedHandler, mv, (Exception)dispatchException);
        } catch (Exception var22) {
            this.triggerAfterCompletion(processedRequest, response, mappedHandler, var22);
        } catch (Throwable var23) {
            this.triggerAfterCompletion(processedRequest, response, mappedHandler, new NestedServletException("Handler processing failed", var23));
        } finally {
            if (asyncManager.isConcurrentHandlingStarted()) {
                if (mappedHandler != null) {
                    mappedHandler.applyAfterConcurrentHandlingStarted(processedRequest, response);
                }
            } else if (multipartRequestParsed) {
                this.cleanupMultipart(processedRequest);
            }

        }

    }
```

从上面代码可以看出HandlerInterceptor执行入口

```
if (!mappedHandler.applyPreHandle(processedRequest, response)) {
     return;
}
```

```
boolean applyPreHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HandlerInterceptor[] interceptors = this.getInterceptors();
        if (!ObjectUtils.isEmpty(interceptors)) {
            for(int i = 0; i < interceptors.length; this.interceptorIndex = i++) {
                HandlerInterceptor interceptor = interceptors[i];
                if (!interceptor.preHandle(request, response, this.handler)) {
                    this.triggerAfterCompletion(request, response, (Exception)null);
                    return false;
                }
            }
        }

        return true;
    }
```

通过这个执行入口，可以获取拦截器，执行代码

SpringBoot 2.X之前的参考https://blog.csdn.net/lhw0621/article/details/71106540