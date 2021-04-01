package ru.otus.publishinghouseapplication.domain;

import java.util.Objects;

public class BookOrder {
    private String title;
    private String author;

    public BookOrder(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookOrder bookOrder = (BookOrder) o;
        return title.equals(bookOrder.title) && author.equals(bookOrder.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author);
    }

    @Override
    public String toString() {
        return "BookOrder{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
