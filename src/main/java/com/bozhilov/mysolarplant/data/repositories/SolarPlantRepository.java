package com.bozhilov.mysolarplant.data.repositories;

import com.bozhilov.mysolarplant.data.models.plant.SolarPlant;
import com.bozhilov.mysolarplant.data.models.plant.SolarUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolarPlantRepository extends JpaRepository<SolarPlant, String> {
}
