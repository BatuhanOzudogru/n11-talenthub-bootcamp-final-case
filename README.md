# n11-talenthub-bootcamp-final-case

## About The Project

This project is a system that allows for the management of user registration, user reviews, and CRUD operations for restaurants.

## Features
- **User Management**:
    - Users can be created, retrieved, updated, and deleted (CRUD operations).
    - During user registration, validation is performed against the Turkish Republic ID Number (TC) using the MERNIS system.


- **User Review Management**:
    - User reviews can be created, retrieved, updated, and deleted (CRUD operations).


- **Restaurant Management**:
    - Restaurants can be created, retrieved, updated, and deleted (CRUD operations).
    - The system provides recommendations of up to three restaurants based on the proximity of the current user's location and the restaurant's rating.


- **Integration with MERNIS System**:
    - During user registration, validation is performed against the MERNIS system using the Turkish Republic ID Number.


- **Integration with Apache Solr**:
    - Restaurant data is stored in Apache Solr.
    - Data is fetched from a different API(RestaurantService) using a Feign Client.


## Built With 
- Java 21
- Spring Boot
- Apache Solr
- Feign Client
- PostgreSQL
- Render

## Getting Started

### Prerequisites
- Java 21
- Docker Desktop
- PostgreSQL


### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/BatuhanOzudogru/n11-talenthub-bootcamp-final-case.git
   
2. Run Docker-Compose file for Apache Solr.
   ```sh
   cd RestaurantService 
    docker-compose up -d
   
3. Run the project (RestaurantServiceApplication && UserServiceApplication)


4. You can find the API documentation at the following address:
   ```sh
   http://localhost:8080/swagger-ui/index.html
