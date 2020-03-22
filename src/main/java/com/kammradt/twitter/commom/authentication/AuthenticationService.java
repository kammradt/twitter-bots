package com.kammradt.twitter.commom.authentication;

import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@AllArgsConstructor
@Service
public class AuthenticationService {

    private final Environment env;

    public Twitter authenticate(BotNameEnum name) {
        ConfigurationBuilder cb = new ConfigurationBuilder();

        cb.setDebugEnabled(false)
                .setOAuthConsumerKey(env.getProperty("consumerKey." + name))
                .setOAuthConsumerSecret(env.getProperty("consumerSecret." + name))
                .setOAuthAccessToken(env.getProperty("accessToken." + name))
                .setOAuthAccessTokenSecret(env.getProperty("accessTokenSecret." + name));

        TwitterFactory tf = new TwitterFactory(cb.build());

        return tf.getInstance();
    }

}