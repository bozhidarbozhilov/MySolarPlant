package com.bozhilov.mysolarplant.data.repositories;

import com.bozhilov.mysolarplant.data.models.plant.ChargeController;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargeControllerRepository extends JpaRepository<ChargeController, String> {
}
