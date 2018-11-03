package com.master.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.io.Serializable;

/**
 * 库存表
 * @author tuonioooo
 * @date 2018-11-03
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Tgoodsinfo{
	/**  主键*/
	private Integer id;
	/**  订单Id*/
	private String code;
	/**  库存数量*/
	private Integer amout;
	/**  版本号*/
	private Integer version;
	/**  购买数量，数据库不存储*/
	private Integer buys;

}