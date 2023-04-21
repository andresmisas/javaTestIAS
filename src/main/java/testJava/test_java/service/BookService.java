package testJava.test_java.service;

import org.springframework.data.repository.CrudRepository;
import testJava.test_java.model.Book;

public interface BookService extends CrudRepository<Book, Integer> {
}
