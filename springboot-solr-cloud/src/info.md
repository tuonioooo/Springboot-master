## springboot 2.0.3 集成solr cloud 7.0

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
#spring.data.solr.zk-host=god.cn:2181
spring.data.solr.repositories.enabled=true
spring.data.solr.host=http://192.168.127.87:8983/solr/
```
>spring.data.solr.host 配置solr地址

**SolrConfig配置与1.5.x的不一致**

```
@Configuration
@EnableSolrRepositories(basePackages = {"com.master.solr"})
public class SolrConfig {

    @Value("${spring.data.solr.host}")
    private String solrUrl;

    @Bean
    public CloudSolrClient solrClient() {
        return new CloudSolrClient.Builder()
            .withSolrUrl(solrUrl)
            .build();
    }

}
```

>这里的CloudSolrClient方法构造，与1.5.x不一样，  
@Bean  
public CloudSolrClient solrClient() {  
   return new CloudSolrClient(zkHost);  
}  
这个方法未来版本会删除，更换最新的方式    
@Bean  
public CloudSolrClient solrClient() {  
    return new CloudSolrClient.Builder()  
        .withSolrUrl(solrUrl)  
        .build();  
}  
新版本已经移除属性 “multicoreSupport = true”
