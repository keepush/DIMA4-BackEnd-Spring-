package com.kdigital.diary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdigital.diary.entity.DiaryEntity;

public interface DiaryRepository extends JpaRepository<DiaryEntity, Integer> {

}
