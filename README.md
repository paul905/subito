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

## Notes

For semplicity I've not provided an api to add a product, so the only products available are the ones
loaded during [startup](https://github.com/paul905/subito/blob/master/src/main/java/subito/codingtest/purchase_cart_service/PurchaseCartServiceApplication.java).

Also, the dockerfile provided does not handle sharing dependencies between docker run commands.





