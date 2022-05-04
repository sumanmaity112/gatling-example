package com.suman.example.gatling.utils;

import io.gatling.javaapi.core.FeederBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static io.gatling.javaapi.core.CoreDsl.listFeeder;

public class Util {
  public static FeederBuilder<Object> getIdFeeder() {
    final int limit = getLimit();
    final int durationInSeconds = getDurationInSeconds();
    final int usersPerSec = getUsersPerSec(limit, durationInSeconds);
    final List<Map<String, Object>> ids =
        IntStream.rangeClosed(1, usersPerSec * durationInSeconds)
            .mapToObj(id -> Collections.singletonMap("id", (Object) id))
            .collect(Collectors.toList());

    return listFeeder(ids);
  }

  public static int getUsersPerSec(int limit, double durationInSeconds) {
    return (int) Math.ceil(limit / durationInSeconds);
  }

  public static int getUsersPerSec() {

    return getUsersPerSec(getLimit(), getDurationInSeconds());
  }

  public static int getLimit() {
    return Integer.parseInt(System.getenv("LIMIT"));
  }

  public static int getDurationInSeconds() {
    return Integer.parseInt(System.getenv("DURATION_IN_SECONDS"));
  }
}
