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
                "\"message\": \"Dear "+winner+"! You win an Iphone 7! For details please call us on " +
                "0123456789 or answer this mail" +
                "Best regards," +
                "Welcome_screen Team!\",\n" +
                "\"subject\": \"Welcome_screen Gambling Game!\",\n" +
                "\"APIKey\": \"f44c716bbecf4d998400dfb95db4fbb9\" }";

        return Request.Post(builder.build())
                .bodyString(emailJSON, APPLICATION_JSON)
                .execute()
                .returnContent()
                .asString();

    }
}