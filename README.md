# Backend challenge

Welcome to Bankin's backend challenge.

You will be adding to the existing code of this project, which has already be cloned and imported into IntelliJ for you.

## Running the project

The project builds and runs in its initial state. You can run it by clicking on the "start" button in the upper-right section of the screen.

Calling the health check endpoint `curl localhost:9000/ping` should then return `HTTP 200`.

Running tests can be done by running `./sbt test` in the console, or by double clicking the `test` package and choosing `Run all tests`.

## The challenge

The BridgeClient service should be used to call the `List accounts` endpoint (see.: https://docs.bridgeapi.io/v2018-06-15/reference#list-accounts) and compute the sum of all of the User's account, rounded to the upper thousand.

The computed value should then be returned by `MyController` (hopefully its name will have changed by then).

A few unit tests would be welcome.

The API and User credentials have already been pre-filled in the service. The User authentication is also already coded.
