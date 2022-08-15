package com.batuhan.credit.repository;

import com.batuhan.credit.model.entity.Credit;
import com.batuhan.credit.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {
}