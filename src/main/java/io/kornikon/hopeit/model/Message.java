package io.kornikon.hopeit.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor(onConstructor = @__({@PersistenceConstructor}))
@Builder
@ToString
@Document
public class Message {
    @Id
    private String id;

    private String title;
    private String content;
    private boolean read;

    private Calendar dateSent;

    Map<String, String> attachmentsIds;
    Map<String, byte[]> attachments;

}
