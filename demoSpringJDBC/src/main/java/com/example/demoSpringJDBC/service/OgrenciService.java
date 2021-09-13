package com.example.demoSpringJDBC.service;

import com.example.demoSpringJDBC.model.Ogrenci;
import com.example.demoSpringJDBC.repo.OgrenciRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OgrenciService {
    private OgrenciRepository ogrenciRepository;

    @Autowired
    public OgrenciService(OgrenciRepository ogrenciRepository) {
        this.ogrenciRepository = ogrenciRepository;
    }

    public List<Ogrenci> getAll() {
        return ogrenciRepository.getAll();
    }

    public Ogrenci getById(int id) {
        return ogrenciRepository.getOgrenciById(id);
    }

    public boolean insertJDBC(Ogrenci ogrenci) {
        this.ogrenciRepository.insertOgrenciJDBC(ogrenci);
        return true;
    }

    public boolean update(Ogrenci ogrenci) {
        this.ogrenciRepository.update(ogrenci);
        return true;
    }

    public boolean delete(int id) {
        this.ogrenciRepository.deleteOgrenciById(id);
        return true;
    }
}
