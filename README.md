# Library Management System API Documentation

This is the documentation for the Library Management System API. It provides information on how to run the application, interact with API endpoints, and run tests.

## Prerequisites

Before running the Library Management System API, ensure that you have the following prerequisites installed on your system:

1. **Git**: Download and install Git from the official website: [Git](https://git-scm.com/downloads). This is required to clone the repository.

2. **Java Development Kit (JDK)**: Download and install a compatible version of the Java Development Kit (JDK) from Oracle JDK or AdoptOpenJDK. The JDK is required to compile and run the Java code. You can download the JDK from the following links:
   - [Oracle JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
   - [AdoptOpenJDK](https://adoptopenjdk.net/)

   **Note:** Java JDK 22 is not supported. Please use a compatible version of the JDK. This project was made with Java 21.

3. **Internet Connection**: An internet connection is required during the initial build process to download dependencies using Gradle.

## Running the Application

To run the Library Management System API, follow these steps:

1. **Clone the Repository**: 
   ```bash
   git clone https://github.com/basselelsheikh/library-management-system.git
   ```

2. **Navigate to the Project Directory**:
   ```bash
   cd library-management-system
   ```

3. **Build the Application**:
   - **Windows**:
     ```bash
     .\gradlew build
     ```
   - **Linux/macOS**:
     ```bash
     ./gradlew build
     ```

4. **Run the Application**:
   - **Windows**:
     ```bash
     .\gradlew bootRun
     ```
   - **Linux/macOS**:
     ```bash
     ./gradlew bootRun
     ```

   The application will start, and you can access the H2 database console at [http://localhost:8080/h2-console](http://localhost:8080/h2-console).
   
   **Credentials for H2 Database Console:**
   - Username: `sa`
   - Password: `pa`

## Interacting with API Endpoints

### Books

- **GET /api/books**: Retrieve a list of all books.
- **GET /api/books/{id}**: Retrieve details of a specific book by ID.
- **POST /api/books**: Add a new book to the library.
- **PUT /api/books/{id}**: Update an existing book's information.
- **DELETE /api/books/{id}**: Remove a book from the library.

### Patrons

- **GET /api/patrons**: Retrieve a list of all patrons.
- **GET /api/patrons/{id}**: Retrieve details of a specific patron by ID.
- **POST /api/patrons**: Add a new patron to the system.
- **PUT /api/patrons/{id}**: Update an existing patron's information.
- **DELETE /api/patrons/{id}**: Remove a patron from the system.

### Borrowing Records

- **POST /api/borrow/{bookId}/patron/{patronId}**: Allow a patron to borrow a book.
- **PUT /api/return/{bookId}/patron/{patronId}**: Record the return of a borrowed book by a patron.

## Running Tests

To run the tests for the Library Management System API, execute the following command:

- **Windows**:
     ```bash
     .\gradlew test
     ```
- **Linux/macOS**:
     ```bash
     ./gradlew test
     ```
