## Spring Boot 2.0.3中使用org.apache.logging.log4j.Logger 输出日志到mongodb和控制台

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

**2.添加logging依赖：**

```
<!-- org.apache.logging.log4j 依赖版本 start-->
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-api</artifactId>
    <version>2.7</version>
</dependency>
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-core</artifactId>
    <version>2.7</version>
</dependency>
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-nosql</artifactId> <!-- 必需，否则报错 -->
    <version>2.7</version>
</dependency>
<!-- org.apache.logging.log4j 依赖版本 end-->

```

**3.mongodb-driver依赖：**

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-mongodb</artifactId><!-- 必需，主要是，加载内部的 mongodb-driver-->
</dependency>

```

4.如果想用slf4j实现日志框架，在上面基础上添加如下依赖：

```
<!--slf4j start -->
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>1.7.25</version>
</dependency>
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-slf4j-impl</artifactId>
    <version>2.7</version>
</dependency>
<!--slf4j end -->
```
