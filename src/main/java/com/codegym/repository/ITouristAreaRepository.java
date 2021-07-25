package com.codegym.repository;

import com.codegym.model.TouristArea;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ITouristAreaRepository extends PagingAndSortingRepository<TouristArea, Long> {
}
