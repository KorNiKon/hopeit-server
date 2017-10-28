package io.kornikon.hopeit.repository;

import io.kornikon.hopeit.model.AndroidUser;
import io.kornikon.hopeit.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {
}
