package com.suman.example.gatling;

import io.gatling.app.Gatling;
import io.gatling.core.config.GatlingPropertiesBuilder;
import io.gatling.javaapi.core.Simulation;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Application {

  public static void main(String[] args) {
    final Set<String> simulations =
        findAllSimulations()
            .filter(simulation -> args.length == 0 || Arrays.stream(args).anyMatch(simulation::endsWith))
            .collect(Collectors.toSet());

    if (simulations.isEmpty()) throw new RuntimeException("Unable to find any simulation to run");

    simulations.forEach(Application::runGatlingSimulation);
  }

  private static Stream<String> findAllSimulations() {
    final String packageName = Application.class.getPackageName();
    System.out.printf("Finding simulations in %s package%n", packageName);

    final Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
    return reflections.getSubTypesOf(Simulation.class).stream().map(Class::getName);
  }

  private static void runGatlingSimulation(String simulationFileName) {
    System.out.printf("Starting %s simulation%n", simulationFileName);
    final GatlingPropertiesBuilder gatlingPropertiesBuilder = new GatlingPropertiesBuilder();

    gatlingPropertiesBuilder.simulationClass(simulationFileName);
    gatlingPropertiesBuilder.resultsDirectory("test-reports");
    try {
      Gatling.fromMap(gatlingPropertiesBuilder.build());
    } catch (Exception exception) {
      System.err.printf(
          "Something went wrong for simulation %s %s%n", simulationFileName, exception);
    }
  }
}
