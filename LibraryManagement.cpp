#include <jni.h>
#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include "AdminLibraryManagement.h"
#include "UserLibraryManagement.h"

using namespace std;

// User and Book classes
class User {
public:
    string username;
    string password;
    User(string user, string pass) : username(user), password(pass) {}
};

class Book {
public:
    string title;
    string author;
    string isbn;
    string availability;
    Book(string t, string a, string i, string av) : title(t), author(a), isbn(i), availability(av) {}
};

// Simulated databases (in-memory)
vector<User> users;
vector<Book> books;

// Load books from a file
JNIEXPORT void JNICALL Java_UserLibraryManagement_loadBooks(JNIEnv *env, jobject obj) {
    ifstream infile("data/books.txt");
    string title, author, isbn, availability;
    while (getline(infile, title, ',') && getline(infile, author, ',') && getline(infile, isbn, ',') && getline(infile, availability)) {
        books.push_back(Book(title, author, isbn, availability));
    }
}

// Borrow a book
JNIEXPORT void JNICALL Java_UserLibraryManagement_borrowBook(JNIEnv *env, jobject obj, jstring username, jstring isbn) {
    const char *user = env->GetStringUTFChars(username, NULL);
    const char *isbn_str = env->GetStringUTFChars(isbn, NULL);

    for (auto &book : books) {
        if (book.isbn == isbn_str && book.availability == "available") {
            book.availability = "borrowed";
            cout << user << " borrowed the book: " << book.title << endl;
            break;
        }
    }

    env->ReleaseStringUTFChars(username, user);
    env->ReleaseStringUTFChars(isbn, isbn_str);
}

// Return a book
JNIEXPORT void JNICALL Java_UserLibraryManagement_returnBook(JNIEnv *env, jobject obj, jstring username, jstring isbn) {
    const char *user = env->GetStringUTFChars(username, NULL);
    const char *isbn_str = env->GetStringUTFChars(isbn, NULL);

    for (auto &book : books) {
        if (book.isbn == isbn_str && book.availability == "borrowed") {
            book.availability = "available";
            cout << user << " returned the book: " << book.title << endl;
            break;
        }
    }

    env->ReleaseStringUTFChars(username, user);
    env->ReleaseStringUTFChars(isbn, isbn_str);
}

// Search for a book
JNIEXPORT void JNICALL Java_UserLibraryManagement_searchBook(JNIEnv *env, jobject obj, jstring query) {
    const char *searchQuery = env->GetStringUTFChars(query, NULL);

    bool found = false;
    for (auto &book : books) {
        if (book.title.find(searchQuery) != string::npos || book.author.find(searchQuery) != string::npos) {
            cout << "Found book: " << book.title << " by " << book.author << endl;
            found = true;
        }
    }

    if (!found) {
        cout << "No books found for: " << searchQuery << endl;
    }

    env->ReleaseStringUTFChars(query, searchQuery);
}

// Admin adds a new book
JNIEXPORT void JNICALL Java_AdminLibraryManagement_addBook(JNIEnv *env, jobject obj, jstring title, jstring author, jstring isbn) {
    const char *bookTitle = env->GetStringUTFChars(title, NULL);
    const char *bookAuthor = env->GetStringUTFChars(author, NULL);
    const char *bookIsbn = env->GetStringUTFChars(isbn, NULL);

    books.push_back(Book(bookTitle, bookAuthor, bookIsbn, "available"));
    cout << "Added book: " << bookTitle << endl;

    env->ReleaseStringUTFChars(title, bookTitle);
    env->ReleaseStringUTFChars(author, bookAuthor);
    env->ReleaseStringUTFChars(isbn, bookIsbn);
}

// Admin removes a book
JNIEXPORT void JNICALL Java_AdminLibraryManagement_removeBook(JNIEnv *env, jobject obj, jstring isbn) {
    const char *isbn_str = env->GetStringUTFChars(isbn, NULL);

    for (auto it = books.begin(); it != books.end(); ++it) {
        if (it->isbn == isbn_str) {
            books.erase(it);
            cout << "Removed book with ISBN: " << isbn_str << endl;
            break;
        }
    }

    env->ReleaseStringUTFChars(isbn, isbn_str);
}

// Load users from a file
JNIEXPORT void JNICALL Java_AdminLibraryManagement_loadUsers(JNIEnv *env, jobject obj) {
    ifstream infile("data/users.txt");
    string username, password;
    while (infile >> username >> password) {
        users.push_back(User(username, password));
    }
}

// Register a new user (admin operation)
JNIEXPORT void JNICALL Java_AdminLibraryManagement_registerUser(JNIEnv *env, jobject obj, jstring username, jstring password) {
    const char *user = env->GetStringUTFChars(username, NULL);
    const char *pass = env->GetStringUTFChars(password, NULL);

    // Append to the file (simulating database insert)
    ofstream outfile("data/users.txt", ios::app);
    outfile << user << " " << pass << endl;
    users.push_back(User(user, pass));

    env->ReleaseStringUTFChars(username, user);
    env->ReleaseStringUTFChars(password, pass);
}

// Update a user's profile (admin operation)
JNIEXPORT void JNICALL Java_AdminLibraryManagement_updateUserProfile(JNIEnv *env, jobject obj, jstring username, jstring newUsername, jstring newPassword) {
    const char *oldUsername = env->GetStringUTFChars(username, NULL);
    const char *updatedUsername = env->GetStringUTFChars(newUsername, NULL);
    const char *updatedPassword = env->GetStringUTFChars(newPassword, NULL);

    for (auto &user : users) {
        if (user.username == oldUsername) {
            user.username = updatedUsername;
            user.password = updatedPassword;
            cout << "Updated user profile for: " << oldUsername << endl;
            break;
        }
    }

    env->ReleaseStringUTFChars(username, oldUsername);
    env->ReleaseStringUTFChars(newUsername, updatedUsername);
    env->ReleaseStringUTFChars(newPassword, updatedPassword);
}
