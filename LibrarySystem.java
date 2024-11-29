import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

public class LibrarySystem {
    private Scanner scanner;
    private AdminLibraryManagement admin;
    private UserLibraryManagement user;
    private boolean isLoggedIn;
    private String currentUser;
    private HashMap<String, ArrayList<String>> borrowedBooks;
    private HashMap<String, ArrayList<String>> readingHistory;
    private ArrayList<String> purchaseRequests;

    public LibrarySystem() {
        scanner = new Scanner(System.in);
        admin = new AdminLibraryManagement();
        user = new UserLibraryManagement();
        isLoggedIn = false;
        borrowedBooks = new HashMap<>();
        readingHistory = new HashMap<>();
        purchaseRequests = new ArrayList<>();
        
        // Load initial data
        admin.loadUsers();
        user.loadBooks();
    }

    public void start() {
        while (true) {
            if (!isLoggedIn) {
                showLoginMenu();
            } else if (currentUser.equals("admin")) {
                showAdminMenu();
            } else {
                showUserMenu();
            }
        }
    }

    private void showLoginMenu() {
        System.out.println("\n=== Library Management System ===");
        System.out.println("1. Login as Admin");
        System.out.println("2. Login as User");
        System.out.println("3. Register New User");
        System.out.println("4. Exit");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Clear buffer

        switch (choice) {
            case 1:
                adminLogin();
                break;
            case 2:
                userLogin();
                break;
            case 3:
                registerNewUser();
                break;
            case 4:
                System.out.println("Thank you for using the Library Management System!");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void showAdminMenu() {
        System.out.println("\n=== Admin Menu ===");
        System.out.println("1. Add Book");
        System.out.println("2. Remove Book");
        System.out.println("3. View All Users");
        System.out.println("4. Update User Profile");
        System.out.println("5. Generate Reports");
        System.out.println("6. View Late Returns");
        System.out.println("7. View Purchase Requests");
        System.out.println("8. Logout");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Clear buffer

        switch (choice) {
            case 1:
                addBook();
                break;
            case 2:
                removeBook();
                break;
            case 3:
                admin.loadUsers();
                break;
            case 4:
                updateUserProfile();
                break;
            case 5:
                generateReports();
                break;
            case 6:
                viewLateReturns();
                break;
            case 7:
                viewPurchaseRequests();
                break;
            case 8:
                logout();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void showUserMenu() {
        System.out.println("\n=== User Menu ===");
        System.out.println("1. Search Books");
        System.out.println("2. Borrow Book");
        System.out.println("3. Return Book");
        System.out.println("4. View My Borrowed Books");
        System.out.println("5. View Reading History");
        System.out.println("6. Request Book Purchase");
        System.out.println("7. Logout");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Clear buffer

        switch (choice) {
            case 1:
                searchBooks();
                break;
            case 2:
                borrowBook();
                break;
            case 3:
                returnBook();
                break;
            case 4:
                viewBorrowedBooks();
                break;
            case 5:
                viewReadingHistory();
                break;
            case 6:
                requestBookPurchase();
                break;
            case 7:
                logout();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void addBook() {
        System.out.println("Enter book title:");
        String title = scanner.nextLine();
        System.out.println("Enter author name:");
        String author = scanner.nextLine();
        System.out.println("Enter ISBN:");
        String isbn = scanner.nextLine();
        admin.addBook(title, author, isbn);
        System.out.println("Book added successfully!");
    }

    private void removeBook() {
        System.out.println("Enter ISBN of book to remove:");
        String isbn = scanner.nextLine();
        admin.removeBook(isbn);
        System.out.println("Book removed successfully!");
    }

    private void searchBooks() {
        System.out.println("Enter search term:");
        String query = scanner.nextLine();
        user.searchBook(query);
    }

    private void generateReports() {
        System.out.println("\n=== Library Statistics ===");
        System.out.println("Total Purchase Requests: " + purchaseRequests.size());
        System.out.println("Active Borrowers: " + borrowedBooks.size());
        System.out.println("Total Reading Records: " + readingHistory.size());
        
        System.out.println("\nMost Active Readers:");
        readingHistory.forEach((user, books) -> {
            System.out.println(user + ": " + books.size() + " books read");
        });
    }

    private void viewLateReturns() {
        System.out.println("\n=== Overdue Books ===");
        borrowedBooks.forEach((username, books) -> {
            System.out.println("User: " + username);
            System.out.println("Books borrowed: " + String.join(", ", books));
        });
    }

    private void viewBorrowedBooks() {
        System.out.println("\n=== Your Borrowed Books ===");
        ArrayList<String> books = borrowedBooks.getOrDefault(currentUser, new ArrayList<>());
        if (books.isEmpty()) {
            System.out.println("No books currently borrowed.");
        } else {
            books.forEach(book -> System.out.println("- " + book));
        }
    }

    private void viewReadingHistory() {
        System.out.println("\n=== Reading History ===");
        ArrayList<String> history = readingHistory.getOrDefault(currentUser, new ArrayList<>());
        if (history.isEmpty()) {
            System.out.println("No reading history available.");
        } else {
            history.forEach(book -> System.out.println("- " + book));
        }
    }

    private void requestBookPurchase() {
        System.out.println("\nEnter book details for purchase request:");
        System.out.println("Title: ");
        String title = scanner.nextLine();
        System.out.println("Author: ");
        String author = scanner.nextLine();
        System.out.println("ISBN: ");
        String isbn = scanner.nextLine();
        
        String request = String.format("Request from %s: %s by %s (ISBN: %s)", 
                                     currentUser, title, author, isbn);
        purchaseRequests.add(request);
        System.out.println("Purchase request submitted successfully!");
    }

    private void viewPurchaseRequests() {
        System.out.println("\n=== Purchase Requests ===");
        if (purchaseRequests.isEmpty()) {
            System.out.println("No purchase requests available.");
        } else {
            purchaseRequests.forEach(request -> System.out.println("- " + request));
        }
    }

    private void adminLogin() {
        System.out.println("Enter admin username:");
        String username = scanner.nextLine();
        System.out.println("Enter admin password:");
        String password = scanner.nextLine();
        
        if (username.equals("admin") && password.equals("admin123")) {
            isLoggedIn = true;
            currentUser = "admin";
            System.out.println("Admin login successful!");
        } else {
            System.out.println("Invalid admin credentials!");
        }
    }

    private void userLogin() {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        
        isLoggedIn = true;
        currentUser = username;
        
        borrowedBooks.putIfAbsent(currentUser, new ArrayList<>());
        readingHistory.putIfAbsent(currentUser, new ArrayList<>());
        
        System.out.println("Login successful! Welcome " + currentUser);
    }

    private void registerNewUser() {
        System.out.println("Enter new username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        admin.registerUser(username, password);
        System.out.println("User registered successfully!");
    }

    private void updateUserProfile() {
        System.out.println("Enter username to update:");
        String oldUsername = scanner.nextLine();
        System.out.println("Enter new username:");
        String newUsername = scanner.nextLine();
        System.out.println("Enter new password:");
        String newPassword = scanner.nextLine();
        admin.updateUserProfile(oldUsername, newUsername, newPassword);
        System.out.println("User profile updated successfully!");
    }

    private void borrowBook() {
        System.out.println("Enter ISBN of book to borrow:");
        String isbn = scanner.nextLine();
        user.borrowBook(currentUser, isbn);
        
        borrowedBooks.get(currentUser).add(isbn);
        readingHistory.get(currentUser).add(isbn);
        System.out.println("Book borrowed successfully!");
    }

    private void returnBook() {
        System.out.println("Enter ISBN of book to return:");
        String isbn = scanner.nextLine();
        user.returnBook(currentUser, isbn);
        
        borrowedBooks.get(currentUser).remove(isbn);
        System.out.println("Book returned successfully!");
    }

    private void logout() {
        isLoggedIn = false;
        currentUser = null;
        System.out.println("Logged out successfully!");
    }

    public static void main(String[] args) {
        LibrarySystem system = new LibrarySystem();
        system.start();
    }
}
