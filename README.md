# Gatling Example

Sample repository with [Gatling](https://gatling.io/) scenarios.

## How to build executable jar

```shell
./gradlew fatJar
```

## How to run simulations

```shell
export LIMIT=<Number of requests needs to invoke>
export DURATION_IN_SECONDS=<How long performance tests should run>
java -jar build/libs/gatling-example-1.0-SNAPSHOT-all.jar # to run all simulations sequentially
java -jar build/libs/gatling-example-1.0-SNAPSHOT-all.jar <simulation name(s)> # to run specific simulation(s)
```

#### example 1

```shell
export LIMIT=5
export DURATION_IN_SECONDS=5

java -jar build/libs/gatling-example-1.0-SNAPSHOT-all.jar
```

#### example 2

```shell
export LIMIT=5
export DURATION_IN_SECONDS=5

java -jar build/libs/gatling-example-1.0-SNAPSHOT-all.jar GetPhotosSimulation
```

#### example 3

```shell
export LIMIT=5
export DURATION_IN_SECONDS=5

java -jar build/libs/gatling-example-1.0-SNAPSHOT-all.jar GetPhotosSimulation GetTodosSimulation
```
