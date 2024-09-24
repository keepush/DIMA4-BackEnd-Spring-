package com.kdigital.Test3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdigital.Test3.entity.FitnessEntity;

public interface FitnessRepository extends JpaRepository<FitnessEntity,Integer> {

}
