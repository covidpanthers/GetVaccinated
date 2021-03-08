# GetVaccinated

## Running
An IDE can be used to run the application.
Alternatively, run the following Maven commands to start the server:
```
mvn compile
mvn exec:java
```

The server will be running on port 5000.

## Testing

### Unit

Unit tests can be exclusively targeted in the IDE by excluding tests
tagged as integration. For example, using the tag pattern "!integration".

The tests can also be run via the following Maven command:
```
mvn test
```

### Integration

Integration tests require DynamoDB. A local instance of DynamoDB can
be created via Docker with the following command:
```
docker run --rm -p 8000:8000 amazon/dynamodb-local
```

Run them in your IDE by targeting "integration" tagged tests.
Alternatively, run the following Maven command to run unit & integration
tests:
```
mvn verify
```
