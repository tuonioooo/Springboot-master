## springboot dubbo 1.5.x 配置

### 简介

Dubbo是阿里巴巴公司开源的一个高性能优秀的服务框架，使得应用可通过高性能的 RPC 实现服务的输出和输入功能，可以和Spring框架无缝集成。更是 SOA 服务治理的一种方案

核心：
1. 远程通信，向本地调用一样调用远程方法。
2. 集群容错
3. 服务自动发现和注册，可平滑添加或者删除服务提供者。
 
我们常常使用 Springboot 暴露 HTTP 服务，并走 JSON 模式。但慢慢量大了，一种 SOA 的治理方案。这样可以暴露出 Dubbo 服务接口，提供给 Dubbo 消费者进行 RPC 调用。下面我们详解下如何集成 Dubbo。

### 一.dubbo provider配置(springboot-dubbo-provider工程)

**application.properties 配置**

```
## Dubbo 服务提供者配置
# 应用名称
spring.dubbo.application.name=provider  
# 注册中心地址
spring.dubbo.registry.address=zookeeper://192.168.127.87:2181
# 协议名称
spring.dubbo.protocol.name=dubbo
# 协议端口
spring.dubbo.protocol.port=20880
# 对外服务目录
spring.dubbo.scan=com.master.dubbo
# tomcat端口号
server.port=8081
```
> 注意：dubbo服务，是基于zookeeper服务使用的，本文不讲解zookeeper的安装和使用，具体安装，请读者另行学习

**对外服务接口**

```
public interface ComputeService {

    Integer add(int a, int b);

}
```

```
import com.alibaba.dubbo.config.annotation.Service;
import com.master.dubbo.service.ComputeService;
@Service(version = "1.0.0")
public class ComputeServiceImpl implements ComputeService {

    @Override
    public Integer add(int a, int b) {
        return a + b;
    }

}
```

> 这里的@Service 不是Spring里的注解

### 二.dubbo client配置(springboot-dubbo-client工程)


**application.properties 配置**

```
## 避免和 server 工程端口冲突
server.port=8082

## Dubbo 服务消费者配置
spring.dubbo.application.name=consumer
spring.dubbo.registry.address=zookeeper://192.168.127.87:2181
spring.dubbo.scan=com.master.dubbo
```

**调用接口**

```
public interface ComputeService {
    Integer add(int a, int b);
}
```

```
@Component
public class ComputeConsumerService {
    @Reference(version = "1.0.0")
    ComputeService computeService;

    public void consumer(){
        System.out.println("consumer={" + computeService.add(1,3)+"}");

    }
}
```
>@Reference(version = “1.0.0”) 通过该注解，订阅该接口版本为 1.0.0 的 Dubbo 服务。

```
@SpringBootApplication
public class SpringbootDubboClientApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringbootDubboClientApplication.class, args);
		ComputeConsumerService computeConsumerService = applicationContext.getBean(ComputeConsumerService.class);
		computeConsumerService.consumer();
	}
}
```