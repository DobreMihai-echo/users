package com.myplanet.users.repository;

import com.myplanet.users.entity.CarbonFootprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarbonFootprintRepository extends JpaRepository<CarbonFootprint,Long> {
}
