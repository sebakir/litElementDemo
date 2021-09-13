package com.example.demoSpringJDBC.controller;

import com.example.demoSpringJDBC.model.Ogretmen;
import com.example.demoSpringJDBC.service.OgretmenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/ogretmen")
@CrossOrigin
public class OgretmenResource {
    private OgretmenService ogretmenService;

    @Autowired
    public OgretmenResource(OgretmenService ogretmenService) {
        this.ogretmenService = ogretmenService;
    }

    @GetMapping(path = "/getall")
    public ResponseEntity<List<Ogretmen>> getall() {
        return new ResponseEntity<>(ogretmenService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/getById/{id}")
    public ResponseEntity<Ogretmen> getById(@PathVariable(name = "id") int id) {
        return new ResponseEntity<>(ogretmenService.getById(id), HttpStatus.OK);
    }

    @PostMapping(path = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> insert(@RequestBody Ogretmen ogretmen) {
        ogretmenService.insertJDBC(ogretmen);
        return new ResponseEntity<>("Başarılı", HttpStatus.OK);
    }

    @PostMapping("/update")
    public boolean update(@RequestBody Ogretmen ogretmen) {
        return this.ogretmenService.update(ogretmen);
    }

    @PostMapping(path = "/deleteById/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(name = "id") int id) {
        ogretmenService.delete(id);
        return new ResponseEntity<>("başarılı", HttpStatus.OK);
    }
}
