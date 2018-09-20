package com.master.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by daizhao.
 * User: tony
 * Date: 2018-8-23
 * Time: 14:20
 * info:
 */
@Configuration
public class SpringConfig {

    @Bean(name="python")
    public Python createP(){
        return new Python("allen", "人工智能");
    }
}
