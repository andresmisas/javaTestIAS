package testJava.test_java.controller;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import testJava.test_java.model.Book;
import testJava.test_java.model.User;
import testJava.test_java.service.BookService;
import testJava.test_java.service.UserService;
import testJava.test_java.util.FieldErrorMessage;

import java.util.List;
import java.util.stream.Collectors;
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user")
    Iterable<User> read(){
        return userService.findAll();
    }
    @PostMapping("/user")
    User create(@Valid @RequestBody User user){
        return userService.save(user);
    }
    @PutMapping("/user")
    User update(@RequestBody User user){
           return userService.save(user);
    }
    @DeleteMapping("/user/{id}")
    void delete(@PathVariable Integer id){
        userService.deleteById(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    List<FieldErrorMessage> exceptionHandler(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<FieldErrorMessage> fieldErrorMessages = fieldErrors.stream().map(fieldError -> new FieldErrorMessage(fieldError.getField(), fieldError.getDefaultMessage())).collect(Collectors.toList());
        return fieldErrorMessages;
    }
}
