package com.kammradt.twitter;

import com.kammradt.twitter.domain.girls.MeanGirl;
import com.kammradt.twitter.domain.naruto.NarutoCharacter;
import com.kammradt.twitter.services.databaseAccess.MeanGirlService;
import com.kammradt.twitter.services.databaseAccess.NarutoCharacterService;
import lombok.SneakyThrows;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.file.Files;

@Component
public class DataLoader {

    @Autowired NarutoCharacterService narutoCharacterService;
    @Autowired MeanGirlService meanGirlService;
    @Value("classpath:naruto.json") Resource narutoJsonFile;
    @Value("classpath:girls.json") Resource girlsJsonFile;

    @PostConstruct
    public void loadData() {
        if (narutoCharacterService.count() == 0)
            insertNarutoCharacters();

        if (meanGirlService.count() == 0)
            insertMeanGirls();
    }

    private void insertNarutoCharacters() {
        JSONArray narutoCharacters = loadGenericResource(narutoJsonFile);

        for (int i = 0; i < narutoCharacters.length(); i++) {
            JSONObject narutoObj = narutoCharacters.getJSONObject(i);
            NarutoCharacter narutoCharacter = new NarutoCharacter();
            narutoCharacter.setName(narutoObj.getString("name"));
            narutoCharacter.setImageUrl(narutoObj.getString("imageUrl"));
            narutoCharacterService.save(narutoCharacter);
        }
    }

    private void insertMeanGirls() {
        JSONArray imageURLs = loadGenericResource(girlsJsonFile);
        for (int i = 0; i < imageURLs.length(); i++) {
            String imageURL = imageURLs.getString(i);
            MeanGirl meanGirl = new MeanGirl();
            meanGirl.setImageUrl(imageURL);
            meanGirlService.save(meanGirl);
        }
    }

    @SneakyThrows
    private JSONArray loadGenericResource(Resource resource) {
        String asString = new String(Files.readAllBytes(resource.getFile().toPath()));
        JSONObject asJson = new JSONObject(asString);
        return asJson.getJSONArray("data");
    }

}