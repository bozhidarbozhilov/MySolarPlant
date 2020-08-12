package com.bozhilov.mysolarplant.data.repositories;

import com.bozhilov.mysolarplant.data.models.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query("select e from User e where e.username=:username")
    User findByUsername(@Param("username")String username);
}
