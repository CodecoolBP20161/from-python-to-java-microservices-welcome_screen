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

    public LotteryAPIController(APIService apiService) {

    }

    public String getWinner(Request req, Response res) throws JSONException, IOException, URISyntaxException {

        JSONObject object = new JSONObject(req.body());
        Iterator<String> keysItr = object.keys();
        Random generator = new Random();
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
        APIService apiService = new APIService();
        apiService.sendEmail();
        return key;
    }

    public String status(Request request, Response response) {
        return "ok";
    }
}
