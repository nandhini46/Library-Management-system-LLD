package repository;

import model.Book;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    private final List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public Book findById(String bookId) {

        for (Book book : books) {
            if (book.getBookId().equalsIgnoreCase(bookId)) {
                return book;
            }
        }

        return null;
    }

    public List<Book> findByTitle(String title) {

        List<Book> result = new ArrayList<>();

        for (Book book : books) {
            if (book.getTitle().toLowerCase()
                    .contains(title.toLowerCase())) {

                result.add(book);
            }
        }

        return result;
    }

    public List<Book> getAllBooks() {
        return books;
    }
}