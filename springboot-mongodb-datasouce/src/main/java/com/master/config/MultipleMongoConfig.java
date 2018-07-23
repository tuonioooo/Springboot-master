package com.master.config;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

@Configuration
public class MultipleMongoConfig {

	@Autowired
	private MultipleMongoProperties multipleMongoProperties;

	@Primary
	@Bean(name = PrimaryMongoConfig.MONGO_TEMPLATE)
	public MongoTemplate primaryMongoTemplate() throws Exception {
		return new MongoTemplate(primaryFactory(this.multipleMongoProperties.getPrimary()));
	}

	@Bean(name = SecondaryMongoConfig.MONGO_TEMPLATE)
	@Qualifier(SecondaryMongoConfig.MONGO_TEMPLATE)
	public MongoTemplate secondaryMongoTemplate() throws Exception {
        return new MongoTemplate(secondaryFactory(this.multipleMongoProperties.getSecondary()));
	}

	@Bean
    @Primary
	public MongoDbFactory primaryFactory(MongoProperties mongo) throws Exception {
		mongo.setPassword(this.multipleMongoProperties.getPwd().toCharArray());
		return new SimpleMongoDbFactory(DBUtil.getMongoClientByURI(mongo, this.multipleMongoProperties.getPwd()), mongo.getDatabase());
	}

	@Bean
	public MongoDbFactory secondaryFactory(MongoProperties mongo) throws Exception {
		return new SimpleMongoDbFactory(new MongoClient(mongo.getHost(), mongo.getPort()),
				mongo.getDatabase());
	}
}