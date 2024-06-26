# user-service
This user service is built with Spring Boot and uses MySQL as the database. It runs on port 8037, and the MySQL database runs on port 3306.

## Features
The user service allows you to perform the following operations:

- Get all users
- Get a user by ID
- Create a new user
- Update an existing user
- Delete a user

## Getting Started

1. Clone the repository:
```
git clone https://github.com/chatgut/user-service.git

```
2. Navigate to the project directory:
```
cd user-service
```
3.
 ```
docker compose up
```

## Endpoints
The following endpoints are available:

## Get Requests

- Get all users:
```
GET http://localhost:8037/users
```
- Get user by ID:
```
GET http://localhost:8037/users/{id}
```

## Post Request

- Create a user based on JSON:
```
POST http://localhost:8037/users
```
Example JSON:
````
{
  "username": "JaneDoe",
  "firstName": "Jane",
  "lastName": "Doe",
  "imageUrl": "https://example.com/image.jpg"
}

````

## Put Request

- Update a user by ID based on JSON:
```
PUT http://localhost:8037/users/{id}
```
Example JSON:
````
{
  "username": "JohnDoe",
  "firstName": "John",
  "lastName": "Doe",
  "imageUrl": "https://example.com/image.jpg"
}
````

## Delete Request

- Delete a user by ID:
```
DELETE http://localhost:8037/users/{id}
```