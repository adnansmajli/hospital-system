# Hospital Management System

## Overview

A Spring Boot–powered backend for a Hospital Management System.
It implements:

* **User authentication & authorization** (JWT-based)
* **CRUD REST API** for Patients, Doctors & Appointments
* **Database migrations** via Flyway
* **Global error handling** with `@RestControllerAdvice`
* **Entity relationships** (One-to-Many between Doctors→Appointments and Patients→Appointments)

> **Note:** The Vue 3 frontend (per assignment requirement) is not yet implemented. See **Future Work** below for details.

---

## Technologies

* **Java 11+** & **Spring Boot 3.x**
* **Spring Web**, **Spring Security** (JWT), **MapStruct**, **Flyway**
* **Maven** (with Maven Wrapper)
* **PostgreSQL**
* **Docker & Docker Compose**
* **(Pending) Frontend:** Vue 3, Vue Router, Pinia, Axios

---

## Prerequisites

* Java 11 or newer
* Maven (or use `./mvnw` / `mvnw.cmd`)
* PostgreSQL (local or Docker)
* Docker & Docker Compose (optional, for containerized run)

---

## Getting Started

1. **Clone the repo**

   ```bash
   git clone https://github.com/adnansmajli/hospital-system.git
   cd hospital-system
   ```

2. **Configure database**

   * Update your PostgreSQL credentials in
     `src/main/resources/application.yml`
   * Or set environment variables: `DB_URL`, `DB_USER`, `DB_PASS`

3. **Run with Maven**

   ```bash
   ./mvnw spring-boot:run
   ```

   The API will be available at `http://localhost:8080`.

4. **Run with Docker Compose**

   ```bash
   docker-compose up --build
   ```

   * Starts both PostgreSQL and the Spring Boot app
   * Default API port: `8080`

---

## Backend Details

### Entities & Repositories

* **User** (`com.adnansmajli.hospital.model.User`)

  * Fields: `id`, `username`, `password`, `email`, `roles`
  * Repo: `UserRepository`

* **Role** (`Role`)

  * Fields: `id`, `name`
  * Repo: `RoleRepository`

* **Patient** (`com.adnansmajli.hospital.model.Patient`)

  * Fields: `id`, `firstName`, `lastName`, `personalNo`, `birthDate`, `gender`, `email`, `phoneNumber`, plus address fields
  * Relation: One-to-Many → `Appointment`
  * Repo: `PatientRepository`

* **Doctor** (`Doctor`)

  * Fields: `id`, `name`, `specialty`, `email`, `phoneNumber`
  * Relation: One-to-Many → `Appointment`
  * Repo: `DoctorRepository`

* **Appointment** (`Appointment`)

  * Fields: `id`, `dateTime`, `status`
  * FKs: `patient_id`, `doctor_id`
  * Repo: `AppointmentRepository`

### DTOs & Mappers

* DTO classes under `dto/` (e.g. `PatientDto`, `DoctorDto`, `AppointmentDto`)
* MapStruct interfaces for conversions:

  * `PatientMapper`
  * `DoctorMapper`
  * `AppointmentMapper`

### REST Controllers

* **AuthController**

  * `POST /api/auth/register` → register user
  * `POST /api/auth/login`    → return JWT

* **PatientController**

  * `GET    /api/patients`       (ADMIN only)
  * `POST   /api/patients`       (ADMIN)
  * `GET    /api/patients/{id}`  (USER or ADMIN)
  * `PUT    /api/patients/{id}`  (ADMIN)
  * `DELETE /api/patients/{id}`  (ADMIN)

* **DoctorController** (same CRUD pattern for doctors)

* **AppointmentController**

  * `GET    /api/appointments`
  * `POST   /api/appointments`
  * `GET    /api/appointments/{id}`
  * `PUT    /api/appointments/{id}`
  * `DELETE /api/appointments/{id}`

### Security Configuration

* `WebSecurityConfig` defines JWT filter (`AuthTokenFilter`), password encoder, and role-based URL protection
* `AuthEntryPointJwt` handles unauthorized access responses

### Flyway Migrations

SQL scripts in `src/main/resources/db/migration`:

* `V1__Create_users_roles_tables.sql`
* `V2__Create_patient_doctor_appointment_tables.sql`
* `V3__Add_foreign_keys_and_constraints.sql`

Flyway runs automatically at startup.

### Global Error Handling

* `GlobalExceptionHandler` (`@RestControllerAdvice`)

  * Handles `MethodArgumentNotValidException` → 400 Bad Request
  * Handles custom `ResourceNotFoundException` → 404 Not Found
  * Handles `AccessDeniedException` → 403 Forbidden

---

## Future Work

Per the original assignment, the **Vue 3 frontend** must include:

1. **Reactivity & Templates** (`ref`, `reactive`, `computed`)
2. **Conditional Rendering & Lists** (`v-if`, `v-for`)
3. **Reusable Components & Props**
4. **Routing** (Vue Router + route guards based on auth/roles)
5. **Global State** (Pinia store for JWT & user info)
6. **HTTP** (Axios or `fetch` for API calls, error handling)
7. **Auth Pages** (Login & Register; hide/show UI based on roles)

> **Status:** Frontend scaffold is pending—components and views will be added next.

---

## License

MIT © Adnan Smajli

---

## Contact

* **Email:** [adnansmajli05@gmail.com](mailto:adnansmajli05@gmail.com)
* **GitHub:** [adnansmajli/hospital-system](https://github.com/adnansmajli/hospital-system)
