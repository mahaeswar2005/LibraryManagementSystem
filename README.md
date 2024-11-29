# Library Management System

## Team Members:
- **Angam Mahabaleswar**
- **Saanvi Vishal**

## Overview
The **Library Management System** is a robust and efficient solution built with Java and C++ integration, designed to streamline library operations for both administrators and users. It leverages a Java-based user interface, a C++ backend for performance-critical operations, and JNI (Java Native Interface) for seamless communication between the two.

---

## Features

### Admin Features
- **Book Management:** Add or remove books from the system.
- **User Management:** Register and update user information.
- **Report Generation:** Generate reports on library operations.
- **Late Returns Monitoring:** Track overdue books.
- **Purchase Request Management:** Handle user requests for new books.
- **User Overview:** View detailed user profiles and activity.
- **Book Request Management:** Approve or reject book borrow requests.

### User Features
- **Book Search:** Search for books by title, author, or genre.
- **Borrow/Return Books:** Borrow books and mark their return.
- **Borrowed Books Tracking:** View currently borrowed books and due dates.
- **Reading History:** Access a history of borrowed books.
- **Book Purchase Requests:** Request new books to be added to the library.
- **Profile Management:** Update personal information.

---

## Technical Architecture
- **Frontend:** Java-based command-line interface for a user-friendly experience.
- **Backend:** Core operations implemented in C++ for high performance.
- **Integration:** JNI (Java Native Interface) to bridge Java and C++.
- **Data Storage:** File-based storage system for persistence.

---

## Prerequisites
- **Java Development Kit (JDK)** - Ensure JDK is installed and configured.
- **C++ Compiler** - A modern C++ compiler (e.g., GCC).
- **JNI Headers and Libraries** - Included with the JDK.


---

## Core Components
- **`LibrarySystem.java`:** Main controller and entry point for the application.
- **`AdminLibraryManagement.java`:** Handles admin-specific operations.
- **`UserLibraryManagement.java`:** Manages user-specific functionalities.
- **`LibraryManagement.cpp`:** Implements core operations like data storage and retrieval.

---

## Build and Run Instructions

### Step 1: Compile the C++ Native Library
```bash
g++ -c -fPIC -I"{path_java_directory}/include" -I"{path_java_directory}/include/linux" LibraryManagement.cpp -o LibraryManagement.o
g++ -shared -o libLibraryManagement.so LibraryManagement.o
```

### Step 2: Compile Java Files
```bash
javac *.java
```

### Step 3: Run the Application
```bash
java -Djava.library.path=. LibrarySystem
```

---

## System Workflow

### System Initialization
1. Native library loading.
2. Data initialization.
3. Login interface startup.

### Authentication Flow
1. Admin/User login.
2. Session management and access control.

### Operation Flow
1. Menu-driven interface for easy navigation.
2. Real-time command processing and data updates.

---

## Data Management
- **File-Based System:** Stores data locally for persistence.
- **Real-Time Tracking:** Updates user and book information dynamically.
- **Transaction Logging:** Maintains a log of all operations for traceability.

---

## Performance Features
- **Efficient Book Management:** Optimized algorithms for large datasets.
- **Real-Time Updates:** Instant reflection of data changes.
- **Optimized Data Operations:** Minimal latency during transactions.
