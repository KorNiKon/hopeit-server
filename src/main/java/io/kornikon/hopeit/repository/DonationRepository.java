package io.kornikon.hopeit.repository;

import io.kornikon.hopeit.model.AndroidUser;
import io.kornikon.hopeit.model.Donation;
import io.kornikon.hopeit.model.Kid;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DonationRepository extends MongoRepository<Donation, String> {
    List<Donation> findByKid(Kid kid);
}
