package io.kornikon.hopeit.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import io.kornikon.hopeit.model.AndroidUser;
import io.kornikon.hopeit.model.Donation;
import io.kornikon.hopeit.model.Kid;
import io.kornikon.hopeit.model.Message;
import io.kornikon.hopeit.repository.AndroidUserRepository;
import io.kornikon.hopeit.repository.DonationRepository;
import io.kornikon.hopeit.repository.KidRepository;
import io.kornikon.hopeit.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

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
    public CommandLineRunner dataLoader(KidRepository kidRepository,
                                        DonationRepository donationRepository,
                                        AndroidUserRepository androidUserRepository,
                                        MessageRepository messageRepository) {
        return args -> {
            kidRepository.deleteAll();
            donationRepository.deleteAll();
            androidUserRepository.deleteAll();
            messageRepository.deleteAll();

            GridFS dbFiles = new GridFS(mongoTemplate().getDb());

            GridFSInputFile f1 = saveFile(dbFiles, "src/main/resources/img/stock_image.png", "img_stock");
            GridFSInputFile f2 = saveFile(dbFiles, "src/main/resources/img/Bob.png", "Bob");
            GridFSInputFile f3 = saveFile(dbFiles, "src/main/resources/img/Alicja.png", "Alicja");
            GridFSInputFile f4 = saveFile(dbFiles, "src/main/resources/img/fulldata.png", "fulldata");
            GridFSInputFile f5 = saveFile(dbFiles, "src/main/resources/img/stock_image.png", "img_stock2");
            GridFSInputFile f6 = saveFile(dbFiles, "src/main/resources/img/Bob.png", "Bob2");
            GridFSInputFile f7 = saveFile(dbFiles, "src/main/resources/img/Alicja.png", "Alicja2");
            GridFSInputFile f8 = saveFile(dbFiles, "src/main/resources/img/fulldata.png", "fulldata2");


            Kid alice = kidRepository.save(Kid.builder().name("Alicja")
                    .desc("Przyjmuje chemię od 6 miesięcy.")
                    .age(5)
                    .cashNow(new BigDecimal(3452))
                    .cashTarget(new BigDecimal(40000))
                    .photoId(f3.getFilename())
                    .build());
            Donation don = donationRepository.save(new Donation(null, alice, new BigDecimal(6258)));

            Calendar dateSent = Calendar.getInstance();

            Message message = messageRepository.save(Message.builder()
                    .title("First message")
                    .content("fdsafkjh dshfhhfh hu hu hu !")
                    .dateSent(dateSent)
                    .build());

            Calendar dateSent2 = Calendar.getInstance();
            dateSent2.add(Calendar.MONTH, -2);
            Message message2 = messageRepository.save(Message.builder()
                    .title("Second message")
                    .content("2. fdsafkjh dshfhhfh hu hu hu !")
                    .dateSent(dateSent2)
                    .attachmentsIds(Arrays.asList(f4.getFilename(), f3.getFilename()))
                    .build());

            Calendar dateSent3 = Calendar.getInstance();
            dateSent3.add(Calendar.DAY_OF_MONTH, -1);
            Message message3 = messageRepository.save(Message.builder()
                    .title("Third message").content("??? fdsafkjh dshfhhfh hu hu hu !")
                    .dateSent(dateSent3)
                    .build());

            Message notification1 = messageRepository.save(Message.builder()
                    .title("Notify user").content("1??? fdsafkjh dshfhhfh hu hu hu !")
                    .dateSent(dateSent3)
                    .build());

            Message notification2 = messageRepository.save(Message.builder()
                    .title("Notify user").content("2??? fdsafkjh dshfhhfh hu hu hu !")
                    .dateSent(dateSent3)
                    .build());

            Message notification3 = messageRepository.save(Message.builder()
                    .title("Notify user").content("3??? fdsafkjh dshfhhfh hu hu hu !")
                    .dateSent(dateSent3)
                    .build());

            androidUserRepository.save(AndroidUser.builder().name("user1").donations(Arrays.asList(don)).messages(Arrays.asList(message, message2, message3)).build());


            kidRepository.save(Kid.builder().name("Krzysiu")
                    .age(3)
                    .cashNow(new BigDecimal(874))
                    .cashTarget(new BigDecimal(6000))
                    .photoId(f2.getFilename())
                    .build());
            kidRepository.save(new Kid(null, "Sławek", f1.getFilename(), null, 7, "Czeka go bardzo poważna i kosztowna operacja",
                    new BigDecimal("99999"), new BigDecimal("45000"), "MyCat",
                    Calendar.getInstance(), true));

            kidRepository.save(Kid.builder().name("Maciek")
                    .age(4)
                    .cashNow(new BigDecimal(874))
                    .cashTarget(new BigDecimal(6000))
                    .photoId(f6.getFilename())
                    .build());

            kidRepository.save(Kid.builder().name("Magda")
                    .age(1)
                    .desc("Ma dopiero rok")
                    .cashNow(new BigDecimal(744))
                    .cashTarget(new BigDecimal(5468))
                    .photoId(f5.getFilename())
                    .build());

            kidRepository.save(Kid.builder().name("Patryk")
                    .age(3)
                    .cashNow(new BigDecimal(874))
                    .cashTarget(new BigDecimal(4579))
                    .photoId(f7.getFilename())
                    .build());

            kidRepository.save(Kid.builder().name("Bartek")
                    .age(7)
                    .cashNow(new BigDecimal(874))
                    .cashTarget(new BigDecimal(4579))
                    .photoId(f8.getFilename())
                    .build());

        };
    }

    private GridFSInputFile saveFile(GridFS dbFiles, String newFileName, String name) throws Exception {
        File imageFile = new File(newFileName);
        GridFSInputFile gfsFile = dbFiles.createFile(imageFile);
        gfsFile.setFilename(name);
        gfsFile.save();
        return gfsFile;
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
