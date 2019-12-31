package com.kammradt.twitter.services.databaseAccess;

import com.kammradt.twitter.domain.naruto.NarutoCharacter;
import com.kammradt.twitter.exception.NotFoundException;
import com.kammradt.twitter.repository.NarutoCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.kammradt.twitter.utils.RandomUtils.getRandomNumber;

@Service
public class NarutoCharacterService {

    @Autowired
    NarutoCharacterRepository narutoCharacterRepository;

    public NarutoCharacter findById(Long id) {
        Optional<NarutoCharacter> result = narutoCharacterRepository.findById(id);
        return result.orElseThrow(() -> new NotFoundException("There are no Character with this ID"));
    }

    public NarutoCharacter getRandomNarutoCharacter() {
        int randomId = getRandomNumber(count() + 1);
        return findById((long) randomId);
    }

    public Long count() {
        return narutoCharacterRepository.count();
    }

    public NarutoCharacter save(NarutoCharacter narutoCharacter) {
        return narutoCharacterRepository.save(narutoCharacter);
    }

}
