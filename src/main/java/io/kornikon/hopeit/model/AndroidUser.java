package io.kornikon.hopeit.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor(onConstructor = @__({@PersistenceConstructor}))
@Builder
@ToString
@Document
public class AndroidUser {
    @Id
    private String id;

    private String name;
    private String email;
    private String password;

    @DBRef
    private List<Donation> donations;

    @DBRef
    private List<Message> messages;
}
