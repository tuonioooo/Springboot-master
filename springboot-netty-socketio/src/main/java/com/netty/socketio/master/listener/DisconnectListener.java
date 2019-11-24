package com.netty.socketio.master.listener;

import com.corundumstudio.socketio.SocketIOClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * <p>断开连接接通时间监听</p>
 *
 * @author daizhao  2019-10-24 18:59
 */
@Slf4j
@Component
public class DisconnectListener implements com.corundumstudio.socketio.listener.DisconnectListener {

    /**
     * <p> 连接断开 </p>
     *
     * @param client 长连接
     * @author daizhao  2019-10-24 17:57
     */
    @Override
    public void onDisconnect(SocketIOClient client) {
        //获取clientId
        UUID clientId = client.getSessionId();
        log.info("clientId={}连接断开", clientId);
    }

}
