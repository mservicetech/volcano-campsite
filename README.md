# volcano-campsite


### Requirement

Please [click link](doc/requirement.md) for detail of business requirement

### Endpoints

- Get: /api/campsite 
   
  List available time for the campsite 
  
  optional query parameters: startDate, endDate  
   
- POST: /api/campsite  

  campsite reservation 
  
- Put:  /api/campsite/{orderId}

- Delate: /api/campsite/{orderId}   


### Local build and verify

swagger ui:  http://localhost:8080/swagger-ui.html

API health check:   http://localhost:8080/actuator/health