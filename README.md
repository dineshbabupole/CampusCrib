# CampusCrib 🏨🎓

*CampusCrib* is a centralized platform that helps college students find hostels and PG accommodations near their campus. It simplifies the hostel search process by providing verified listings, amenities, pricing, and contact information — all in one place.

---

## 🚧 Project Status

This project is currently under development.

- ✅ Spring Boot project initialized
- 🔧 Backend development in progress
- 🖥 Frontend (React.js) planned

---

## 🌟 Features (Planned)

- Hostel/PG listings by location or college
- Filters for rent, amenities, and distance
- Contact details of hostel owners
- Image gallery and descriptions
- User login and account management

---

## 🧰 Tech Stack

- **Backend:** Spring Boot, Java  
- **Database:** MySQL  
- **Frontend:** React.js (planned)  
- **Build Tool:** Maven  
- **Version Control:** Git & GitHub

---

## 📁 Project Structure

```bash
campuscrib-backend/
├── src/
│   └── main/
│       └── java/
│           └── com/campuscrib/
│               ├── CampusCribApplication.java      # Main Spring Boot application class
│               ├── config/                         # Configuration files (e.g., security, CORS)
│               ├── controllers/                    # REST API endpoints
│               ├── dtos/                           # Data Transfer Objects for requests/responses
│               ├── exceptions/                     # Custom exception classes and global handler
│               ├── filter/                         # Request filters (e.g., JWT, authentication)
│               ├── models/                         # JPA entity classes representing DB tables
│               ├── repositories/                   # JPA repositories for DB access
│               ├── services/                       # Business logic layer
│               └── util/                           # Utility/helper classes (e.g., token utils)
│
├── src/
│   └── main/
│       └── resources/
│           ├── application.properties              # App configuration (DB URL, port, etc.)
│           └── static/                             # Static resources (if any)
│
└── pom.xml                                          # Maven build configuration
```
