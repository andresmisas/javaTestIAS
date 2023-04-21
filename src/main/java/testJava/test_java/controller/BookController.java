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
import testJava.test_java.service.BookService;
import testJava.test_java.util.FieldErrorMessage;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("/book")
    Iterable<Book> read(){
        return bookService.findAll();
    }
    @PostMapping("/book")
    Book create(@Valid @RequestBody Book book){
        return bookService.save(book);
    }

    @DeleteMapping("/book/{id}")
    void delete(@PathVariable Integer id){
        bookService.deleteById(id);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    List<FieldErrorMessage> exceptionHandler(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<FieldErrorMessage> fieldErrorMessages = fieldErrors.stream().map(fieldError -> new FieldErrorMessage(fieldError.getField(), fieldError.getDefaultMessage())).collect(Collectors.toList());
        return fieldErrorMessages;
    }
}
