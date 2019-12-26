package com.kammradt.twitter.repository;

import com.kammradt.twitter.domain.NarutoCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NarutoCharacterRepository extends JpaRepository<NarutoCharacter, Long> {
}