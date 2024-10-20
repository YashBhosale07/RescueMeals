package com.rescueMeal.dao;

import com.rescueMeal.model.NGO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NgoDAO extends JpaRepository<NGO,Long> {
}
