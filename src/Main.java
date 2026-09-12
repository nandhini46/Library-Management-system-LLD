import exception.LibraryException;
import model.Book;
import model.Loan;
import model.Member;
import repository.BookRepository;
import repository.LoanRepository;
import repository.MemberRepository;
import service.LibraryService;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        BookRepository bookRepository = new BookRepository();
        MemberRepository memberRepository = new MemberRepository();
        LoanRepository loanRepository = new LoanRepository();

        LibraryService libraryService =
                new LibraryService(
                        bookRepository,
                        memberRepository,
                        loanRepository
                );

        boolean running = true;

        System.out.println("=================================");
        System.out.println("   LIBRARY MANAGEMENT SYSTEM");
        System.out.println("=================================");

        while (running) {

            System.out.println("\n1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Search Book");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. View All Books");
            System.out.println("7. View Available Books");
            System.out.println("8. View Members");
            System.out.println("9. View Loan History");
            System.out.println("10. Exit");

            System.out.print("\nEnter your choice: ");

            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            try {

                switch (choice) {

                    case 1 -> {

                        System.out.print("Enter Book ID: ");
                        String bookId = scanner.nextLine();

                        System.out.print("Enter Book Title: ");
                        String title = scanner.nextLine();

                        System.out.print("Enter Author Name: ");
                        String author = scanner.nextLine();

                        libraryService.addBook(
                                bookId,
                                title,
                                author
                        );
                    }

                    case 2 -> {

                        System.out.print("Enter Member ID: ");
                        String memberId = scanner.nextLine();

                        System.out.print("Enter Member Name: ");
                        String name = scanner.nextLine();

                        libraryService.addMember(
                                memberId,
                                name
                        );
                    }

                    case 3 -> {

                        System.out.print("Enter Book Title: ");
                        String title = scanner.nextLine();

                        List<Book> books =
                                libraryService.searchBook(title);

                        if (books.isEmpty()) {
                            System.out.println("No books found.");
                        } else {
                            books.forEach(System.out::println);
                        }
                    }

                    case 4 -> {

                        System.out.print("Enter Book ID: ");
                        String bookId = scanner.nextLine();

                        System.out.print("Enter Member ID: ");
                        String memberId = scanner.nextLine();

                        libraryService.issueBook(
                                bookId,
                                memberId
                        );
                    }

                    case 5 -> {

                        System.out.print("Enter Book ID: ");
                        String bookId = scanner.nextLine();

                        libraryService.returnBook(bookId);
                    }

                    case 6 -> {

                        List<Book> books =
                                libraryService.getAllBooks();

                        if (books.isEmpty()) {
                            System.out.println("No books available.");
                        } else {
                            books.forEach(System.out::println);
                        }
                    }

                    case 7 -> {

                        List<Book> books =
                                libraryService.getAvailableBooks();

                        if (books.isEmpty()) {
                            System.out.println(
                                    "No books are currently available."
                            );
                        } else {
                            books.forEach(System.out::println);
                        }
                    }

                    case 8 -> {

                        List<Member> members =
                                libraryService.getAllMembers();

                        if (members.isEmpty()) {
                            System.out.println("No members found.");
                        } else {
                            members.forEach(System.out::println);
                        }
                    }

                    case 9 -> {

                        List<Loan> loans =
                                libraryService.getAllLoans();

                        if (loans.isEmpty()) {
                            System.out.println("No loan history found.");
                        } else {
                            loans.forEach(System.out::println);
                        }
                    }

                    case 10 -> {

                        running = false;

                        System.out.println(
                                "Thank you for using Library Management System!"
                        );
                    }

                    default ->
                            System.out.println("Invalid choice.");
                }

            } catch (LibraryException e) {

                System.out.println(
                        "Error: " + e.getMessage()
                );
            }
        }

        scanner.close();
    }
}