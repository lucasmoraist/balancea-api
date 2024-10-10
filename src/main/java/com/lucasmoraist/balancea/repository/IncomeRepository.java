package com.lucasmoraist.balancea.repository;

import com.lucasmoraist.balancea.domain.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
}
