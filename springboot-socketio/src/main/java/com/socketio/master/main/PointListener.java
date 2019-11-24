package com.socketio.master.main;

import com.alibaba.fastjson.JSON;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;


public class PointListener implements DataListener<Point> {

	@Override
	public void onData(SocketIOClient client, Point data, AckRequest ackSender) throws Exception {

		System.out.println("client = " + JSON.toJSONString(client,true));
		System.out.println("ackSender = " + JSON.toJSONString(ackSender,true));
		System.out.println(data);
	}

}