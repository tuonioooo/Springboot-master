## Spring Boot中使用JavaMailSender发送各类邮件

### 快速入门

添加依赖：
```
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-mail</artifactId>
</dependency>
```

application.properties中加入如下配置（用户名就是qq邮箱名、密码是POP3/SMTP服务授权码）

```
spring.mail.host=smtp.qq.com
spring.mail.username=用户名
spring.mail.password=密码
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true
```

注意：QQ邮箱要开启POP3/SMTP服务

具体示例，参考代码：SpringbootMailApplicationTests