package com.dmtd.hanfu.forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class},
		scanBasePackages= {"com.dmtd.hanfu.forum.controller.*","com.dmtd.hanfu.forum.service.*","com.dmtd.hanfu.forum.dao.*"})

public class ForumApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForumApplication.class, args);
	}

}
