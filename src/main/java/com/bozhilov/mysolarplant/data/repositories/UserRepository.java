package com.bozhilov.mysolarplant.data.repositories;

import com.bozhilov.mysolarplant.data.models.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
