package com.kammradt.twitter.bots.naruto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NarutoCharacterRepository extends JpaRepository<NarutoCharacter, Long> {
}
