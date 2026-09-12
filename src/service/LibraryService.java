package service;

import exception.LibraryException;
import model.Book;
import model.Loan;
import model.Member;
import repository.BookRepository;
import repository.LoanRepository;
import repository.MemberRepository;

import java.util.List;

public class LibraryService {

    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final LoanRepository loanRepository;

    private int loanCounter = 1;

    public LibraryService(BookRepository bookRepository,
                          MemberRepository memberRepository,
                          LoanRepository loanRepository) {

        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.loanRepository = loanRepository;
    }

    // Add Book
    public void addBook(String bookId, String title, String author) {

        if (bookRepository.findById(bookId) != null) {
            throw new LibraryException("Book ID already exists.");
        }

        Book book = new Book(bookId, title, author);

        bookRepository.addBook(book);

        System.out.println("Book added successfully.");
    }

    // Add Member
    public void addMember(String memberId, String name) {

        if (memberRepository.findById(memberId) != null) {
            throw new LibraryException("Member ID already exists.");
        }

        Member member = new Member(memberId, name);

        memberRepository.addMember(member);

        System.out.println("Member added successfully.");
    }

    // Search Book
    public List<Book> searchBook(String title) {
        return bookRepository.findByTitle(title);
    }

    // Issue Book
    public void issueBook(String bookId, String memberId) {

        Book book = bookRepository.findById(bookId);

        if (book == null) {
            throw new LibraryException("Book not found.");
        }

        Member member = memberRepository.findById(memberId);

        if (member == null) {
            throw new LibraryException("Member not found.");
        }

        if (!book.isAvailable()) {
            throw new LibraryException("Book is currently not available.");
        }

        String loanId = "L" + loanCounter++;

        Loan loan = new Loan(loanId, bookId, memberId);

        loanRepository.addLoan(loan);

        book.issueBook();

        System.out.println("Book issued successfully.");
    }

    // Return Book
    public void returnBook(String bookId) {

        Book book = bookRepository.findById(bookId);

        if (book == null) {
            throw new LibraryException("Book not found.");
        }

        Loan loan = loanRepository.findActiveLoanByBookId(bookId);

        if (loan == null) {
            throw new LibraryException(
                    "No active loan found for this book."
            );
        }

        loan.returnBook();

        book.returnBook();

        System.out.println("Book returned successfully.");
    }

    // View All Books
    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    // View Available Books
    public List<Book> getAvailableBooks() {

        return bookRepository.getAllBooks()
                .stream()
                .filter(Book::isAvailable)
                .toList();
    }

    // View Members
    public List<Member> getAllMembers() {
        return memberRepository.getAllMembers();
    }

    // View Loans
    public List<Loan> getAllLoans() {
        return loanRepository.getAllLoans();
    }
}