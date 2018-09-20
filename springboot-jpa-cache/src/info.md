## springboot data jpa 缓存配置

### 前言
默认JPA缓存使用一级缓存，当前Session，当前会话退出，缓存消失通Mybatis一样，二级缓存需要单独配置  
具体参考 https://blog.csdn.net/J080624/article/details/78776800

### SpringBoot+SpringDataJPA使用二级缓存
**1.添加依赖**
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-cache</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```
**2.SpringBoot启动类上面一定要加@EnableCaching注解**
```
@SpringBootApplication
@EnableCaching
public class SpringbootJpaCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaCacheApplication.class, args);
	}
}
```
**3.在使用缓存的接口类或者方法上面加上相关的注解**

* @CacheConfig
@CacheConfig(cacheNames = "users")该注解是可以将缓存分类，它是类级别的注解方式。 
@CacheConfig(cacheNames = "xxx")统一声明

* @Cacheable
主要针对方法配置，能够根据方法的请求参数对其结果进行缓存 
1.如果key不存在，执行方法体，并将结果更新到缓存中。 
2.如果key存在，直接查询缓存中的数据。

>@Cacheable可以与@CacheConfig结合使用，也可以单独使用，情况如下：
1.结合使用时，可以指定自己的缓存名称，也可以不用指定，不指定缓存名称，统一用@CacheConfig定义的
2.单独使用时，必须指定自己的缓存名称，否则会报错找不到指定的缓存CacheManage

**4.@CacheConfig和@Cacheable配置示例**

* 结合使用
```
@CacheConfig(cacheNames = "users")
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Cacheable
    public User getUser(Long id){
        return userRepository.findById(id).get();
    }
}
//======================测试========================
@Test
public void contextLoads() {
    User user = userService.getUser(1l);
    System.out.println("user.getName() = " + user.getName());
    User user2 = userService.getUser(1l);
    System.out.println("user2.getName() = " + user2.getName());
}
可以看到控制台，仅打印一条Sql语句，证明缓存配置成功
```
* 单独使用

```
public interface UserRepository extends JpaRepository<User, Long> {
    @Cacheable(value="user")
    User findByName(String name);
}

//======================测试========================

@Test
public void configCache(){
    User user = userRepository.findByName("Kobe");
    System.out.println("user.getName() = " + user.getName());
    User user2 = userRepository.findByName("Kobe");
    System.out.println("user2.getName() = " + user2.getName());
}
同样可以看到控制台，仅打印一条Sql语句，证明缓存配置成功

```

参考 https://blog.csdn.net/pyycsd/article/details/80969427 介绍了CachingConfigurationSelector使用流程



 