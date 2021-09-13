package com.example.demoSpringJDBC.controller;

import com.example.demoSpringJDBC.model.Konu;
import com.example.demoSpringJDBC.service.KonuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/konu")
@CrossOrigin
public class KonuResource {
    private KonuService konuService;

    @Autowired
    public KonuResource(KonuService konuService) {
        this.konuService = konuService;
    }

    @GetMapping(path = "/getall")
    public ResponseEntity<List<Konu>> getall() {
        return new ResponseEntity<>(konuService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/getById/{id}")
    public ResponseEntity<Konu> getById(@PathVariable(name = "id") int id) {
        return new ResponseEntity<>(konuService.getById(id), HttpStatus.OK);
    }

    @PostMapping(path = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> insert(@RequestBody Konu konu) {
        konuService.insertJDBC(konu);
        return new ResponseEntity<>("Başarılı", HttpStatus.OK);
    }

    @PostMapping("/update")
    public boolean update(@RequestBody Konu konu) {
        return this.konuService.update(konu);
    }

    @PostMapping(path = "/deleteById/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(name = "id") int id) {
        konuService.delete(id);
        return new ResponseEntity<>("başarılı", HttpStatus.OK);
    }
}
