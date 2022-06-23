package com.berg.repositary;

import com.berg.entity.DailyMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyMenuRepository extends JpaRepository<DailyMenu, Long> {

}
