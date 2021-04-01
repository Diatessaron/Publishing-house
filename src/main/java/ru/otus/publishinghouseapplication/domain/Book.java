package ru.otus.publishinghouseapplication.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Book {
    private BookOrder bookOrder;
    private LocalDateTime date;

    public Book(BookOrder bookOrder) {
        this.bookOrder = bookOrder;
        this.date = LocalDateTime.now();
    }

    public BookOrder getBookOrder() {
        return bookOrder;
    }

    public void setBookOrder(BookOrder bookOrder) {
        this.bookOrder = bookOrder;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return bookOrder.equals(book.bookOrder) && date.equals(book.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookOrder, date);
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookOrder=" + bookOrder +
                ", date=" + date +
                '}';
    }
}
