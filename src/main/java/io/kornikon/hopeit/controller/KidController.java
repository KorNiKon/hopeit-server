package io.kornikon.hopeit.controller;


import io.kornikon.hopeit.model.Child;
import io.kornikon.hopeit.repository.KidRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("kids")
class KidController {

    private final KidRepository repository;

    @RequestMapping
    public List<Child> children() {
        return repository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Child save(@RequestBody Child child) {
        return repository.save(child);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void delete(@RequestParam String id) {
        repository.delete(id);
    }

    @RequestMapping("/{id}")
    public Child customer(@PathVariable String id) {
        return repository.findById(id);
    }

}
