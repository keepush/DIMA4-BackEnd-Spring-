package com.kdigital.score.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdigital.score.entity.ScoreEntity;

public interface ScoreRepository extends JpaRepository<ScoreEntity, String> {

}
