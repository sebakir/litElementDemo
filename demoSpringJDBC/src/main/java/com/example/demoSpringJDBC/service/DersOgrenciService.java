package com.example.demoSpringJDBC.service;

import com.example.demoSpringJDBC.model.Ders_Ogrenci;
import com.example.demoSpringJDBC.repo.DersOgrenciRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DersOgrenciService {
    private DersOgrenciRepository dersOgrenciRepository;

    @Autowired
    public DersOgrenciService(DersOgrenciRepository dersOgrenciRepository) {
        this.dersOgrenciRepository = dersOgrenciRepository;
    }

    public List<Ders_Ogrenci> getAll() {
        return dersOgrenciRepository.getall();
    }

    public Ders_Ogrenci getById(int id) {
        return dersOgrenciRepository.getById(id);
    }

    public boolean insertJDBC(Ders_Ogrenci dersOgrenci) {
        this.dersOgrenciRepository.insert(dersOgrenci);
        return true;
    }

    public boolean update(Ders_Ogrenci dersOgrenci) {
        this.dersOgrenciRepository.update(dersOgrenci);
        return true;
    }

    public boolean delete(int id) {
        this.dersOgrenciRepository.deleteById(id);
        return true;
    }
}
