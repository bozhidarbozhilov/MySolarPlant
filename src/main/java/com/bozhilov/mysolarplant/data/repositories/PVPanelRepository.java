package com.bozhilov.mysolarplant.data.repositories;

import com.bozhilov.mysolarplant.data.models.plant.PVPanel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PVPanelRepository extends JpaRepository<PVPanel, String> {
}
