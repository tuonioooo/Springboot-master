package com.netty.socketio.master.listener;

import com.corundumstudio.socketio.SocketIOClient;
import com.netty.socketio.master.constants.RedissonKey;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * <p>连接接通时间监听</p>
 *
 * @author daizhao  2019-10-24 18:59
 */
@Slf4j
@Component
public class ConnectListener implements com.corundumstudio.socketio.listener.ConnectListener {

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 连接处理
     *
     * @param client socket 连接
     */
    @Override
    public void onConnect(SocketIOClient client) {
        try {
            log.info("Client Connected!【{}】", client.getSessionId());
            //从client 获取 clientId
            UUID clientId = client.getSessionId();
            //
            String token = client.getHandshakeData().getSingleUrlParam("token");
            if (token != null && !token.isEmpty()) {
//                JwtPrincipal principal = JwtUtils.parseToken(token);
//                int userId = principal.getId();
//                int tenantId = principal.getTenantId();
//                if (!Objects.isNull(userId)) {
//                    connectService.connect(userId, clientId);
//
//                } else {
//                    //token error
//                    log.warn("token is error, client disconnect!");
//                }
                log.info("clientId={}", clientId);
                log.info("token={}", token);
                client.sendEvent("notification", "成功连接");
            } else {
                //token 不存在
                log.warn("token is Empty, client disconnect!");
            }
        } catch (Exception e) {
            log.error("socket连接 异常!", e);
        }
    }
}
