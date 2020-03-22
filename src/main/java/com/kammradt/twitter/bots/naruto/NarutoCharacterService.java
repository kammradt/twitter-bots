package com.kammradt.twitter.bots.naruto;

import com.kammradt.twitter.commom.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.kammradt.twitter.commom.utils.RandomUtils.getRandomNumber;

@AllArgsConstructor
@Service
public class NarutoCharacterService {

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
