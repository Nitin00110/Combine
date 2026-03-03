# 📦 Combine: E-Commerce Order Management API

A production-ready RESTful API built with Spring Boot for managing e-commerce orders, tracking fulfillments, and aggregating revenue data. 

This project demonstrates enterprise-level backend architecture, including data masking via DTOs, highly optimized database queries, pagination, and cloud deployment.

## ✨ Key Features

* **Data Transfer Object (DTO) Pattern:** Utilizes modern Java Records to cleanly map database entities to API contracts, preventing data leakage and mass-assignment vulnerabilities.
* **Optimized Aggregations:** Pushes heavy mathematical computations (like total shipped revenue) down to the PostgreSQL database layer using custom Spring Data JPA `@Query` methods, reducing Java memory footprint to `O(1)`.
* **Scalable Data Retrieval:** Implements Spring Data `Pageable` to efficiently handle large datasets on read-heavy endpoints.
* **Database Indexing:** Utilizes Hibernate-generated B-Tree indexes on high-frequency lookup columns (e.g., Order Status) to eliminate full table scans.
* **Input Validation:** Enforces strict payload rules using `jakarta.validation` to ensure database integrity.
* **Environment Configuration:** Decouples sensitive credentials from the codebase using `.env` files for local development and injected environment variables for cloud deployment.

## 🛠️ Tech Stack

* **Language:** Java 17+
* **Framework:** Spring Boot 3.x
* **Data Access:** Spring Data JPA / Hibernate
* **Database (Production):** PostgreSQL
* **Database (Local Testing):** H2 In-Memory Database
* **Deployment & Hosting:** Railway.app
* **Build Tool:** Maven

## 🛣️ API Reference

### Order Management
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/orders` | Create a new order (defaults to `PLACED` status). |
| `GET` | `/orders` | Retrieve a paginated list of all orders. |
| `GET` | `/orders/{id}` | Fetch a specific order by its ID. |
| `PUT` | `/orders/{id}/fulfill` | Update an order's status to `SHIPPED` or `DELIVERED`. |

### Analytics
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/revenue` | Calculates the total gross revenue of all `SHIPPED` orders. |

*Note: All endpoints return sanitized DTOs rather than raw database entities.*

## 🚀 Local Setup Instructions

### Prerequisites
* Java 17 or higher installed
* PostgreSQL installed and running locally
* Maven

### 1. Clone the repository
```bash
git clone [https://github.com/your-username/combine-api.git](https://github.com/your-username/combine-api.git)
cd combine-api
