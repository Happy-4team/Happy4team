package com.app.milestone.repository;

import com.app.milestone.domain.PeopleDTO;
import com.querydsl.core.Tuple;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PeopleCustomRepository {
    public PeopleDTO findInfoById(Long userId);
    public List<Tuple> sortByMoneyCash();
    public List<Tuple> sortByVisitRank();
    public List<Tuple> sortBytalentRank();

    public List<PeopleDTO> findByPeopleOnly();
    public List<PeopleDTO> findByPeopleOnlyAsc();
}
