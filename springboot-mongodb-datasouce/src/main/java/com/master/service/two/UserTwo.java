package com.master.service.two;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

/**
 * @author tuonioooo
 * @version 1.0.0
 * @date 16/4/27 下午10:04.
 * @blog https://blog.csdn.net/tuoni123
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserTwo {

    @Id
    private Long id;

    private String username;
    private int age;


}
