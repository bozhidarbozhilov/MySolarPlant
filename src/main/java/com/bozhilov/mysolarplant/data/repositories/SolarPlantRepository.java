package com.bozhilov.mysolarplant.data.repositories;

import com.bozhilov.mysolarplant.data.models.plant.SolarPlant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolarPlantRepository extends JpaRepository<SolarPlant, String> {
    @Query("select e from SolarPlant e where e.user.username=:username")
    List<SolarPlant> findAllByUsername(@Param("username")String username);
}
