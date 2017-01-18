package com.codecool.lottery.controller;

import com.codecool.lottery.service.APIService;
import org.json.JSONException;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by patrik on 2017.01.03..
 */
public class LotteryAPIController {

    public LotteryAPIController(APIService apiService) {

    }

    public String getWinner(Request req, Response res) throws JSONException {

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
        return key;
    }

    //Request body JSON format: { "to":"email address", "from":"email adress", "message": "It's working!", "subject": "Newest", "APIKey": "APIKey(you get it from us)" }

    public String status(Request request, Response response) {
        return "ok";
    }
}
