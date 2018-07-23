package com.master.config;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync //开启异步任务支持 
public class ThreadPoolConfig implements AsyncConfigurer {

	private static final int CORE_POLL_SIZE = 150;
	
	private static final int MAX_POLL_SIZE = 200;
	
	private static final int QUEUE_CAPACITY = 1000000;
	
	private static final int KEEP_ALIVE_SECONDS = 60;
	
	/**
	 * 实现AsyncConfigurer接口并重写getAsyncExecutor方法，
	 * 创建线程池ThreadPoolTaskExecutor(Spring自带的线程池与Java使用的还是有些区分)，并返回
	 */
	@Bean("executor")
	@Override
	public Executor getAsyncExecutor() {
		 ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
         taskExecutor.setCorePoolSize(CORE_POLL_SIZE);//核心线程数
         taskExecutor.setMaxPoolSize(MAX_POLL_SIZE);//最大线程数
         taskExecutor.setQueueCapacity(QUEUE_CAPACITY);//队列最大长度
         taskExecutor.setKeepAliveSeconds(KEEP_ALIVE_SECONDS);//线程池维护线程所允许的空闲时间
		 taskExecutor.setWaitForTasksToCompleteOnShutdown(true);//用来设置线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean
		 taskExecutor.setAwaitTerminationSeconds(KEEP_ALIVE_SECONDS);//设置线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住
         taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());//线程池对拒绝任务(无线程可用)的处理策略
         taskExecutor.initialize();
         return taskExecutor;
	}

	/**
	 * 优雅的关闭线程池
	 * @param executor
	 */
	public void stop(ExecutorService executor) {
		try {
			executor.shutdown();
			executor.awaitTermination(60, TimeUnit.SECONDS);
		}
		catch (InterruptedException e) {
			System.err.println("termination interrupted");
		}
		finally {
			if (!executor.isTerminated()) {
				System.err.println("killing non-finished tasks");
			}
			executor.shutdownNow();
		}
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		// TODO Auto-generated method stub
		return null;
	}

}
