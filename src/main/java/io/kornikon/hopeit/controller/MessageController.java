package io.kornikon.hopeit.controller;


import io.kornikon.hopeit.model.AndroidUser;
import io.kornikon.hopeit.model.Message;
import io.kornikon.hopeit.repository.AndroidUserRepository;
import io.kornikon.hopeit.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("messages")
class MessageController {

    private final MessageRepository repository;

    @RequestMapping(name ="List all Message records")
    public List<Message> messages() {
        return repository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, name="Save Message record")
    public Message save(@RequestBody Message message) {
        return repository.save(message);
    }

    @RequestMapping(method = RequestMethod.DELETE, name="Delete AndroidUser record")
    public void delete(@RequestParam String id) {
        repository.delete(id);
    }

}
