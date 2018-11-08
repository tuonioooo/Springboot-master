package com.master.bean;

import lombok.*;

/**
 * Created by daizhao.
 * User: tony
 * Date: 2018-11-6
 * Time: 10:01
 * info:
 */

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String uid;

    private String username;

    private String password;
}
