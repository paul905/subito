## Prerequisites
**Docker** installed

## Building docker image
Navigate to the root of the project and run:
` docker build -t mytest .`

## How to start the application

The project provides scripts to: build the application, run tests and start the application. 

`docker run -v $(pwd):/mnt -p 9090:9090 -w /mnt mytest ./scripts/build.sh`

`docker run -v $(pwd):/mnt -p 9090:9090 -w /mnt mytest ./scripts/tests.sh`

`docker run -v $(pwd):/mnt -p 9090:9090 -w /mnt mytest ./scripts/run.sh`

Note: The dockerfile provided does not handle sharing dependencies between docker run commands.


