package com.atlas.skull.BaseSkullRepository;

import com.atlas.skull.BaseSkullModel.UsersData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDataRepository extends JpaRepository<UsersData, Long> {
}
