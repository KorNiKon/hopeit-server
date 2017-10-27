package io.kornikon.hopeit.repository;

import io.kornikon.hopeit.model.Child;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface KidRepository extends MongoRepository<Child, String> {
    List<Child> findByName(String name);
    List<Child> findByAge(int age);
    Child findById(String id);
}
