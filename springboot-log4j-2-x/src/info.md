###log4j高版本使用注意事项

最近测试在spring boot项目中引入log4j日志工具，碰到了一系列的问题，费了好大的劲，终于解决完成，现做个记录，供大家参考，有错误的地方，还望指出：

问题1： 
1.3.8以后的spring boot版本对log4j不支持，无法通过maven加载相应的jar包，需要改为log4j2；

```
<dependency>
    <groupId>org.springframework.boot</groupId>  
    <artifactId>spring-boot-starterlog4j2</artifactId>   
</dependency>
```

问题2： 
log4j2与logbackjar包冲突，无法正常使用日志

需要在下面的dependence中加入去除语句

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-logging</artifactId>
        </exclusion>
    </exclusions>
</dependency>
或者
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-logging</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

问题3：
配置文件的错误

（1）log4j2的配置文件不能再是 .properties 类型的文件，Log4j2提供了三种ConfiguarationFactory的实现：JSON、YAML、XML。
   
    ```
        如果强制使用.properties文件，将无法使用Spring环境中变量设置功能，如：设置多环境下的日志级别功能
    ```
（2）文件的位置要与设置的保持匹配

