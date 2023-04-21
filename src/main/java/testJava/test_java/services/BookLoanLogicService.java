package testJava.test_java.services;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testJava.test_java.interfaces.IBookLoanLogic;
import testJava.test_java.model.Book;
import testJava.test_java.model.BookLoan;
import testJava.test_java.request.RequestCreateBookLoanDto;
import testJava.test_java.request.RequestUpdateBookLoanDto;
import testJava.test_java.service.BookLoanService;
import testJava.test_java.service.BookService;
import testJava.test_java.util.CustomException;

@Service
public class BookLoanLogicService implements IBookLoanLogic {
    @Autowired
    BookLoanService bookLoanService;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    BookService bookService;

    @Transactional
    public BookLoan updateloan(RequestUpdateBookLoanDto requestUpdateBookLoanDto){
        BookLoan bookLoanStored = bookLoanService.findById(requestUpdateBookLoanDto.getId()).get();
        if (bookLoanStored.isStatus()==false && requestUpdateBookLoanDto.isStatus()==true) {
            bookLoanStored.setStatus(requestUpdateBookLoanDto.isStatus());
            bookService.save(updateBookStatus(bookLoanStored.getBooks(),requestUpdateBookLoanDto.isStatus()));
            bookLoanService.save(bookLoanStored);
            return bookLoanStored;
        }else {
            throw new CustomException("El libro ya se encuentra en prestamo o disponible");
        }
    }
    // @Transactional
    public BookLoan loanBook(RequestCreateBookLoanDto requestCreateBookLoanDto){
        boolean bookStatus = findStatusByBookId(requestCreateBookLoanDto);
        if (bookStatus){
            BookLoan bookLoanToSave = modelMapper.map(requestCreateBookLoanDto,BookLoan.class);
            bookService.save(updateBookStatus(requestCreateBookLoanDto.getBooks(),requestCreateBookLoanDto.isStatus()));
            return bookLoanService.save(bookLoanToSave);
        }
        else {
            throw new CustomException("El libro ya se encuentra prestado");
        }
    }
    public boolean findStatusByBookId(RequestCreateBookLoanDto requestCreateBookLoanDto) {
        Book bookStatus = bookService.findById(requestCreateBookLoanDto.getBooks().getId()).get();
        if (bookStatus.isStatus()) {
            return true;
        }
        return false;
    }
    public Book updateBookStatus(Book book, boolean status){
        Book bookToUpdate = bookService.findById(book.getId()).get();
        bookToUpdate.setStatus(status);
        return  bookToUpdate;
    }
}


