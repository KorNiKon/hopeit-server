package io.kornikon.hopeit.model;

import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.Calendar;

@Data
@AllArgsConstructor(onConstructor = @__({@PersistenceConstructor}))
@Builder
@ToString
@Document
public class Kid {
    @Id
    private String id;

    private String name;

    private String photoId;
    private byte[] photo;

    private int age;
    private String desc;
    private BigDecimal cashTarget;
    private BigDecimal cashNow;
    private String category;
    private Calendar deadline;
    private boolean lastChance;
}
