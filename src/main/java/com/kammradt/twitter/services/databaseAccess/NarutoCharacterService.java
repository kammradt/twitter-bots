package com.kammradt.twitter.services.databaseAccess;

import com.kammradt.twitter.domain.naruto.NarutoCharacter;
import com.kammradt.twitter.exception.NotFoundException;
import com.kammradt.twitter.repository.NarutoCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NarutoCharacterService {

    @Autowired
    NarutoCharacterRepository narutoCharacterRepository;

    public NarutoCharacter findById(Long id) {
        Optional<NarutoCharacter> result = narutoCharacterRepository.findById(id);
        return result.orElseThrow(() -> new NotFoundException("There are no Character with this ID"));
    }

    public Long count() {
        return narutoCharacterRepository.count();
    }
}
