package io.kornikon.hopeit.controller;


import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import io.kornikon.hopeit.model.Message;
import io.kornikon.hopeit.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
class MessageController {

    private final MessageRepository repository;
//    private final MongoTemplate mongoTemplate;

    @RequestMapping(path="messages", name = "List all Message records")
    public List<Message> messages() {
        return repository.findAll().stream().filter(m->m.getDateSent() != null).collect(Collectors.toList());
    }

    @RequestMapping(path="messages", method = RequestMethod.POST, name = "Save Message record")
    public Message save(@RequestBody Message message) {
//        if (!message.getAttachments().isEmpty()) {
//            message.getAttachments().forEach((photoName, bytecode) -> {
//                if (photoName == null) {
//                    return;
//                }
//                GridFS photo = new GridFS(mongoTemplate.getDb());
//                GridFSInputFile gfsFile = photo.createFile(photoName);
//                gfsFile.setFilename(photoName);
//                gfsFile.save();
//                message.getAttachmentsIds().add(photoName);
//            });
//        }

        return repository.save(message);
    }

    @RequestMapping(path="messages", method = RequestMethod.DELETE, name = "Delete Message record")
    public void delete(@RequestParam String id) {
        repository.delete(id);
    }

    @RequestMapping(path="notifications", name = "List all Notification records")
    public List<Message> notifications() {
        return repository.findAll().stream().filter(m->m.getDateSent() == null).collect(Collectors.toList());
    }

    @RequestMapping(path="notifications", method = RequestMethod.POST, name = "Save Notification record")
    public Message saveNotifications(@RequestBody Message message) {
        return repository.save(message);
    }

    @RequestMapping(path="notifications", method = RequestMethod.DELETE, name = "Delete Notification record")
    public void deleteNotifications(@RequestParam String id) {
        repository.delete(id);
    }

}
