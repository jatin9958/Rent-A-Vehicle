# Rent-A-Vehicle

Rent-A-Vehicle is a vehicle rental service API that allows users to log in, add vehicles, view all vehicles, and manage vehicle data. The application implements role-based access controls with "USER" and "ADMIN" roles.

Endpoints
1. Login
URL: /v1/login
Method: POST
Description: Authenticate a user using their username and password.

Request Headers:
Content-Type: application/json
Request Body:
json
Copy code
{
  "username": "tanvi",
  "password": "tanvi@9958"
}
Example cURL:
bash
Copy code
curl --location 'http://localhost:8060/v1/login' \
--header 'Content-Type: application/json' \
--data-raw '{
      "username" : "tanvi", 
      "password": "tanvi@9958"
}'
2. Add Vehicle
URL: /v1/addVehicle
Method: POST
Description: Add a new vehicle to the system. Accessible only by users with the "ADMIN" role.

Request Headers:
Content-Type: application/json
Authorization: Bearer <JWT_TOKEN> (Replace <JWT_TOKEN> with the actual token obtained after login)
Request Body:
json
Copy code
{
  "type": "car",
  "model": "Hundai venue 2018",
  "vehicleNo": "MHAhwHE7",
  "price": "1200",
  "location": "b-1287 sector-1235 saket delhi-192837981",
  "availability": true
}
Example cURL:
bash
Copy code
curl --location 'http://localhost:8060/v1/addVehicle' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer <JWT_TOKEN>' \
--data-raw '{
      "type": "car",
      "model": "Hundai venue 2018",
      "vehicleNo": "MHAhwHE7",
      "price": "1200",
      "location": "b-1287 sector-1235 saket delhi-192837981",
      "availability": true
}'
3. Logout
URL: /v1/logout
Method: POST
Description: Blacklist the current JWT token, effectively logging out the user.

Request Headers:
Authorization: Bearer <JWT_TOKEN> (Replace <JWT_TOKEN> with the actual token)
Example cURL:
bash
Copy code
curl --location 'http://localhost:8060/v1/logout' \
--header 'Authorization: Bearer <JWT_TOKEN>'
4. Get All Vehicles
URL: /v1/getAllVehicle
Method: GET
Description: Retrieve a list of all available vehicles. Accessible to both "USER" and "ADMIN" roles.

Request Headers:
Authorization: Bearer <JWT_TOKEN> (Replace <JWT_TOKEN> with the actual token)
5. Get Vehicles by Admin
URL: /v1/getVehiclesByAdmin
Method: GET
Description: Retrieve a list of vehicles added by the currently logged-in admin.

Request Headers:
Authorization: Bearer <JWT_TOKEN> (Replace <JWT_TOKEN> with the actual token)
6. Make Changes to Current Vehicle
URL: /v1/currentVehicleChanges
Method: POST
Description: Modify details of an existing vehicle. Accessible only by "ADMIN" role.

Request Headers:
Content-Type: application/json
Authorization: Bearer <JWT_TOKEN> (Replace <JWT_TOKEN> with the actual token)
Request Body:
json
Copy code
{
  "type": "car",
  "model": "Updated Model 2020",
  "vehicleNo": "MHAhwHE7",
  "price": "1300",
  "location": "Updated Location",
  "availability": false
}
Roles and Permissions
USER:
Can view all vehicles.
ADMIN:
Can add, view, and manage vehicles.
Security
This application uses JWT for authentication and role-based access control. Tokens are blacklisted upon logout to ensure secure session termination.

Let me know if you'd like to make further adjustments or add more details!
