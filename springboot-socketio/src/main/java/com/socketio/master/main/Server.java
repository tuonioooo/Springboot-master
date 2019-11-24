package com.socketio.master.main;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;


import java.util.*;

public class Server {
	private static List<SocketIOClient> clients = new ArrayList<>();//用于保存所有客户端
 
	public static void main(String[] args) throws Exception {
        System.setProperty("io.netty.leakDetectionLevel", "advanced");
		Configuration configuration = new Configuration();
		configuration.setHostname("127.0.0.1");//设置主机名
		configuration.setPort(8082);//设置监听的端口号
		SocketIOServer server = new SocketIOServer(configuration);//根据配置创建服务器对象

		//添加客户端连接监听器
		server.addConnectListener(client -> {
			System.out.println("connected:SessionId=" + client.getSessionId());
			clients.add(client);//保存客户端
		});
        //添加事件监听
        server.addEventListener("pointevent", Point.class, new PointListener());
		//服务端启动
		server.start();
		System.out.println("server started");
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				Random random = new Random();
				for(SocketIOClient client : clients) {
					//添加推送事件
					client.sendEvent("pushpoint", new Point(random.nextInt(100), random.nextInt(100)));//每隔一秒推送一次
				}
			}
		}, 1000, 1000);
		
		Object object = new Object();
		synchronized (object) {
			object.wait();
		}
	}
 
}