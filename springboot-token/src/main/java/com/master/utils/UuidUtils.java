package com.master.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * Uuid工具类
 */

public class UuidUtils {

	private static final Logger logger = LoggerFactory.getLogger(UuidUtils.class);

	/**
	 * 获得4个长度的十六进制的UUID
	 * @return UUID
	 */
	public static String get4UUID(){
		UUID id=UUID.randomUUID();
		String[] idd=id.toString().split("-");
		return idd[1];
	}
	/**
	 * 获得8个长度的十六进制的UUID
	 * @return UUID
	 */
	public static String get8UUID(){
		UUID id=UUID.randomUUID();
		String[] idd=id.toString().split("-");
		return idd[0];
	}
	/**
	 * 获得12个长度的十六进制的UUID
	 * @return UUID
	 */
	public static String get12UUID(){
		UUID id=UUID.randomUUID();
		String[] idd=id.toString().split("-");
		return idd[0]+idd[1];
	}
	/**
	 * 获得16个长度的十六进制的UUID
	 * @return UUID
	 */
	public static String get16UUID(){

		UUID id=UUID.randomUUID();
		String[] idd=id.toString().split("-");
		return idd[0]+idd[1]+idd[2];
	}
	/**
	 * 获得20个长度的十六进制的UUID
	 * @return UUID
	 */
	public static String get20UUID(){

		UUID id=UUID.randomUUID();
		String[] idd=id.toString().split("-");
		return idd[0]+idd[1]+idd[2]+idd[3];
	}
	/**
	 * 获得24个长度的十六进制的UUID
	 * @return UUID
	 */
	public static String get24UUID(){
		UUID id=UUID.randomUUID();
		String[] idd=id.toString().split("-");
		return idd[0]+idd[1]+idd[4];
	}

	public static String get32UUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}

	public static void main(String[] args) {
		logger.debug(UUID.randomUUID().toString());
	}
}
