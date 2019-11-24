package com.netty.socketio.master.config.socketio;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p> socket ip 端口 </p>
 *
 * @author daizhao  2019-10-24 17:37
 */
@Component
@Data
@ConfigurationProperties(prefix = "socket-io")
class WebsocketConnectConfig {
    private String host;
    private Integer port;
}

