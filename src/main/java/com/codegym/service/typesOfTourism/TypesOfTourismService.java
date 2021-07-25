package com.codegym.service.typesOfTourism;

import com.codegym.model.TypesOfTourism;
import com.codegym.repository.ITypeOfTourismRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class TypesOfTourismService implements ITypesOfTourismService{
    @Autowired
    private ITypeOfTourismRepository iTypeOfTourismRepository;

    @Override
    public Iterable<TypesOfTourism> findAll() {
        return iTypeOfTourismRepository.findAll();
    }

    @Override
    public Optional<TypesOfTourism> findById(Long id) {
        return iTypeOfTourismRepository.findById(id);
    }

    @Override
    public void save(TypesOfTourism typesOfTourism) {
        iTypeOfTourismRepository.save(typesOfTourism);
    }

    @Override
    public void delete(Long id) {
        iTypeOfTourismRepository.deleteById(id);
    }
}
