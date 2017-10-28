package io.kornikon.hopeit.config;

import com.mongodb.*;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSFile;
import com.mongodb.gridfs.GridFSInputFile;
import io.kornikon.hopeit.model.AndroidUser;
import io.kornikon.hopeit.model.Donation;
import io.kornikon.hopeit.model.Kid;
import io.kornikon.hopeit.repository.AndroidUserRepository;
import io.kornikon.hopeit.repository.DonationRepository;
import io.kornikon.hopeit.repository.KidRepository;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import java.io.File;
import java.io.FileInputStream;
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
    public CommandLineRunner dataLoader(KidRepository kidRepository, DonationRepository donationRepository, AndroidUserRepository androidUserRepository) {
        return args -> {
            kidRepository.deleteAll();
            donationRepository.deleteAll();
            androidUserRepository.deleteAll();

            String newFileName = "src/main/resources/img/stock_image.png";
            File imageFile = new File(newFileName);
            GridFS dbFiles = new GridFS(mongoTemplate().getDb());
            DBCursor cursor = dbFiles.getFileList();

            GridFSInputFile gfsFile = dbFiles.createFile(imageFile);
            gfsFile.setFilename("img_stock");
            gfsFile.save();


            Kid alice = kidRepository.save(Kid.builder().name("Alice").desc("lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum "
                    + "lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum "
                    + "lorem ipsum lorem ipsum ").build());
            Donation don = donationRepository.save(new Donation(null,alice,BigDecimal.ONE));

            androidUserRepository.save(AndroidUser.builder().name("user1").donations(Arrays.asList(don)).build());

            kidRepository.save(Kid.builder().name("Bob").build());
            kidRepository.save(new Kid(null, "Full Data", "img_stock",null, 19999, "Great desc.",
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
