package com.evoke.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.evoke.employee.entities.HrmsHistory;

@Repository
public interface HrmsHistoryRepository extends JpaRepository<HrmsHistory, Integer> {

}
