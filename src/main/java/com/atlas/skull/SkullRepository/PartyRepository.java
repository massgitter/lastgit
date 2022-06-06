package com.atlas.skull.SkullRepository;

import com.atlas.skull.SkullModel.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PartyRepository extends JpaRepository<Party, Long> {
}
