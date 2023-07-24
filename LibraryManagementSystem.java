import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private boolean isBorrowed;
    private String borrower;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
        this.borrower = "";
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setBorrowed(boolean borrowed, String borrower) {
        isBorrowed = borrowed;
        this.borrower = borrower;
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Library Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Display Books");
            System.out.println("3. Search Books");
            System.out.println("4. Remove Book");
            System.out.println("5. Update Book Details");
            System.out.println("6. Borrow Book");
            System.out.println("7. Return Book");
            System.out.println("8. Book Availability Status");
            System.out.println("9. Library Statistics");
            System.out.println("10. Quit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            if (choice == 1) {
                System.out.print("Enter book title: ");
                String title = scanner.nextLine();
                System.out.print("Enter book author: ");
                String author = scanner.nextLine();
                Book book = new Book(title, author);
                books.add(book);
                System.out.println("Book added to the library.");
            } else if (choice == 2) {
                displayBooks(books);
            } else if (choice == 3) {
                searchBooks(books, scanner);
            } else if (choice == 4) {
                removeBook(books, scanner);
            } else if (choice == 5) {
                updateBookDetails(books, scanner);
            } else if (choice == 6) {
                borrowBook(books, scanner);
            } else if (choice == 7) {
                returnBook(books, scanner);
            } else if (choice == 8) {
                checkBookAvailability(books, scanner);
            } else if (choice == 9) {
                displayLibraryStatistics(books);
            } else if (choice == 10) {
                System.out.println("Exiting program.");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayBooks(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            System.out.println("Books in the library:");
            for (Book b : books) {
                System.out.println("Title: " + b.getTitle());
                System.out.println("Author: " + b.getAuthor());
                System.out.println("Borrowed: " + (b.isBorrowed() ? "Yes" : "No"));
                if (b.isBorrowed()) {
                    System.out.println("Borrower: " + b.getBorrower());
                }
                System.out.println("--------------------");
            }
        }
    }

    private static void searchBooks(List<Book> books, Scanner scanner) {
        System.out.print("Enter the keyword (title or author) to search for: ");
        String keyword = scanner.nextLine().toLowerCase();
        List<Book> searchResults = new ArrayList<>();

        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword) || book.getAuthor().toLowerCase().contains(keyword)) {
                searchResults.add(book);
            }
        }

        if (searchResults.isEmpty()) {
            System.out.println("No books found matching the keyword.");
        } else {
            System.out.println("Books matching the keyword:");
            displayBooks(searchResults);
        }
    }

    private static void removeBook(List<Book> books, Scanner scanner) {
        System.out.print("Enter the book title you want to remove: ");
        String titleToRemove = scanner.nextLine();

        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().equalsIgnoreCase(titleToRemove)) {
                books.remove(i);
                System.out.println("Book removed from the library.");
                return;
            }
        }

        System.out.println("Book not found in the library.");
    }

    private static void updateBookDetails(List<Book> books, Scanner scanner) {
        System.out.print("Enter the book title you want to update: ");
        String titleToUpdate = scanner.nextLine();

        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(titleToUpdate)) {
                System.out.println("Select the field to update:");
                System.out.println("1. Title");
                System.out.println("2. Author");
                int fieldChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (fieldChoice == 1) {
                    System.out.print("Enter the new title: ");
                    String newTitle = scanner.nextLine();
                    book.setTitle(newTitle);
                    System.out.println("Book title updated successfully.");
                } else if (fieldChoice == 2) {
                    System.out.print("Enter the new author: ");
                    String newAuthor = scanner.nextLine();
                    book.setAuthor(newAuthor);
                    System.out.println("Book author updated successfully.");
                } else {
                    System.out.println("Invalid choice. No updates were made.");
                }

                return;
            }
        }

        System.out.println("Book not found in the library.");
    }

    private static void borrowBook(List<Book> books, Scanner scanner) {
        System.out.print("Enter the book title you want to borrow: ");
        String titleToBorrow = scanner.nextLine();

        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(titleToBorrow)) {
                if (book.isBorrowed()) {
                    System.out.println("Sorry, the book is already borrowed by " + book.getBorrower());
                } else {
                    System.out.print("Enter your name: ");
                    String borrower = scanner.nextLine();
                    book.setBorrowed(true, borrower);
                    System.out.println("Book borrowed successfully.");
                }
                return;
            }
        }

        System.out.println("Book not found in the library.");
    }

    private static void returnBook(List<Book> books, Scanner scanner) {
        System.out.print("Enter the book title you want to return: ");
        String titleToReturn = scanner.nextLine();

        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(titleToReturn)) {
                if (book.isBorrowed()) {
                    book.setBorrowed(false, "");
                    System.out.println("Book returned successfully.");
                } else {
                    System.out.println("The book is not marked as borrowed.");
                }
                return;
            }
        }

        System.out.println("Book not found in the library.");
    }

    private static void checkBookAvailability(List<Book> books, Scanner scanner) {
        System.out.print("Enter the book title you want to check: ");
        String titleToCheck = scanner.nextLine();

        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(titleToCheck)) {
                if (book.isBorrowed()) {
                    System.out.println("Book is currently borrowed by " + book.getBorrower());
                } else {
                    System.out.println("Book is available for borrowing.");
                }
                return;
            }
        }

        System.out.println("Book not found in the library.");
    }

    private static void displayLibraryStatistics(List<Book> books) {
        int totalBooks = books.size();
        int availableBooks = 0;
        int borrowedBooks = 0;

        for (Book book : books) {
            if (book.isBorrowed()) {
                borrowedBooks++;
            } else {
                availableBooks++;
            }
        }

        System.out.println("Library Statistics:");
        System.out.println("Total number of books: " + totalBooks);
        System.out.println("Available books: " + availableBooks);
        System.out.println("Borrowed books: " + borrowedBooks);
    }
}
