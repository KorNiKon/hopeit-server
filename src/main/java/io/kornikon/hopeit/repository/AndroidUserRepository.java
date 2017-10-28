package io.kornikon.hopeit.repository;

import io.kornikon.hopeit.model.AndroidUser;
import io.kornikon.hopeit.model.Kid;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AndroidUserRepository extends MongoRepository<AndroidUser, String> {
    List<AndroidUser> findByName(String name);
    List<AndroidUser> findByEmail(String email);
    AndroidUser findById(String id);
    AndroidUser findByIdOrName(String idOrName);
}
