package testJava.test_java.request;

import testJava.test_java.model.Book;
import testJava.test_java.model.User;

public class RequestCreateBookLoanDto {

    private boolean status;
    private Book books;
    private User users;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Book getBooks() {
        return books;
    }

    public void setBooks(Book books) {
        this.books = books;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }
}
