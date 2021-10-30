# volcano-campsite


### Requirement

Please [click link](doc/requirement.md) for detail of business requirement

### Prerequisites

- JDK 11

- Maven 3.8.1+

- SpringBoot


### Endpoints

- Get: /api/campsite           -- List available time for the campsite


- POST: /api/campsite                  --campsite reservation


- Put:  /api/campsite/{orderId}        -- Change reservation


- Delete: /api/campsite/{orderId}      -- Delete reservation

For the detail, please refer to the openapi spec [here](src/main/resources/openapi.yaml)

### Local build

There are several ways to start API:

- From IDE, run the /src/main/java/com/mservicetech/campsite/CampsiteApplication.java

- Start from command line:

```text
cd /workspace/volcano-campsite
mvn clean install
java -jar target/volcano-0.0.1-SNAPSHOT.jar
```

- Start from local docker environment
//TODO
  

### API  verification

Swagger ui:  http://localhost:8080/swagger-ui.html

API health check:   http://localhost:8080/actuator/health

We can use postman to send https request to test the API:

![postman](doc/test.png)


For detail test cases, please refer the [test cases](doc/test_cases.md) document