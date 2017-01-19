package com.codecool.lottery.controller;

import com.codecool.lottery.service.APIService;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.internal.matchers.Any;
import org.mockito.runners.MockitoJUnitRunner;
import spark.Request;

import java.util.Random;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * Created by patrik on 2017.01.03..
 */
@RunWith(MockitoJUnitRunner.class)
public class LotteryAPIControllerTest {
    APIService testService;
    LotteryAPIController testController;

    @Mock
    Random generator;


    @Before
    public void setUp() throws Exception {
        when(generator.nextInt(anyInt())).thenReturn(2);
        testService = new APIService();
        testController = new LotteryAPIController(testService);
        testController.setGenerator(generator);
    }

    @Test
    public void getWinnerReturnAString() throws Exception {
        String JSONExample = "{cxy@gh.com: tom jones, ghj@hjk: bela, hjk@hl.com: geza}";
        JSONObject testObject = new JSONObject(JSONExample);
        assertEquals("ghj@hjk", testController.getRandomFromJson(testObject));







    }



}