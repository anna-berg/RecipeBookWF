package com.berg.repositary;

import com.berg.entity.GroupDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupDayRepository extends JpaRepository<GroupDay, Long> {

}
