package com.lucasmoraist.balancea.repository;

import com.lucasmoraist.balancea.domain.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepository extends JpaRepository<Income, Long> {
}
