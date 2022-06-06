package com.atlas.skull.BaseSkullRepository;

import com.atlas.skull.BaseSkullModel.Tenants;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantsRepository extends JpaRepository<Tenants, Long> {

    Tenants findByDsKey(String dsKey);
}
