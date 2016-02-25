package com.test.car.app;

import com.spotify.apollo.Environment;
import com.spotify.apollo.httpservice.HttpService;
import com.spotify.apollo.httpservice.LoadingException;
import com.test.car.CarResource;

public class App {

    public static void main(String[] args) throws LoadingException {
        HttpService.boot(App::init, "my-app", args);
    }

    /**
     * Init the resources
     * 
     * @param environment
     */
    static void init(Environment environment) {
        new CarResource().register(environment);
    }
}
