package com.think;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 *
 * @author NINGMEI
 */
@SpringBootApplication
@EnableTransactionManagement
public class TrainDataApplication {

	static Logger logger = LoggerFactory.getLogger(TrainDataApplication.class);

	public static void main(String[] args) {
		logger.info("=================Train-Server begin to start==========================");
		SpringApplication.run(TrainDataApplication.class, args);
		logger.info("=================Train-Server Start over==========================");
	}

}
