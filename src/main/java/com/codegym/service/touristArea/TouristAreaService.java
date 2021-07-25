package com.codegym.service.touristArea;

import com.codegym.model.TouristArea;
import com.codegym.repository.ITouristAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TouristAreaService implements ITouristAreaService{
    @Autowired
    private ITouristAreaRepository iTouristAreaRepository;

    @Override
    public Iterable<TouristArea> findAll() {
        return iTouristAreaRepository.findAll();
    }

    @Override
    public Optional<TouristArea> findById(Long id) {
        return iTouristAreaRepository.findById(id);
    }

    @Override
    public void save(TouristArea touristArea) {
        iTouristAreaRepository.save(touristArea);
    }

    @Override
    public void delete(Long id) {
        iTouristAreaRepository.deleteById(id);
    }
}
