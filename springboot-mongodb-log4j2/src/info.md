## log4j之LogManager

LogManager,获取Logger实例或者操作当前的LoggerRepository。

LogManager类的初始化过程在加载到内存前已经完成。

在使用log4j时， 我们一开始会获取Logger:

```
org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(this.getClass());
```

Logger.getLogger 源代码如下：

```
static public Logger getLogger(Class clazz) {
  return LogManager.getLogger(clazz.getName());
}
```

可见我们平常使用的方式，与源代码端调用的方式，是相同的

## Spring Boot 2.0.3中使用log4j2 输出日志到mongodb和控制台

**1.排除依赖原有spring boot logging的依赖**

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
```

**2.添加log4j2依赖：**

```
<!-- log4j-nosql -->
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-nosql</artifactId> <!-- 必需，否则报错 -->
    <version>2.7</version>
</dependency>
<!-- log4j2-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-log4j2</artifactId>
</dependency>
```

**3.mongodb-driver依赖：**

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-mongodb</artifactId><!-- 必需，主要是，加载内部的 mongodb-driver-->
</dependency>

```

