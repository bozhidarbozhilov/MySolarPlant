package com.bozhilov.mysolarplant.data.repositories;

import com.bozhilov.mysolarplant.data.models.plant.Battery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatteryRepository extends JpaRepository<Battery, String> {
}
