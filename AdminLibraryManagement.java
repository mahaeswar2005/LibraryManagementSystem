public class AdminLibraryManagement {
    static {
        System.loadLibrary("LibraryManagement"); // Load the shared library
    }

    // Declare the native methods for admin operations
    public native void addBook(String title, String author, String isbn);
    public native void removeBook(String isbn);
    public native void loadUsers();
    public native void registerUser(String username, String password);
    public native void updateUserProfile(String username, String newUsername, String newPassword);

    public static void main(String[] args) {
        AdminLibraryManagement adminLibrary = new AdminLibraryManagement();
        adminLibrary.loadUsers();

        // Example of adding a new book
        adminLibrary.addBook("Learn C++", "John Smith", "67890");

        // Example of removing a book
        adminLibrary.removeBook("67890");

        // Example of updating a user's profile
        adminLibrary.updateUserProfile("johnDoe", "johnUpdated", "newPassword123");

        // Example of registering a new user
        adminLibrary.registerUser("newUser", "password123");
    }
}
