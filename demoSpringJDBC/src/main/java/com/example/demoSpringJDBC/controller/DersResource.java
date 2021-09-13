package com.example.demoSpringJDBC.controller;

import com.example.demoSpringJDBC.model.Ders;
import com.example.demoSpringJDBC.service.DersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/ders")
@CrossOrigin
public class DersResource {
    private DersService dersService;

    @Autowired
    public DersResource(DersService dersService) {
        this.dersService = dersService;
    }

    @GetMapping(path = "/getall")
    public ResponseEntity<List<Ders>> getall() {
        return new ResponseEntity<>(dersService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/getById/{id}")
    public ResponseEntity<Ders> getById(@PathVariable(name = "id") int id) {
        return new ResponseEntity<>(dersService.getById(id), HttpStatus.OK);
    }

    @PostMapping(path = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> insert(@RequestBody Ders ders) {
        dersService.insertJDBC(ders);
        return new ResponseEntity<>("Başarılı", HttpStatus.OK);
    }

    @PostMapping("/update")
    public boolean update(@RequestBody Ders ders) {
        return this.dersService.update(ders);
    }

    @PostMapping(path = "/deleteById/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(name = "id") int id) {
        dersService.delete(id);
        return new ResponseEntity<>("başarılı", HttpStatus.OK);
    }
}
