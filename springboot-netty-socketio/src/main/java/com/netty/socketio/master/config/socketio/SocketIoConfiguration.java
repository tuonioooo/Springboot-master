package com.netty.socketio.master.config.socketio;

import com.corundumstudio.socketio.AckMode;
import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import com.corundumstudio.socketio.store.RedissonStoreFactory;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * <p>SocketIo 配置类</p>
 *
 * @author daizhao  2019-10-24 17:47
 */
@Configuration
public class SocketIoConfiguration {
    @Resource
    private WebsocketConnectConfig websocketConnectConfig;

    @Bean
    public RedissonStoreFactory redissonStoreFactory(@Autowired RedissonClient redisson) throws IOException {
        return new RedissonStoreFactory(redisson);
    }

    @Bean
    public SocketIOServer socketIOServer(RedissonStoreFactory redissonStoreFactory) {
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        // config.setPackagePrefix("");
        config.getSocketConfig().setReuseAddress(true);
        // config.setUseLinuxNativeEpoll(true);
        config.setHostname(websocketConnectConfig.getHost());
        config.setPort(websocketConnectConfig.getPort());
        config.setWorkerThreads(100);

        // 连接认证，这里使用token更合适
//        config.setAuthorizationListener(new AuthorizationListener() {
//            @Override
//            public boolean isAuthorized(HandshakeData data) {
//                // String token = data.getSingleUrlParam("token");
//                // String username = JWTUtil.getSocketUsername(token);
//                // return JWTUtil.verifySocket(token, "secret");
//                return true;
//            }
//        });

        config.setAckMode(AckMode.MANUAL);
        //数据存储容器
        config.setStoreFactory(redissonStoreFactory);
        // 协议升级超时时间（毫秒），默认10000。HTTP握手升级为ws协议超时时间
        config.setUpgradeTimeout(10000);
        // Ping消息间隔（毫秒），默认25000。客户端向服务器发送一条心跳消息间隔
        config.setPingInterval(25000);
        // Ping消息超时时间（毫秒），默认60000，这个时间间隔内没有接收到心跳消息就会发送超时事件
        config.setPingTimeout(60000);
        //核心线程数
        config.setBossThreads(100);
        //工作线程数
        config.setWorkerThreads(200);
        return new SocketIOServer(config);
    }

    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketServer) {
        return new SpringAnnotationScanner(socketServer);
    }
}

