package io.kornikon.hopeit.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Calendar;

@Data
@AllArgsConstructor(onConstructor = @__({@PersistenceConstructor}))
@Builder
@ToString
@Document
public class User {
    @Id
    private String id;

    private String name;
    private String role;
    private String password;
}
