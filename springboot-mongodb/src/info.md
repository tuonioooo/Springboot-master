## spring boot mongodb集成介绍

### moggodb教程网址：[This link](http://www.runoob.com/mongodb/mongodb-tutorial.html) 

**在spring boot 入口类添加 @EnableMongoPlus注解，启动对mongongdb的支持，使用spring boot 自动配置功能：**

```
@SpringBootApplication
@EnableMongoPlus
public class SpringbootMongodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMongodbApplication.class, args);
	}
}
```

**配置application.properties，带密码的配置**

```
spring.data.mongodb.uri=mongodb://name:pass@localhost:27017/test

```

**配置application.properties，不带密码的配置**

```
spring.data.mongodb.uri=mongodb://192.168.127.131:27017/runoob
```

**配置application.properties，多个IP集群配置**

```
spring.data.mongodb.uri=mongodb://user:pwd@ip1:port1,ip2:port2/database

```

**创建实体类**

```
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private Long id;

    private String username;
    private Integer age;
}

```

**创建实体类的删除操作**

```
/**
 * @author tuonioooo
 * @version 1.0.0
 * @date 16/4/27 下午10:16.
 * @blog https://blog.csdn.net/tuoni123
 */
public interface UserRepository extends MongoRepository<User, Long> {

    User findByUsername(String username);

}
```

**测试类**

```
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringbootMongodbApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Before
	public void setUp() {
		userRepository.deleteAll();
	}

	@Test
	public void testUser() throws Exception {

		// 创建三个User，并验证User总数
		userRepository.save(new User(1L, "didi", 30));
		userRepository.save(new User(2L, "mama", 40));
		userRepository.save(new User(3L, "kaka", 50));
		Assert.assertEquals(3, userRepository.findAll().size());

		// 删除一个User，再验证User总数
		Optional<User> user = userRepository.findById(1L);
		User u = user.get();
		userRepository.delete(u);
		Assert.assertEquals(2, userRepository.findAll().size());

		// 删除一个User，再验证User总数
		u = userRepository.findByUsername("mama");
		userRepository.delete(u);
		Assert.assertEquals(1, userRepository.findAll().size());

	}

```

**mongodb的数据持久化的另一个方法是：MongoTemplate mongoTemplate; 详细的列子看代码**

**mongodb开启用户认证流程**

```
1.修改配置文件 /usr/local/mongodb-linux-x86_64-rhel62-3.6.5/etc/mongodb.conf 打开auth的注释，设置auth=true
2.kill进程，重新启动 ./mongod --config /usr/local/mongodb-linux-x86_64-rhel62-3.6.5/etc/mongodb.conf
3.添加管理员：
   
使用命令mongo进入命令行 ，创建第一个用户，该用户需要有用户管理权限，这里设置其角色为root

> use admin
switched to db admin

> db.createUser({user:"root",pwd:"root",roles:["root"]})
Successfully added user: { "user" : "root", "roles" : [ "root" ] }

新增的用户在system.users中，执行命令前，需要先认证

> db.auth("root", "root")
1
> db.getCollectionNames()

[ "system.users", "system.version" ]

4.添加数据库用户:

第一个用户添加完成后，便需要认证才能继续添加其他用户:

db.auth("root", "root")

> use runoob
switched to db runoob

> db.createUser(
   {
    user: "runoob",
    pwd:  "runoob",
    roles: [{ role: "dbOwner", db: "runoob" }]
   }
)
Successfully added user: {
        "user" : "runoob",
        "roles" : [
                {
                        "role" : "dbOwner",
                        "db" : "runoob"
                }
        ]
}

查看用户

use admin

db.system.users.find()

```