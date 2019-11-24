package com.socketio.master.main;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p> 点定义</p>
 *
 * @author: daizhao 14:01 2019/11/24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Point {

    private int xAxis;

    private int yAxis ;
}
