package testJava.test_java.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import testJava.test_java.interfaces.IBookLoanLogic;
import testJava.test_java.model.BookLoan;
import testJava.test_java.request.RequestCreateBookLoanDto;
import testJava.test_java.request.RequestUpdateBookLoanDto;
import testJava.test_java.service.BookLoanService;
import testJava.test_java.util.FieldErrorMessage;

import java.util.List;
import java.util.stream.Collectors;
@RestController
public class BookLoanController {
    @Autowired
    BookLoanService bookLoanService;
    @Autowired
    IBookLoanLogic iBookLoanLogic;

    @GetMapping("/bookLoan")
    Iterable<BookLoan> read(){
        return bookLoanService.findAll();
    }
    @PostMapping("/bookLoan")
    BookLoan create(@Valid @RequestBody RequestCreateBookLoanDto requestCreateBookLoanDto){
        return iBookLoanLogic.loanBook(requestCreateBookLoanDto);
    }
    @PutMapping("/bookLoan")
    BookLoan update(@Valid @RequestBody RequestUpdateBookLoanDto requestUpdateBookLoanDto){
        return iBookLoanLogic.updateloan(requestUpdateBookLoanDto);
    }
    @DeleteMapping("/bookLoan/{id}")
    void delete(@PathVariable Integer id){
        bookLoanService.deleteById(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    List<FieldErrorMessage> exceptionHandler(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<FieldErrorMessage> fieldErrorMessages = fieldErrors.stream().map(fieldError -> new FieldErrorMessage(fieldError.getField(), fieldError.getDefaultMessage())).collect(Collectors.toList());
        return fieldErrorMessages;
    }
}
