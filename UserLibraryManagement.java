public class UserLibraryManagement {
    static {
        System.loadLibrary("LibraryManagement"); // Load the shared library
    }

    // Declare the native methods for user operations
    public native void loadBooks();
    public native void borrowBook(String username, String isbn);
    public native void returnBook(String username, String isbn);
    public native void searchBook(String query);

    public static void main(String[] args) {
        UserLibraryManagement userLibrary = new UserLibraryManagement();
        userLibrary.loadBooks();

        // Example of borrowing a book
        userLibrary.borrowBook("johnDoe", "12345");

        // Example of returning a book
        userLibrary.returnBook("johnDoe", "12345");

        // Example of searching for a book
        userLibrary.searchBook("Java Programming");
    }
}
