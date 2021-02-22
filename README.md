# vehicle_management_system
vehicle management system will use to manage information about vehicles based on different parameters.

# Project structure
  * Backend - Spring boot framework used to build the backend service and for content deliver used inbuilt tomcat apache
   server which us running on default port 8080.
  * Database - Use h2 memory database used for this project

# How to use this?
  - Use has need to clone this repo at host machine and start tomcat server.

# Features
    This service has provide three operation. Below is the list of service and corresponding the definition.
    
|  HTTP METHOD | END POINT   |  DESCRIPTION |
|---|---|---|
|  POST | http://localhost:8080/upload_csv/{dealerId}/  | This end point will upload csv data into database  |
|  POST | http://localhost:8080/vehicle_listings/{dealerId}/  | This end point will persist vehicle json data into database |
|  GET | http://localhost:8080/searchVehicle  | This endpoint will serach vehicle based on specified parameters |
|  GET | http://127.0.01:9001/actuator/health  | This will provide service heath status. |

# Swagger documentation: 
 User can open below link for more detail information about services and request parameters.
 http://localhost:8080/swagger-ui.htm
 
 ### Maintainer 
 Raj K Upadhyay

