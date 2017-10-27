package io.kornikon.hopeit.controller;


import io.kornikon.hopeit.model.Child;
import io.kornikon.hopeit.repository.KidRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/{id}")
    public Child customer(@PathVariable String id) {
        return repository.findById(id);
    }

//    @RequestMapping(value = "/query")
//    public List<Child> query(
//            @RequestParam(required = false) String firstName,
//            @RequestParam(required = false) String lastName) {
//        return repository.findByFirstNameOrLastName(firstName, lastName);
//    }

}
