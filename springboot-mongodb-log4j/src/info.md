## 实现自定义log4j步骤

1、继承log4j公共的基类：AppenderSkeleton  
2、打印日志核心方法：abstract protected void append(LoggingEvent event);  
3、初始化加载资源：public void activateOptions()，默认实现为空  
4、释放资源：public void close()  
5、是否需要按格式输出文本：public boolean requiresLayout()  
正常情况下我们只需要覆盖append方法即可。然后就可以在log4j中使用了  