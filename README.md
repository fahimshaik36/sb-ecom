# E-Commerce REST API

A production-style e-commerce REST API built with Spring Boot, providing JWT-based authentication, user and role management, product and category management, shopping cart functionality, address management, order placement, payment information handling, product image uploads, pagination, sorting, searching, validation, centralized exception handling, and interactive OpenAPI/Swagger documentation.

## Key Highlights

- JWT-based authentication with cookie and Bearer token support
- User, seller, and administrator role management
- RESTful API built with Spring Boot
- PostgreSQL database with Spring Data JPA
- Product and category management
- Shopping cart and order management
- Address management
- Product image upload
- Pagination, sorting, category filtering, and keyword search
- Jakarta Bean Validation
- Centralized exception handling
- OpenAPI 3 and Swagger UI documentation
- AWS Elastic Beanstalk deployment
- Amazon RDS for PostgreSQL

## Tech Stack

| Technology | Purpose |
|---|---|
| Java 17 | Application development |
| Spring Boot 4.1.0 | Backend framework |
| Spring MVC | REST API development |
| Spring Data JPA | Data access and ORM |
| Spring Security | Authentication and security |
| JJWT | JWT-based authentication |
| PostgreSQL | Relational database |
| ModelMapper | DTO and entity mapping |
| Jakarta Bean Validation | Request validation |
| Lombok | Boilerplate code reduction |
| OpenAPI 3 / Swagger UI | API documentation and testing |
| Maven | Build and dependency management |
| AWS Elastic Beanstalk | Application deployment |
| Amazon RDS for PostgreSQL | Managed production database |

## Live Application

The application is deployed to AWS Elastic Beanstalk with Amazon RDS for PostgreSQL as the production database.

### Live Swagger UI

http://ecom-app-env.eba-pcyrd3rr.us-east-1.elasticbeanstalk.com/swagger-ui/index.html

The live Swagger UI provides an interactive interface for exploring and testing the available REST APIs.

## Architecture

The application follows a layered architecture that separates request handling, business logic, and data access.

```text
Client
  |
  v
Controller Layer
  |
  v
Service Layer
  |
  v
Repository Layer
  |
  v
PostgreSQL Database
```

Cross-cutting concerns such as authentication, validation, exception handling, DTO mapping, and API documentation are handled separately.

### Main Layers

- **Controller**: Exposes REST endpoints and handles HTTP requests and responses.
- **Service**: Contains application and business logic.
- **Repository**: Provides database access through Spring Data JPA.
- **Model**: Contains JPA entities representing persistent data.
- **Payload / DTO**: Defines API request and response models.
- **Security**: Handles JWT authentication and Spring Security configuration.
- **Exceptions**: Provides centralized exception handling.
- **Config**: Contains application and OpenAPI configuration.
- **Util**: Contains reusable authentication-related utilities.

## Project Structure

```text
src/
├── main/
│   ├── java/com/ecommerce/project/
│   │   ├── config/
│   │   ├── controller/
│   │   ├── exceptions/
│   │   ├── model/
│   │   ├── payload/
│   │   ├── repositories/
│   │   ├── security/
│   │   │   ├── jwt/
│   │   │   ├── request/
│   │   │   ├── response/
│   │   │   └── services/
│   │   ├── service/
│   │   └── util/
│   └── resources/
└── test/
```

## Authentication and Security

The application uses Spring Security with JWT-based authentication.

### Authentication Flow

```text
User
  |
  | Username and Password
  v
Sign-in Endpoint
  |
  | Authenticate Credentials
  v
JWT Generated
  |
  +--------------------------+
  |                          |
  v                          v
JWT Cookie              Authorization Header
                              |
                              | Bearer <token>
                              v
                       Protected API Request
                              |
                              v
                         JWT Filter
                              |
                              v
                         Validate JWT
                              |
                              v
                       Security Context
```

The JWT filter supports retrieving tokens from both the authentication cookie and the `Authorization: Bearer <token>` header.

### Supported Roles

The application defines the following roles:

- `ROLE_USER`
- `ROLE_SELLER`
- `ROLE_ADMIN`

Roles are stored with users and included in the authenticated user's authorities.

> **Note:** The current implementation provides role management and authenticated user authorities, but endpoint-level role authorization is not currently enforced through Spring Security rules.

### Password Security

User passwords are encoded using BCrypt before being stored.

### Sign-out

The sign-out endpoint clears the JWT authentication cookie and returns a confirmation response.

## REST API

### Authentication APIs

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/auth/signin` | Authenticate a user |
| POST | `/api/auth/signup` | Register a new user |
| GET | `/api/auth/username` | Retrieve the authenticated username |
| GET | `/api/auth/user` | Retrieve authenticated user information |
| POST | `/api/auth/signout` | Clear the JWT authentication cookie |

### Category APIs

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/public/categories` | Create a category |
| GET | `/api/public/categories` | Retrieve categories with pagination and sorting |
| PUT | `/api/public/categories/{categoryId}` | Update a category |
| DELETE | `/api/admin/categories/{categoryId}` | Delete a category |

### Product APIs

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/admin/categories/{categoryId}/product` | Add a product to a category |
| GET | `/api/public/products` | Retrieve products with pagination and sorting |
| GET | `/api/public/categories/{categoryId}/products` | Retrieve products by category |
| GET | `/api/public/products/keyword/{keyword}` | Search products by keyword |
| PUT | `/api/admin/products/{productId}` | Update a product |
| DELETE | `/api/admin/products/{productId}` | Delete a product |
| PUT | `/api/products/{productId}/image` | Upload or update a product image |

### Cart APIs

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/carts/products/{productId}/quantity/{quantity}` | Add a product to the cart |
| GET | `/api/carts` | Retrieve carts |
| GET | `/api/carts/users/cart` | Retrieve the authenticated user's cart |
| PUT | `/api/cart/products/{productId}/quantity/{operation}` | Increase or decrease cart item quantity |
| DELETE | `/api/carts/{cartId}/product/{productId}` | Remove a product from a cart |

### Address APIs

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/addresses` | Create an address |
| GET | `/api/addresses` | Retrieve addresses |
| GET | `/api/addresses/{addressId}` | Retrieve an address by ID |
| GET | `/api/users/addresses` | Retrieve addresses for the authenticated user |
| PUT | `/api/addresses/{addressId}` | Update an address |
| DELETE | `/api/addresses/{addressId}` | Delete an address |

### Order APIs

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/order/users/payments/{paymentMethod}` | Place an order with payment information |

## API Documentation

The project uses OpenAPI 3 and Swagger UI for interactive API documentation.

Swagger provides:

- Interactive API documentation
- API grouping by functional area
- Request and response schemas
- Request parameter documentation
- DTO field descriptions and examples
- JWT Bearer authentication support
- Interactive API testing

### API Groups

- Authentication APIs
- Category APIs
- Product APIs
- Cart APIs
- Address APIs
- Order APIs

### Local Environment

**Swagger UI**

http://localhost:8080/swagger-ui/index.html

**OpenAPI Specification**

http://localhost:8080/v3/api-docs

### AWS Deployment

**Swagger UI**

http://ecom-app-env.eba-pcyrd3rr.us-east-1.elasticbeanstalk.com/swagger-ui/index.html

**OpenAPI Specification**

http://ecom-app-env.eba-pcyrd3rr.us-east-1.elasticbeanstalk.com/v3/api-docs

## Database

The application uses PostgreSQL with Spring Data JPA.

### Local Development

For local development, PostgreSQL is used as the application database.

### AWS Deployment

In the AWS environment:

- The application is deployed using AWS Elastic Beanstalk.
- PostgreSQL is hosted on Amazon RDS.
- The Spring Boot application connects to the RDS database using environment-specific configuration.
- Database credentials and other sensitive configuration are kept outside the source repository.

The data model includes:

- Users and roles
- Addresses
- Categories
- Products
- Shopping carts and cart items
- Orders and order items
- Payments

An ER diagram is included as part of the project documentation.

## Validation

The application uses Jakarta Bean Validation for validating request data.

Validation is applied to relevant request objects and entities using annotations such as:

- `@NotBlank`
- `@Size`
- `@Email`
- `@Valid`

Examples include validation for user registration, login, addresses, categories, products, and payment information.

## Exception Handling

The application uses centralized exception handling through `@RestControllerAdvice`.

The global exception handler handles:

- Validation errors
- Resource-not-found errors
- Application/API exceptions

The implemented responses include:

| Scenario | HTTP Status |
|---|---|
| Validation error | `400 Bad Request` |
| Application/API exception | `400 Bad Request` |
| Resource not found | `404 Not Found` |

## Pagination, Sorting and Search

Product and category listing APIs support:

- Pagination
- Sorting

Product APIs additionally support:

- Category-based filtering
- Case-insensitive keyword search

Pagination and sorting defaults are centralized through application constants.

## Product Image Upload

Product images can be uploaded or updated through a multipart REST endpoint.

The file service:

- Accepts `MultipartFile`
- Generates a unique filename
- Stores the image in the configured file path
- Returns the generated filename

## Orders and Payments

The application supports order placement with associated payment information.

When an order is placed, the application:

1. Retrieves the authenticated user's cart.
2. Validates the requested address.
3. Creates the order.
4. Creates and associates a payment record.
5. Creates order items from the cart.
6. Reduces product stock quantities.
7. Removes ordered items from the cart.
8. Returns the created order details.

### Payment Integration Note

The current implementation does not integrate with a real external payment gateway such as Stripe or Razorpay.

Payment information such as the payment method, payment ID, payment status, response message, and payment gateway name is stored as part of the order and payment data model.

## Configuration and Environment Variables

Sensitive configuration is kept outside source control.

The repository excludes environment-specific and sensitive configuration files such as:

```text
.env
.env.*
src/main/resources/application.properties
```

Do not commit:

- Database credentials
- JWT secrets
- Infrastructure credentials
- Other sensitive configuration

## Running Locally

### Prerequisites

- JDK 17
- PostgreSQL
- Git
- Maven or Maven Wrapper

### Clone the Repository

```bash
git clone https://github.com/fahimshaik36/ecommerce-rest-api.git
cd ecommerce-rest-api
```

### Configure the Database

Create the required PostgreSQL database and configure the application with the required database and JWT settings.

Keep credentials and secrets outside the source repository.

### Build the Application

**Windows**

```bash
mvnw.cmd clean package
```

**Linux / macOS**

```bash
./mvnw clean package
```

### Run the Application

**Windows**

```bash
mvnw.cmd spring-boot:run
```

**Linux / macOS**

```bash
./mvnw spring-boot:run
```

The API will be available on the configured server port.

## Deployment

The application is deployed to AWS Elastic Beanstalk with Amazon RDS for PostgreSQL as the production database.

```text
Client
  |
  v
AWS Elastic Beanstalk
  |
  | Spring Boot REST API
  |
  v
Amazon RDS
  |
  | PostgreSQL
  v
Application Data
```

The deployed application uses environment-specific configuration for:

- RDS database connection
- Database credentials
- JWT secrets
- Other sensitive application settings

Sensitive information is not stored in the repository.

### AWS Swagger UI

http://ecom-app-env.eba-pcyrd3rr.us-east-1.elasticbeanstalk.com/swagger-ui/index.html

## Testing

The project includes Spring Boot application context tests.

Run the test suite with:

**Windows**

```bash
mvnw.cmd test
```

**Linux / macOS**

```bash
./mvnw test
```

## Documentation

Project documentation includes:

- ER diagram
- Application architecture
- REST API reference
- OpenAPI / Swagger documentation

## License

This project is licensed under the MIT License. See the [LICENSE.md](LICENSE.md) file for details.

## Author

**Fahim Shaik**
