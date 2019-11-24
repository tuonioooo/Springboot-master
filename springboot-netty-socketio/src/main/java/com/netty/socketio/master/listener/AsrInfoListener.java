package com.netty.socketio.master.listener;

import com.alibaba.fastjson.JSON;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <p> 签入 </p>
 *
 * @author zhengpengfei 2019/11/14 14:11
 */
@Slf4j
@Component
public class AsrInfoListener implements DataListener<String> {
    @Override
    public void onData(SocketIOClient client, String data, AckRequest ackSender) throws Exception {
        log.info("client = " + JSON.toJSONString(client,true));
        log.info("ackSender = " + JSON.toJSONString(ackSender,true));
        log.info(data);
    }
}
