package testJava.test_java.interfaces;

import testJava.test_java.model.BookLoan;
import testJava.test_java.request.RequestCreateBookLoanDto;
import testJava.test_java.request.RequestUpdateBookLoanDto;

public interface IBookLoanLogic {
    BookLoan updateloan(RequestUpdateBookLoanDto requestUpdateBookLoanDto);
    BookLoan loanBook(RequestCreateBookLoanDto requestCreateBookLoanDto);
}
