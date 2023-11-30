# spring-modulith-externalization-demo

Testing success and failure scenarios of Spring Modulith's [Externalization](https://docs.spring.io/spring-modulith/reference/events.html#externalization)

## Results

There are 3 endpoints, one for each scenario:
1. `GET /listener`
    - With local listener setup.
    - Expectation: listener executed, entry in `event_publication`
        - Success case: `completion_date` is set
        - Failure case: `completion_date` is not set, can be retried
    - Result: works as expected
1. `GET /noListener`
    - With no listener
    - Expectation: no entry in `event_publication`
    - Result: works as expected
1. `GET /externalized`
    - With no listener, but `@Externalized` set to publish to SQS
    - Expectation: entry in `event_publication`,
        - Success case: `completion_date` is set
        - Failure case: `completion_date` is not set, can be retried
    - Result: works as expected with version `1.2.0-SNAPSHOT` due to a [bug](https://github.com/spring-projects/spring-modulith/issues/395) (1.2.0 not released yet)

## Do it yourself

### Setup

```shell
# Start PostgreSQL and SQS Queue
$ docker-compose up

# Start application (another terminal)
$ ./gradlew bootRun
```

## Trigger Events

Run the following commands to trigger each scenario. For failure scenario, see the  `TODO`s in code.

```shell
$ curl http://localhost:8080/listener
$ curl http://localhost:8080/noListener
$ curl http://localhost:8080/externalized
```

### Checking DB

```shell
$ psql --host=localhost --username=local --dbname=postgres
# password: local

## Fetching events
select id, listener_id, event_type, completion_date from event_publication;
```
