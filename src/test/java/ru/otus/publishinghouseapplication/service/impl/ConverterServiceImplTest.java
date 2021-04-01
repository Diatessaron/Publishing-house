package ru.otus.publishinghouseapplication.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.publishinghouseapplication.domain.Book;
import ru.otus.publishinghouseapplication.domain.BookOrder;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConverterServiceImplTest {
    @Mock
    private ConverterServiceImpl converterService;

    @Test
    void convertBookOrderToBook() {
        final BookOrder bookOrder = new BookOrder("title", "author");
        final Book book = new Book(bookOrder);

        when(converterService.convertBookOrderToBook(bookOrder)).thenReturn(book);

        final Book actual = converterService.convertBookOrderToBook(bookOrder);

        assertEquals(actual, book);
    }
}
