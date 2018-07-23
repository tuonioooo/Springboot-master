## Spring Boot 2.0.3中使用logback整合AOP，输出日志到mongodb和控制台、自定义appender输出mongodb

**添加依赖**
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

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-core</artifactId>
    <version>1.2.3</version>
</dependency>
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>1.2.3</version>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.16.12</version>
    <scope>provided</scope>
</dependency>
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>fastjson</artifactId>
    <version>1.2.38</version>
</dependency>
```

**配置application.properties**
```
# 配置mongodb 默认的是mongodb://localhost:27017
spring.data.mongodb.uri=mongodb://192.168.111.103:27017/logtest
```

**配置logback.xml**

```
<?xml version="1.0" encoding="utf-8"?>

<configuration status="WARN" monitorInterval="30">

    <property name="instance" value="./logs"/>

    <!-- 控制台输出 -->
    <appender name="stdout" class="ch.qos.logback.core.ConsoleAppender">
        <encoder charset="UTF-8"> <!-- encoder 可以指定字符集，对于中文输出有意义 -->
            <!--格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度%msg：日志消息，%n是换行符-->
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %msg[%logger]%n</pattern>
        </encoder>
    </appender>

    <!-- 日志输出到nosql中-->
    <appender name="mongoAppender" class="com.master.log.MongoAppender">
    </appender>

    <!--日志名称-->

    <Logger name="mongoAppender" level="debug"/> <!-- 默认 additivity="true"  向root传递  -->
    <logger name="com.master" level="debug" /> <!-- 默认 additivity="true"  向root传递  -->
    <root level="info">
        <appender-ref ref="mongoAppender"/>
        <appender-ref ref="stdout"/>
    </root>

</configuration>
```

**测试类**

```
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMongodbLogbackApplicationTests {

	//两种方式都会输出到mongodb中
	static Logger slf4j1 = LoggerFactory.getLogger(SpringbootMongodbLogbackApplicationTests.class);
	static Logger slf4j2 = LoggerFactory.getLogger("mongoAppender");

	@Test
	public void testSlf4j() {
		slf4j1.info("slf4j1输入mongodb测试");
		slf4j2.info("slf4j2输入mongodb测试");

	}

}
```

**有关aop测试输出日志到mongodb中，可以参考代码**
