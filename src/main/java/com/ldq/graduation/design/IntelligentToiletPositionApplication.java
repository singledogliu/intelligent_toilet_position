package com.ldq.graduation.design;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScans;

/**
 * @author ldq
 */
@SpringBootApplication
@MapperScan("com.ldq.graduation.design.dao")
public class IntelligentToiletPositionApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntelligentToiletPositionApplication.class, args);
	}

}
