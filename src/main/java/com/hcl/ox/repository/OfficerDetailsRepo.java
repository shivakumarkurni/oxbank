package com.hcl.ox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.ox.entity.Officer;

@Repository
public interface OfficerDetailsRepo extends JpaRepository<Officer, Long>{

}
