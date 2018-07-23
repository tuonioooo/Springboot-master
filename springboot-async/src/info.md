#async使用介绍

##常用注解

@EnableAsync开启注解：

```
@SpringBootApplication
@EnableAsync
public class SpringbootAsyncApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAsyncApplication.class, args);
	}
}
```
@Async 声明异步方法，每个声明方法都会开启一个线程，进行异步操作

```
@Async
public Future<String> doTaskOne() throws Exception {
   System.out.println("开始做任务一");
   long start = System.currentTimeMillis();
   Thread.sleep(random.nextInt(10000));
   long end = System.currentTimeMillis();
   System.out.println("完成任务一，耗时：" + (end - start) + "毫秒");
   return new AsyncResult<>("任务一完成");
}
```

@Async("executor") 指定线程池，声明异步方法

```
@Async("executor")
public void doTaskOne() throws Exception {
    log.info("开始做任务一");
    long start = System.currentTimeMillis();
    Thread.sleep(random.nextInt(10000));
    long end = System.currentTimeMillis();
    log.info("完成任务一，耗时：" + (end - start) + "毫秒");
}
```
@SneakyThrows 抛出指定异常
```
https://projectlombok.org/features/SneakyThrows
```