# E-Commerce Management System (ECM)

## Overview
The **E-Commerce Management System (ECM)** is a web-based application designed to manage products, categories, user carts, and user authentication. It provides functionalities for administrators to manage products and categories, and for users to browse products, add them to their cart, and complete purchases.

---

## Features
- **User Authentication**: Secure user registration and login using Spring Security.
- **Product Management**: Add, update, delete, and view products.
- **Category Management**: Add, update, delete, and view categories.
- **Shopping Cart**: Users can add/remove products to/from their cart and view the total price.
- **File Upload**: Admins can upload product images.
- **Role-Based Access Control**: Different access levels for admins and users.

---

## Technologies Used
- **Backend**:
  - Java 17
  - Spring Boot
  - Spring MVC
  - Spring Data JPA
  - Spring Security
  - Hibernate
- **Frontend**:
  - Thymeleaf (for server-side rendering)
  - HTML/CSS
  - Bootstrap (for styling)
- **Database**:
  - MySQL (or any relational database supported by Spring Data JPA)
- **Build Tool**:
  - Maven

---

## System Architecture
The system follows a **3-tier architecture**:
1. **Presentation Layer**:
   - Handles user interactions and displays data using Thymeleaf templates.
2. **Business Logic Layer**:
   - Contains service classes that implement business logic (e.g., `ProductService`, `CartService`).
3. **Data Access Layer**:
   - Uses Spring Data JPA repositories (`ProductDao`, `UserDao`, etc.) to interact with the database.

### Flow of Control
1. User interacts with the frontend (Thymeleaf templates).
2. Requests are handled by Spring MVC controllers (`AdminController`, `CartController`, etc.).
3. Controllers call appropriate services to process requests.
4. Services interact with repositories to fetch or modify data in the database.
5. Responses are sent back to the frontend for rendering.

---

## Database Schema
The database consists of the following tables:
- **Users**: Stores user information (e.g., email, password, roles).
- **Products**: Stores product details (e.g., name, price, category).
- **Categories**: Stores product categories.
- **Carts**: Stores user cart information.
- **Cart_Product**: A join table to manage many-to-many relationships between carts and products.
- **Roles**: Stores user roles (e.g., ADMIN, USER).

## API Endpoints
### Admin Endpoints
- `GET /admin`: Admin home page.
- `GET /admin/categories`: List all categories.
- `GET /admin/categories/add`: Display form to add a new category.
- `POST /admin/categories/add`: Save a new category.
- `GET /admin/categories/delete/{id}`: Delete a category by ID.
- `GET /admin/categories/update/{id}`: Display form to update a category.
- `POST /admin/categories/submitUpdate`: Submit updated category details.

### User Endpoints
- `GET /shop`: Display all products.
- `GET /shop/category/{id}`: Display products by category.
- `GET /shop/viewproduct/{id}`: View details of a specific product.
- `GET /cart`: Display the user's cart.
- `GET /addToCart/{id}`: Add a product to the cart.
- `GET /cart/removeItem/{index}`: Remove a product from the cart.


