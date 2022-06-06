package com.atlas.skull.BaseSkullRepository;

import com.atlas.skull.BaseSkullModel.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, Long> {
}
