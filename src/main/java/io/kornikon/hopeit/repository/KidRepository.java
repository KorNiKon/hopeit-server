package io.kornikon.hopeit.repository;

import io.kornikon.hopeit.model.Kid;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface KidRepository extends MongoRepository<Kid, String> {
    List<Kid> findByName(String name);
    List<Kid> findByAge(int age);
    Kid findById(String id);
}
