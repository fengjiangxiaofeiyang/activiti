package com.xiaofeiyang.activiti;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * @author: yangchun
 * @description:
 * @date: Created in 2020-06-12 11:37
 */
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class DemoApplication {
    public static void main(String[] args) {

    }

}
