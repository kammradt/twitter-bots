package com.kammradt.twitter.bots.meangirls;

import com.kammradt.twitter.commom.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.kammradt.twitter.commom.utils.RandomUtils.getRandomNumber;

@AllArgsConstructor
@Service
public class MeanGirlService {

    MeanGirlRepository meanGirlsRepository;

    public MeanGirl findById(Long id) {
        Optional<MeanGirl> result = meanGirlsRepository.findById(id);
        return result.orElseThrow(() -> new NotFoundException("There are no MeanGirl with this ID"));
    }

    public MeanGirl getRandomMeanGirl() {
        int randomId = getRandomNumber(count() + 1);
        return findById((long) randomId);
    }

    public Long count() {
        return meanGirlsRepository.count();
    }

    public MeanGirl save(MeanGirl meanGirl) {
        return meanGirlsRepository.save(meanGirl);
    }
}
