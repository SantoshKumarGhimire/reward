# Application to calculate reward earned by a customer each month and in total for a given 3 months of data. 

# Application Endpoint URL for HTTP GET Request :- localhost:8081/customer/{id}/reward
where id should be integer value example :- localhost:8081/customer/1/reward/

# Created a  GlobalExceptionHandler class by using @ControllerAdvice anotation. In which we can handle multiple exceptions,
just for the demo purpose I have implemented CustomerNotFoundException. 

# Unit Testing - Using Junit and Mockito test coverage for service is 100 % ; 
