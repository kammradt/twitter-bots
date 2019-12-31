package com.kammradt.twitter.services.databaseAccess;

import com.kammradt.twitter.domain.girls.MeanGirl;
import com.kammradt.twitter.exception.NotFoundException;
import com.kammradt.twitter.repository.MeanGirlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.kammradt.twitter.utils.RandomUtils.getRandomNumber;

@Service
public class MeanGirlService {

    @Autowired MeanGirlRepository meanGirlsRepository;

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
