package com.think;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author NINGMEI
 */
@SpringBootApplication
@MapperScan("com.think.db.mapper")
public class TrainDataApplication {

	static Logger logger = LoggerFactory.getLogger(TrainDataApplication.class);

	public static void main(String[] args) {
		logger.info("=================Train-Data begin to start==========================");
		SpringApplication.run(TrainDataApplication.class, args);
		logger.info("=================Train-Data Start over==========================");
	}

}
