# volcano-campsite

volcano-campsite API is the microservice restful API for volcano campsite reservation.

### Requirement

Please [click link](doc/requirement.md) for detail of business requirement.

### Prerequisites

- JDK 11

- Maven 3.8.1+

- SpringBoot

### API design:

#### Backend repository Design

The volcano-campsite API uses H2 database as backend repository to save user and reservation information. As the API is designed to handle one single campsite, the database structure is simple:


| Table   | Comments        |
| --------|---------------|
| client  | Client information including client name and client email |
| reservation  | Reservation detail information. This is the table hold book of records for reservation |
| reserved  | Reserved date. This table is used to handle concurrent requests  |
  

By default, when the API starts, h2 will start in-memory database testdb and create tables listed above.

```yaml
  datasource:
    url: jdbc:h2:mem:testdb
    username: sa
    password:
    driverClassName: org.h2.Driver
```
The data in the database will disappear after API shutdown. In case if we need keep the data, change the url to use file base h2 database:

    url: jdbc:h2:~/testdb

ER-diagram:

![ERD](doc/ER_diagram.png)
  

#### API Endpoints Design

- Get: /api/campsite           -- List available time for the campsite


- POST: /api/campsite                  --campsite reservation


- Put:  /api/campsite/{orderId}        -- Change reservation by Id and new reservation info


- Delete: /api/campsite/{orderId}      -- Delete reservation by Id

- Get: /api/campsite/{orderId}      -- Get reservation by Id

For the detail, please refer to the openapi spec [here](src/main/resources/openapi.yaml).


For detail work sequence, please refer [here](doc/workflow.md) .

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

From the API root folder, run docker command:
```yaml
docker build -t volcano/campsite .
docker run -p 8080:8080 volcano/campsite
```
  

### API  verification

Swagger ui:  http://localhost:8080/swagger-ui.html

API health check:   http://localhost:8080/actuator/health
 
Postman could be used to send https request to test the API:

![postman](doc/test.png)


For detailed test cases, please refer the [test cases](doc/test_cases.md) document.

### Performance 


Jmeter could be used to verify the API performance. 

From jmeter, open the jmeter file: ./jmeter/campsite-get.jmx. The test plan set 10 concurrent threads to access the GET campsite endpoint (http://localhost:8080/api/campsite?startDate=2021-11-10&endDate=2021-11-18).

For performance result detail, please refer to [performance](doc/performance.md) document.

---
### Appendix

#### Error Codes:

- ERR10000      System error code, http code 500
- ERR10001      Backend Data process error, http code 500
- ERR10002      Data not found error,  http code 400

- ERR20001      Invalid  data range from input,  http code 422
- ERR20002      Invalid  data input,  http code 422
- ERR20003      Data over limit error,  http code 422