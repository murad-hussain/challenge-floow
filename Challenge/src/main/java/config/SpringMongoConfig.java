package config;

import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoClient;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.net.UnknownHostException;

/**
 * Spring MongoDB configuration file
 */
@Configuration
public class SpringMongoConfig {

    MongoClientURI uri = null;

    MongoClient mongoClient = null;
	MongoDatabase database = null;

    public SpringMongoConfig() throws UnknownHostException {
        uri = new MongoClientURI(
                "mongodb://root:<root>@cluster0-shard-00-00-80xbv.mongodb.net:27017,cluster0-shard-00-01-80xbv.mongodb.net:27017,cluster0-shard-00-02-80xbv.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin");
        mongoClient = new MongoClient(uri);
        database = mongoClient.getDatabase("test");
    }

    public @Bean
    MongoTemplate mongoTemplate() throws Exception {

        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, "test"); // default db path
        return mongoTemplate;

    }

}