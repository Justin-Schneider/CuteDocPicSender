package com.aws.lambda.commons;


import lombok.experimental.UtilityClass;
import lombok.extern.java.Log;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

@Log
@UtilityClass
public class HttpClient {

    public static String sendGetRequest(String url) {
        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try(Response response = okHttpClient.newCall(request).execute()){
            log.info("Sent get request to " + url + " and got response " + response.code() + " " + response.message());
            return response.body().string();
        } catch (IOException e) {
            log.severe("Could not send get request to " + url + "\nReceived Error" + e.getMessage());
            return null;
        }
    }
}
