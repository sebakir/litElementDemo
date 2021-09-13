package com.example.demoSpringJDBC.service;

import com.example.demoSpringJDBC.model.Ders;
import com.example.demoSpringJDBC.repo.DersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DersService {
    private DersRepository dersRepository;

    @Autowired
    public DersService(DersRepository dersRepository) {
        this.dersRepository = dersRepository;
    }

    public List<Ders> getAll() {
        return dersRepository.getall();
    }

    public Ders getById(int id) {
        return dersRepository.getById(id);
    }

    public boolean insertJDBC(Ders ders) {
        this.dersRepository.insert(ders);
        return true;
    }

    public boolean update(Ders ders) {
        this.dersRepository.update(ders);
        return true;
    }

    public boolean delete(int id) {
        this.dersRepository.deleteById(id);
        return true;
    }
}

