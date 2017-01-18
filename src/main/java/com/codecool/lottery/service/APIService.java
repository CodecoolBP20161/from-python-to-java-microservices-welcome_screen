package com.codecool.lottery.service;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.utils.StringUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class APIService {

    private static final String API_URL = "localhost:60000/api/create";

    private static APIService INSTANCE;

    public static APIService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new APIService();
        }
        return INSTANCE;
    }

    public String sentenceYoda(String sentence) throws IOException, URISyntaxException {
        URIBuilder builder = new URIBuilder(API_URL);
        builder.addParameter(GreetingsAPIController.YODA_SENTENCE_PARAM_KEY, sentence );
        builder.addParameter("mashape-key", "CcilQQxkvcmshXRgzsRYUPrrgN9wp1zBZfKjsnjKI4Rtb5BaMW");
        return execute(builder.build());

    }

    private String execute(URI uri) throws IOException {
        return Request.Get(uri)
                .execute()
                .returnContent()
                .asString();
    }
}