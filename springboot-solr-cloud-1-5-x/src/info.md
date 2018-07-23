## springboot 1.5.x 集成solr cloud 7.0

**添加依赖** 

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-solr</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.data</groupId>
    <artifactId>spring-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```

**applicaiton.properties配置**

```
spring.application.name=solr-cloud

#SolrCloud zookeeper
spring.data.solr.zk-host=god.cn:2181
```
>spring.data.solr.zk-host 是zookeeper服务器地址，多个
是spring.data.solr.zk-host=master:2181,slave:2181,slave:2181

**SolrConfig配置与2.0.x的不一致**

```
@Configuration
@EnableSolrRepositories(basePackages = {"com.master.solr"}, multicoreSupport = true)
public class SolrConfig {
    @Value("${spring.data.solr.zk-host}")
    private String zkHost;

    @Bean
    public CloudSolrClient solrClient() {
        return new CloudSolrClient(zkHost);
    }
}

```

