# Library Management System API Documentation

This is the documentation for the Library Management System API. It provides information on how to run the application, interact with API endpoints, and run tests.
## Technologies Used

- **Java**: The core programming language used for backend development.
- **Spring Boot**: A powerful framework for building Java-based applications, providing features like dependency injection, auto-configuration, and more.
- **Gradle**: A build automation tool used for managing project dependencies and building the application.
- **H2 Database**: An in-memory relational database used for storing application data during development and testing.
- **JUnit**: A unit testing framework for Java used to write and run tests for the application.
- **Postman**: An API client used for testing API endpoints during development.
- **Git**: A version control system used for managing project source code.

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

### Using Postman

To simplify testing of the Library Management System API, you can use Postman, a popular API testing tool. Follow the steps below to import the provided API collection into Postman:

1. **Open Postman**: Launch the Postman application on your computer.

2. **Import Collection**: Click on the "Import" button located in the top left corner of the Postman application.

3. **Choose File**: In the import modal, select the "Choose Files" button and navigate to the `docs/api/` folder in this repository.

4. **Select File**: Choose the `library_management_system_api.postman_collection.json` file and click "Open" to import it into Postman.

5. **Verify Import**: After the import process completes, you will see the imported collection listed in the left sidebar under the "Collections" tab.

6. **Explore Endpoints**: Click on the collection to expand it and view the individual requests. You can now explore the endpoints and execute requests to interact with your API.
   
7. **Send Requests with JSON Body**:
   - Select the request method (POST, PUT).
   - Enter the endpoint URL.
   - Go to the **Body** tab.
   - Select **raw**.
   - Choose **JSON** from the dropdown.
   - Enter the JSON data in the request body.
   - Click the **Send** button to make the request.
   - View the response in the response body pane.

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
