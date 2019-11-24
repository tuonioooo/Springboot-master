package com.netty.socketio.master.init.socketio;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.netty.socketio.master.constants.RedissonKey;
import com.netty.socketio.master.listener.AsrInfoListener;
import com.netty.socketio.master.listener.AuthorizationHandlerListener;
import com.netty.socketio.master.listener.ConnectListener;
import com.netty.socketio.master.listener.DisconnectListener;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.*;

/**
 * <p>socketIO 事件注册、启动销毁的server</p>
 *
 * @author daizhao  2018/5/4 20:53
 */
@Slf4j
@Service
public class WebSocketInitServer {


    @Autowired
    private RedissonClient redissonClient;

    private static List<SocketIOClient> clients = new ArrayList<>();//用于保存所有客户端

    @Autowired
    private AuthorizationHandlerListener authorizationHandlerListener;

    @Autowired
    private SocketIOServer socketIoServer;

    @Autowired
    private ConnectListener connectListener;

    @Autowired
    private DisconnectListener disconnectListener;

    @Autowired
    private AsrInfoListener asrInfoListener;

    @PostConstruct
    public void start() {
        log.info("SocketIO Server starting...");
        //region 连接socket 相关
        socketIoServer.getConfiguration().setAuthorizationListener(authorizationHandlerListener);
        socketIoServer.addConnectListener(connectListener);
        socketIoServer.addDisconnectListener(disconnectListener);
        //endregion

        //事件监听
        socketIoServer.addEventListener("asrInfoEvent", String.class, asrInfoListener);
        socketIoServer.start();
        log.info("SocketIO Server started.");
    }

    @PreDestroy
    public void stop() {
        log.info("SocketIO Server stopping...");
        socketIoServer.removeAllListeners("test");
        socketIoServer.stop();
        log.info("SocketIO Server stopped.");
    }


}
