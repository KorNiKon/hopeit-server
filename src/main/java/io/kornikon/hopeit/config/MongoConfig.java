package io.kornikon.hopeit.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import io.kornikon.hopeit.model.Child;
import io.kornikon.hopeit.repository.KidRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

class MongoConfig extends AbstractMongoConfiguration {

    @Value("${spring.data.mongodb.database}")
    private String database;

    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.port}")
    private int port;

    @Bean
    public CommandLineRunner dataLoader(KidRepository repository) {
        return args -> {
            repository.deleteAll();
            repository.save(Child.builder().name("Alice").build());
            repository.save(Child.builder().name("Bob").build());
        };
    }

    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(host, port);
    }

}
