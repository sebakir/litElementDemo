package com.example.demoSpringJDBC.service;

import com.example.demoSpringJDBC.model.Konu;
import com.example.demoSpringJDBC.repo.KonuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KonuService {
    private KonuRepository konuRepository;

    @Autowired
    public KonuService(KonuRepository konuRepository) {
        this.konuRepository = konuRepository;
    }

    public List<Konu> getAll() {
        return konuRepository.getall();
    }

    public Konu getById(int id) {
        return konuRepository.getById(id);
    }

    public boolean insertJDBC(Konu konu) {
        this.konuRepository.insert(konu);
        return true;
    }

    public boolean update(Konu konu) {
        this.konuRepository.update(konu);
        return true;
    }

    public boolean delete(int id) {
        this.konuRepository.deleteById(id);
        return true;
    }
}
