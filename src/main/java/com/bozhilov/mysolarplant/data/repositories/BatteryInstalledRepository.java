package com.bozhilov.mysolarplant.data.repositories;

import com.bozhilov.mysolarplant.data.models.plant.BatteryInstalled;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatteryInstalledRepository extends JpaRepository<BatteryInstalled, String> {
}
