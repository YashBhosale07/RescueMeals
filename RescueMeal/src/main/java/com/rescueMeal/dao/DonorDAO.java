package com.rescueMeal.dao;

import com.rescueMeal.model.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonorDAO extends JpaRepository<Donor,Long> {
}
