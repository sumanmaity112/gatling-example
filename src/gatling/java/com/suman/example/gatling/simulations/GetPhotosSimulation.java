package com.suman.example.gatling.simulations;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.time.Duration;

import static com.suman.example.gatling.utils.Util.getDurationInSeconds;
import static com.suman.example.gatling.utils.Util.getIdFeeder;
import static com.suman.example.gatling.utils.Util.getUsersPerSec;
import static io.gatling.javaapi.core.CoreDsl.constantUsersPerSec;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;

public class GetPhotosSimulation extends Simulation {
  {
    final HttpProtocolBuilder httpProtocol =
        http.baseUrl("https://jsonplaceholder.typicode.com");

    final ScenarioBuilder scn =
        scenario("Get Photo")
            .feed(getIdFeeder().shuffle())
            .exec(http("get_photo").get("/photos/#{id}"));

    setUp(
        scn.injectOpen(
            constantUsersPerSec(getUsersPerSec()).during(Duration.ofSeconds(getDurationInSeconds()))))
        .protocols(httpProtocol);
  }
}
