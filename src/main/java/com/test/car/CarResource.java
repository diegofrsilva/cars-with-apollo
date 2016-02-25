package com.test.car;

import com.spotify.apollo.Environment;
import com.spotify.apollo.RequestContext;
import com.spotify.apollo.Response;
import okio.ByteString;

import java.util.List;
import java.util.UUID;

import static com.spotify.apollo.Response.ok;
import static com.spotify.apollo.route.Route.sync;
import static com.test.car.app.GsonUtil.fromJson;
import static com.test.car.app.GsonUtil.toJson;

public class CarResource {

    public Response<ByteString> save(RequestContext context) {
        ByteString json = context.request().payload().get();
        Car savedCar = CarService.me().save(fromJson(json, Car.class));

        return ok().withPayload(toJson(savedCar)).withHeader("Content-type", "application/json");
    }

    public Response<ByteString> update(RequestContext context) {
        ByteString json = context.request().payload().get();
        CarService.me().update(fromJson(json, Car.class));
        return ok();
    }

    public Response<ByteString> delete(RequestContext context) {
        String carId = context.pathArgs().get("carId");
        CarService.me().delete(UUID.fromString(carId));
        return ok();
    }

    public Response<List<Car>> list(RequestContext context) {
        return Response.forPayload(CarService.me().list()).withHeader("Content-type", "application/json");
    }

    public void register(Environment environment) {
        environment.routingEngine().registerAutoRoute(sync("GET", "/api/car", this::list));
        environment.routingEngine().registerAutoRoute(sync("POST", "/api/car", this::save));
        environment.routingEngine().registerAutoRoute(sync("PUT", "/api/car", this::update));
        environment.routingEngine().registerAutoRoute(sync("DELETE", "/api/car/<carId>", this::delete));
    }
}
