package config;

import com.mongodb.MongoClientURI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import com.mongodb.MongoClient;

/**
 * Spring MongoDB configuration file
 */
@Configuration
public class SpringMongoConfig {

    public
    @Bean
    MongoTemplate mongoTemplate() throws Exception {

        MongoClientURI uri = new MongoClientURI(
                "mongodb://root:<root>@cluster0-shard-00-00-80xbv.mongodb.net:27017,cluster0-shard-00-01-80xbv.mongodb.net:27017,cluster0-shard-00-02-80xbv.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin");
        MongoClient mongoClient = new MongoClient(uri);
//        MongoClient mongoClient = new MongoClient("127.0.0.1");

//        MongoDatabase database = mongoClient.getDatabase("test");

        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, "test"); // default db path
        return mongoTemplate;

    }

}