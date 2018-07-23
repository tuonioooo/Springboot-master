package com.master.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * MongoDBUtils
 * @author administration
 *
 */

@Slf4j
public class DBUtil {

	/**
	 * 有用户名密码的方式连接mongoDB（MongoCredential方式）
	 * @param mongoProperties
	 * @return
	 */
	public static MongoClient getMongoClientByCredent(MongoProperties mongoProperties){
		MongoClient mongoClient;
		List<ServerAddress> serverAddrList = new ArrayList<ServerAddress>();
		ServerAddress serverAddress = new ServerAddress(mongoProperties.getHost());
		serverAddrList.add(serverAddress);
		List<MongoCredential> credentialList = new ArrayList<MongoCredential>();
		MongoCredential credential = MongoCredential.createCredential(mongoProperties.getUsername(), mongoProperties.getDatabase(), mongoProperties.getPassword());
		credentialList.add(credential);
		mongoClient = new MongoClient(serverAddrList, credentialList);
		return mongoClient;
	}

	/**
	 *有用户名密码的方式连接mongoDB（URI方式）
	 * @param mongoProperties
	 * @return
	 */
	public static MongoClient getMongoClientByURI(MongoProperties mongoProperties, String pwd){
		MongoClient mongoClient;
		log.info(mongoProperties.getUsername() + "," + mongoProperties.getPassword());
		String url = String.format("mongodb://%s:%s@%s/%s", mongoProperties.getUsername(), pwd, mongoProperties.getHost(), mongoProperties.getDatabase());
		log.info("mongodb url" + url);
		MongoClientURI mongoClientURI = new MongoClientURI(url);
		mongoClient = new MongoClient(mongoClientURI);
		return mongoClient;
	}

	public static void main(String[] args) {
		
		String str = "sdf";
		char[] chars = str.toCharArray();
		System.out.println(chars);
		System.out.println(String.valueOf(chars));
	}
	
}