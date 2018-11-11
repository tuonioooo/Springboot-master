package com.master.listener;
 
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
 
/**
 * Application Lifecycle Listener implementation class MyServletContextListener
 *
 */
/**
 * 通过  @WebListener 或者 使用代码注册  ServletListenerRegistrationBean
 * @author Administrator
 *
 */
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