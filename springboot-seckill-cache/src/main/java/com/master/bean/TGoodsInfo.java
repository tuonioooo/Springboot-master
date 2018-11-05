package com.master.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


/**
 * 
 * @author tuonioooo
 * @date 2018-11-05
 */
@Table(name="t_goods_info")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TGoodsInfo {
	/**  */
    @Id
    @GeneratedValue(generator = "assignedGenerator")
    @GenericGenerator(name = "assignedGenerator", strategy = "assigned")
	private Integer id;
	/**  */
    @Column
	private String code;
	/**  */
    @Column
	private Integer amout;
	/**  */
    @Column
	private Integer version;

}