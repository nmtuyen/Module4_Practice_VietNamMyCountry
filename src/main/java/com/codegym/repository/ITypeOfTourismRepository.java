package com.codegym.repository;

import com.codegym.model.TouristArea;
import com.codegym.model.TypesOfTourism;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ITypeOfTourismRepository extends PagingAndSortingRepository<TypesOfTourism,Long> {
}
