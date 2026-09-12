package repository;

import model.Loan;
import java.util.ArrayList;
import java.util.List;

public class LoanRepository {

    private final List<Loan> loans = new ArrayList<>();

    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    public Loan findActiveLoanByBookId(String bookId) {

        for (Loan loan : loans) {

            if (loan.getBookId().equalsIgnoreCase(bookId)
                    && !loan.isReturned()) {

                return loan;
            }
        }

        return null;
    }

    public List<Loan> getAllLoans() {
        return loans;
    }
}