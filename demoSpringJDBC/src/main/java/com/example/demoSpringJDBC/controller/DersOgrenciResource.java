package com.example.demoSpringJDBC.controller;

import com.example.demoSpringJDBC.model.Ders_Ogrenci;
import com.example.demoSpringJDBC.service.DersOgrenciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/dersOgreci")
@CrossOrigin
public class DersOgrenciResource {
    private DersOgrenciService dersOgrenciService;

    @Autowired
    public DersOgrenciResource(DersOgrenciService dersOgrenciService) {
        this.dersOgrenciService = dersOgrenciService;
    }

    @GetMapping(path = "/getall")
    public ResponseEntity<List<Ders_Ogrenci>> getall() {
        return new ResponseEntity<>(dersOgrenciService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/getById/{id}")
    public ResponseEntity<Ders_Ogrenci> getById(@PathVariable(name = "id") int id) {
        return new ResponseEntity<>(dersOgrenciService.getById(id), HttpStatus.OK);
    }

    @PostMapping(path = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> insert(@RequestBody Ders_Ogrenci dersOgrenci) {
        dersOgrenciService.insertJDBC(dersOgrenci);
        return new ResponseEntity<>("Başarılı", HttpStatus.OK);
    }

    @PostMapping("/update")
    public boolean update(@RequestBody Ders_Ogrenci dersOgrenci) {
        return this.dersOgrenciService.update(dersOgrenci);
    }

    @PostMapping(path = "/deleteById/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(name = "id") int id) {
        dersOgrenciService.delete(id);
        return new ResponseEntity<>("başarılı", HttpStatus.OK);
    }
}
