package com.bozhilov.mysolarplant.data.repositories;

import com.bozhilov.mysolarplant.data.models.plant.Inverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InverterRepository extends JpaRepository<Inverter, String> {
}
