package com.bozhilov.mysolarplant.data.repositories;

import com.bozhilov.mysolarplant.data.models.users.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
}
