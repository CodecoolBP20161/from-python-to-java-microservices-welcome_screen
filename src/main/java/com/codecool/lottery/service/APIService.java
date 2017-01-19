package com.codecool.lottery.service;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.utils.StringUtils;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class APIService {

    private static final String API_URL = "http://localhost:60000/api/create";

    private static APIService INSTANCE;

    public static APIService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new APIService();
        }
        return INSTANCE;
    }

    public String sendEmail(String email, String winner) throws IOException, URISyntaxException {
        URIBuilder builder = new URIBuilder(API_URL);

        String emailJSON = "{\"to\":\""+email+"\",\n" +
                "\"from\":\"pindurpandurok.codecool@gmail.com\",\n" +
                "\"message\": \"Dear "+winner+"!\",\n" +
                "\"subject\": \"Welcome_screen Gambling Game!\",\n" +
                "\"APIKey\": \"1b0e88c8fde7477aac96ce1635306a3c\" }";

        return Request.Post(builder.build())
                .bodyString(emailJSON, APPLICATION_JSON)
                .execute()
                .returnContent()
                .asString();

    }

    private String execute(URI uri) throws IOException {
        return Request.Post(uri)
                .execute()
                .returnContent()
                .asString();
    }
}