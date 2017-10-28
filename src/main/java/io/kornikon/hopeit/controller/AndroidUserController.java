package io.kornikon.hopeit.controller;


import io.kornikon.hopeit.model.AndroidUser;
import io.kornikon.hopeit.model.Kid;
import io.kornikon.hopeit.repository.AndroidUserRepository;
import io.kornikon.hopeit.repository.KidRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("android-users")
class AndroidUserController {

    private final AndroidUserRepository repository;

    @RequestMapping(name ="List all AndroidUser records")
    public List<AndroidUser> androidUsers() {
        return repository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, name="Save AndroidUser record")
    public AndroidUser save(@RequestBody AndroidUser androidUser) {
        return repository.save(androidUser);
    }

    @RequestMapping(method = RequestMethod.DELETE, name="Delete AndroidUser record")
    public void delete(@RequestParam String id) {
        repository.delete(id);
    }

    @RequestMapping(path = "/{id}", name="Get AndroidUser by {id}")
    public AndroidUser customer(@PathVariable String id) {
        return repository.findById(id);
    }

}
