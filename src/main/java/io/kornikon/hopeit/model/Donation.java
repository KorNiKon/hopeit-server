package io.kornikon.hopeit.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@Data
@AllArgsConstructor(onConstructor = @__({@PersistenceConstructor}))
@Builder
@ToString
@Document
public class Donation {
    @Id
    private String id;

    @DBRef
    private Kid kid;
    private BigDecimal amount;

}
