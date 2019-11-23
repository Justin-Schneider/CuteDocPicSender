package com.aws.lambda.commons;

import com.aws.lambda.models.RedditResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import lombok.extern.java.Log;

import java.io.IOException;

@Log
@UtilityClass
public class JsonToObject {

    public static RedditResponse redditResponse(String response){
        RedditResponse redditResponse = null;
        try {
            if (response != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                redditResponse = objectMapper.readValue(response, RedditResponse.class);
            }
        } catch (IOException e) {
            log.severe("Could not convert reddit response to object\n" + e.getMessage());
        }
        return redditResponse;
    }
}
