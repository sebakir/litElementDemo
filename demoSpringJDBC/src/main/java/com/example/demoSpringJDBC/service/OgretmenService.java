package com.example.demoSpringJDBC.service;

import com.example.demoSpringJDBC.model.Ogretmen;
import com.example.demoSpringJDBC.repo.OgretmenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OgretmenService {
    private OgretmenRepository ogretmenRepository;

    @Autowired
    public OgretmenService(OgretmenRepository ogretmenRepository) {
        this.ogretmenRepository = ogretmenRepository;
    }

    public List<Ogretmen> getAll() {
        return ogretmenRepository.getAll();
    }

    public Ogretmen getById(int id) {
        return ogretmenRepository.getById(id);
    }

    public boolean insertJDBC(Ogretmen ogretmen) {
        this.ogretmenRepository.insertJDBC(ogretmen);
        return true;
    }

    public boolean update(Ogretmen ogretmen) {
        this.ogretmenRepository.update(ogretmen);
        return true;
    }

    public boolean delete(int id) {
        this.ogretmenRepository.deleteById(id);
        return true;
    }
}
