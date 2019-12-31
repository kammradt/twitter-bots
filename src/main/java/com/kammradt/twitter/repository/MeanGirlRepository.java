package com.kammradt.twitter.repository;

import com.kammradt.twitter.domain.girls.MeanGirl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeanGirlRepository extends JpaRepository<MeanGirl, Long> {
}
