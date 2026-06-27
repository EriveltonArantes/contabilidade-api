package com.contabilidadeapi.repository;

import com.contabilidadeapi.model.PlanoConta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanoContaRepository extends JpaRepository<PlanoConta, Long> {
}
