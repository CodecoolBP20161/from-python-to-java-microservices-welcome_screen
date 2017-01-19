package com.codecool.lottery.controller;

import com.codecool.lottery.service.APIService;
import org.json.JSONException;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by patrik on 2017.01.03..
 */
public class LotteryAPIController {
     private Random generator;

    public LotteryAPIController(APIService apiService) {
        generator = new Random();

    }

    public void setGenerator(Random generator) {
        this.generator = generator;
    }

    public String getWinner(Request req, Response res) throws JSONException, IOException, URISyntaxException {
        JSONObject object = new JSONObject(req.body());
        return getRandomFromJson(object);
    }

    public String getRandomFromJson(JSONObject object) throws JSONException, IOException, URISyntaxException {
        Iterator<String> keysItr = object.keys();
        Integer randomValue =  generator.nextInt(object.length());
        Integer count = 0;
        String key = "";
        while(keysItr.hasNext()){
            key = keysItr.next();
            if(count == randomValue){
                break;
            }
            count += 1;
        }
        String value = object.getString(key);
        APIService apiService = new APIService();
        apiService.sendEmail(key, value);
        return key;
    }

    public String status(Request request, Response response) {
        return "ok";
    }
}
