package com.netty.socketio.master.listener;

import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.HandshakeData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <p>socket鉴权</p>
 *
 * @author daizhao  2019/1/5 16:15
 */
@Slf4j
@Component
public class AuthorizationHandlerListener implements AuthorizationListener {

    @Override
    public boolean isAuthorized(HandshakeData handshakeData) {
        //TODO
        return true;
    }
}
