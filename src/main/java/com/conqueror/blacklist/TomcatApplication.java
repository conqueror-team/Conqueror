package com.conqueror.blacklist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * 
    * @ClassName: TomcatApplication
    * @Description: tomcat启动添加类
    * @author Sven
    * @date 2017年9月8日
    *
 */
@SpringBootApplication
@ComponentScan({"com.conqueror.blacklist"})
@EnableAutoConfiguration(exclude = {
		org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class
})
public class TomcatApplication extends SpringBootServletInitializer{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TomcatApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(TomcatApplication.class, args);
    }
}
