package com.example.company_management_app.repository;

import com.example.company_management_app.entity.SellingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellingsRepository extends JpaRepository<SellingsEntity,Long> {
}
