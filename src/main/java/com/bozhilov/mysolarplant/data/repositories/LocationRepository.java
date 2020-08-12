package com.bozhilov.mysolarplant.data.repositories;

import com.bozhilov.mysolarplant.data.models.other.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, String> {
}
