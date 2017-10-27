package io.kornikon.hopeit.model;

import lombok.*;
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
public class Child {
    @Id
    private String id;

    private String name;
    private int age;
    private String desc;
    private BigDecimal cashTarget;
    private BigDecimal cashNow;
    private String category;
    private Calendar deadline;
    private boolean lastChance;
}
