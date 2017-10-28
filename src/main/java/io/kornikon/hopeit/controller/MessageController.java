package io.kornikon.hopeit.controller;


import com.mongodb.MongoException;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import io.kornikon.hopeit.model.AndroidUser;
import io.kornikon.hopeit.model.Message;
import io.kornikon.hopeit.repository.AndroidUserRepository;
import io.kornikon.hopeit.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("messages")
class MessageController {

    private final MessageRepository repository;
    private final MongoTemplate mongoTemplate;

    @RequestMapping(name ="List all Message records")
    public List<Message> messages() {
        return repository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, name="Save Message record")
    public Message save(@RequestBody Message message) {
        if(!message.getAttachments().isEmpty()) {
            message.getAttachments().forEach((photoName, bytecode) -> {
                if (photoName == null) {
                    return;
                }
                GridFS photo = new GridFS(mongoTemplate.getDb());
                try {
                    photo.findOne(photoName);
                } catch (MongoException e) {
                    GridFSInputFile gfsFile = photo.createFile(photoName);
                    gfsFile.setFilename(photoName);
                    gfsFile.save();
                    message.getAttachmentsIds().add(photoName);
                }
            });
        }
        return repository.save(message);
    }

    @RequestMapping(method = RequestMethod.DELETE, name="Delete AndroidUser record")
    public void delete(@RequestParam String id) {
        repository.delete(id);
    }

}
