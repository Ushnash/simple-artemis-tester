# simple-artemis-tester
A simple utility to test an Artemis instance.

## How to Build
`mvn clean install`

The project uses the `maven-shade-plugin` to create an uber-jar with all the required dependencies.

### Usage
`java -jar amq-test-0.0.2-snapshot.jar brokerURL [username] [password] queuename`
