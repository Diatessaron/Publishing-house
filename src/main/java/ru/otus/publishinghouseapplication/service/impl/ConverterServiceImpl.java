package ru.otus.publishinghouseapplication.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.publishinghouseapplication.domain.Book;
import ru.otus.publishinghouseapplication.domain.BookOrder;
import ru.otus.publishinghouseapplication.service.ConverterService;

@Service("ConverterServiceImpl")
public class ConverterServiceImpl implements ConverterService {
    @Override
    public Book convertBookOrderToBook(BookOrder bookOrder) {
        return new Book(bookOrder);
    }
}
