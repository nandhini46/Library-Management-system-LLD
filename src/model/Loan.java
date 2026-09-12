package model;

import java.time.LocalDate;

public class Loan {

    private String loanId;
    private String bookId;
    private String memberId;
    private LocalDate issueDate;
    private LocalDate returnDate;

    public Loan(String loanId, String bookId, String memberId) {
        this.loanId = loanId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.issueDate = LocalDate.now();
        this.returnDate = null;
    }

    public String getLoanId() {
        return loanId;
    }

    public String getBookId() {
        return bookId;
    }

    public String getMemberId() {
        return memberId;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void returnBook() {
        this.returnDate = LocalDate.now();
    }

    public boolean isReturned() {
        return returnDate != null;
    }

    @Override
    public String toString() {
        return "Loan ID: " + loanId +
                ", Book ID: " + bookId +
                ", Member ID: " + memberId +
                ", Issue Date: " + issueDate +
                ", Return Date: " +
                (returnDate == null ? "Not Returned" : returnDate);
    }
}