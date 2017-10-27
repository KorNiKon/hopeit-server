package io.kornikon.hopeit.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import io.kornikon.hopeit.model.Child;
import io.kornikon.hopeit.repository.KidRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;

@Configuration
class MongoConfig extends AbstractMongoConfiguration {

    @Value("${spring.data.mongodb.database}")
    private String database;

    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.username}")
    private String username;

    @Value("${spring.data.mongodb.password}")
    private String password;

    @Value("${spring.data.mongodb.port}")
    private int port;

    @Bean
    public CommandLineRunner dataLoader(KidRepository repository) {
        return args -> {
            repository.deleteAll();
            repository.save(Child.builder().name("Alice").build());
            repository.save(Child.builder().name("Bob").build());
            repository.save(new Child(null, "Full Data", 19999, "Great desc.",
                    new BigDecimal("99999.99"), new BigDecimal("0.00"), "MyCat",
                    Calendar.getInstance(), true));
        };
    }

    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Override
    public Mongo mongo() throws Exception {
        MongoCredential credentials = MongoCredential.createCredential(username, database, password.toCharArray());
        ServerAddress serverAddress = new ServerAddress(host, port);
        return new MongoClient(serverAddress, Arrays.asList(credentials));
    }

}
