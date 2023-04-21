package testJava.test_java.service;

import org.springframework.data.repository.CrudRepository;
import testJava.test_java.model.BookLoan;

public interface BookLoanService extends CrudRepository<BookLoan, Integer> {
}
