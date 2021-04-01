package ru.otus.publishinghouseapplication.service;

import ru.otus.publishinghouseapplication.domain.Book;
import ru.otus.publishinghouseapplication.domain.BookOrder;

public interface ConverterService {
    Book convertBookOrderToBook(BookOrder bookOrder);
}
