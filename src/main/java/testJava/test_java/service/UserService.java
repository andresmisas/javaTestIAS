package testJava.test_java.service;

import org.springframework.data.repository.CrudRepository;
import testJava.test_java.model.User;

public interface UserService extends CrudRepository<User, Integer> {
}
