package com.kammradt.twitter.bots.meangirls;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeanGirlRepository extends JpaRepository<MeanGirl, Long> {
}
