package com.codecool.lottery;

import com.codecool.lottery.controller.LotteryAPIController;
import com.codecool.lottery.service.APIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;

import static spark.Spark.*;

public class LotteryService {
    private static final Logger logger = LoggerFactory.getLogger(LotteryService.class);

    private LotteryAPIController controller;

    public static void main(String[] args) {
        logger.debug("Starting " + LotteryService.class.getName() + "...");

        setup(args);

        LotteryService application = new LotteryService();

        application.controller = new LotteryAPIController(APIService.getInstance());

        port(60001);
        // --- MAPPINGS ---
        get("/status", application.controller::status);
        post("/api/random", application.controller::getWinner);


        // --- EXCEPTION HANDLING ---
        exception(URISyntaxException.class, (exception, request, response) -> {
            response.status(500);
            response.body(String.format("URI building error, maybe wrong format? : %s", exception.getMessage()));
            logger.error("Error while processing request", exception);
        });

        exception(Exception.class, (exception, request, response) -> {
            response.status(500);
            response.body(String.format("Unexpected error occurred: %s", exception.getMessage()));
            logger.error("Error while processing request", exception);
        });
    }

    /**
     * Setting up port
     * @param args - app args
     */
    private static void setup(String[] args){
        if(args == null || args.length == 0){
            logger.error("Port must be given as the first argument.");
            System.exit(-1);
        }

        try {
            port(Integer.parseInt(args[0]));
        } catch (NumberFormatException e){
            logger.error("Invalid port given '{}', it should be number.", args[0]);
            System.exit(-1);
        }
    }
}
