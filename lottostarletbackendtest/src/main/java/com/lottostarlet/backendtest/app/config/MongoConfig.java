package com.lottostarlet.backendtest.app.config;

import com.mongodb.WriteConcern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.WriteResultChecking;
import org.springframework.data.mongodb.core.convert.MongoConverter;

import java.net.UnknownHostException;

@Configuration
public class MongoConfig {

	private static final Logger LOG = LogManager.getLogger();

	@Bean
	public MongoTemplate mongoTemplate(final MongoDbFactory mongoDbFactory, final MongoConverter converter) throws UnknownHostException {

		final MongoTemplate template = new MongoTemplate(mongoDbFactory, converter);

		final WriteConcern defaultWriteConcern = WriteConcern.W1.withJournal(true);
		template.setWriteConcern(defaultWriteConcern);

		final WriteResultChecking defaultWriteCheck = WriteResultChecking.EXCEPTION;
		template.setWriteResultChecking(defaultWriteCheck);

		LOG.info("Setting default write concern to {} with write result checking {}", defaultWriteConcern, defaultWriteCheck);
		return template;
	}

}
