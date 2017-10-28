package io.kornikon.hopeit.controller;


import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import io.kornikon.hopeit.model.Kid;
import io.kornikon.hopeit.repository.KidRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("kids")
class KidController {

    private final KidRepository repository;
    private final MongoTemplate mongoTemplate;

    @RequestMapping(name = "List all Child records")
    public List<Kid> children() {
        List<Kid> kids = repository.findAll();
        for (Kid kid : kids) {
            String photoId = kid.getPhotoId();
            if (photoId == null) {
                continue;
            }
            GridFS photo = new GridFS(mongoTemplate.getDb());
            GridFSDBFile gfsFile = photo.findOne(photoId);
            try {
                kid.setPhoto(IOUtils.toByteArray(gfsFile.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return kids;
    }

    @RequestMapping(method = RequestMethod.POST, name = "Save Child record")
    public Kid save(@RequestBody Kid kid) {
        return repository.save(kid);
    }

    @RequestMapping(method = RequestMethod.DELETE, name = "Delete Child record")
    public void delete(@RequestParam String id) {
        repository.delete(id);
    }

    @RequestMapping(path = "/{id}", name = "Get Child by {id}")
    public Kid customer(@PathVariable String id) {
        return repository.findById(id);
    }

}
