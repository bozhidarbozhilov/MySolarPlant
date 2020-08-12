package com.bozhilov.mysolarplant.data.repositories;

import com.bozhilov.mysolarplant.data.models.plant.SolarUnit;
import com.bozhilov.mysolarplant.data.models.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolarUnitRepository extends JpaRepository<SolarUnit, String> {
    @Query("select e from SolarUnit e where e.user.username=:username")
    List<SolarUnit> findAllByUsername(@Param("username")String username);
}
