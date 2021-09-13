package com.example.demoSpringJDBC.controller;

import com.example.demoSpringJDBC.model.Ogrenci;
import com.example.demoSpringJDBC.repo.OgrenciRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/ogrenci")
@CrossOrigin
public class OgrenciResource {
    private OgrenciRepository ogrenciRepository;

    // http://localhost:8080/ogrenci/helloSpringBoot
    @GetMapping(path = "/helloSpringBoot")
    public String helloSpringBoot() {
        return "hello SpringBoot";
    }

    // http://localhost:8080/ogrenci/getAll
    @GetMapping(path = "/getAll")
    public ResponseEntity<List<Ogrenci>> getAll() {
        return new ResponseEntity<>(ogrenciRepository.getAll(), HttpStatus.OK);
    }

    // http://localhost:8080/ogrenci/getById/{id}
    @GetMapping(path = "/getById/{id}")
    public ResponseEntity<Ogrenci> getById(@PathVariable(name = "id") int id) {
        return new ResponseEntity<>(ogrenciRepository.getOgrenciById(id), HttpStatus.OK);
    }

    // http://localhost:8080/ogrenci/insert
    @CrossOrigin
    @PostMapping(path = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> insert(@RequestBody Ogrenci ogrenci) {
        ogrenciRepository.insertOgrenciNamedParamEsas(ogrenci);
        return new ResponseEntity<>("Başarılı", HttpStatus.OK);
    }

    @PostMapping("/update")
    public boolean update(@RequestBody Ogrenci ogrenci) {
        return this.ogrenciRepository.update(ogrenci);
    }

    // http://localhost:8080/ogrenci/deleteById/{id}
    @PostMapping(path = "/deleteById/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(name = "id") int id) {
        /*try
        {
            int k = 7 / 0;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }*/
        ogrenciRepository.deleteOgrenciById(id);
        return new ResponseEntity<>("başarılı", HttpStatus.OK);
    }
}
