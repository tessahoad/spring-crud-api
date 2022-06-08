# Spring CRUD API

## Running the project locally

### Prerequisites

Docker: ([Installing docker](https://docs.docker.com/desktop/mac/install/))
If above does not include docker-compose (check by running `$ which docker-compose` in terminal), then: 
```sh
  brew install docker-compose
```

### Running

From the project root, we need to a database running our application can connect to. Change to the docker directory and run:

```sh
  cd docker
  docker-compose up -d
```

Then we have a choice either:
1. Going back to root directory:
```sh
  cd ..
  gradle run
```

OR

2. Hit play button in intellij

When you are done, stop the database container from running:

```shell
  cd docker
  docker-compose down
```

### Interacting with the API

#### Using the makefile

From the project root, run:

```shell
  make
```
to list some convenient commands to allow you to interact with the api.

#### Call API using your favourite client

However you usually call an API, you can do that here, e.g. curl:

```shell
  curl -s -X GET 'http://localhost:8080/api/books' --header 'Content-Type: application/json'
```

## Integration Tests

### Integration test setup

For the integration tests, the database is set up using docker-compose with a given schema. The database schema is located in the `docker` folder ([link](docker/sql/V1__init.sql)).

## Errors running integration tests

### Generic errors

If you are having issues with running the integration tests on docker you can try forcibly removing all docker instances
by running the following command:

```sh
    docker ps -q -a | xargs docker rm -f
```

`docker ps -q -a` retrieves the container ids of all running instances, `xargs` iterates
over all the instance ids and calls `docker rm -f` on each which force removes them.

### Docker configuration on OSX

The Docker Desktop application needs particular configuration for integration tests to run. Note that this
is just a difficulty with OSX -- it doesn't affect Jenkins or production systems.

To configure Docker Desktop in a way that is compatible with the tests:

- Open Docker Desktop
- Click on the cog wheel to go to settings
- Click on "General" to go to the general settings tab
    - Untick "Use gRPC FUSE for file sharing" to tell Docker to use the legacy OSX file sharing system
    - Untick "Use Docker Compose V2" to revert to the old Docker Compose support
- Click on "Apply & Restart" to restart Docker

### Can not connect to Ryuk

If integration tests fail with the message `"Can not connect to Ryuk"` then this is a known incompatibility between
`testcontainers` and recent versions of Docker on OSX. Make sure that the OSX configuration above has been done.
This does not affect Jenkins or production systems; it's just an incompatibility with OSX.