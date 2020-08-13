package com.bozhilov.mysolarplant.data.repositories;

import com.bozhilov.mysolarplant.data.models.users.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileRepository  extends JpaRepository<UserProfile, String> {
    @Query("select e from UserProfile e where e.user.username=:username")
    Optional<UserProfile> findByUsername(@Param("username")String username);
}
