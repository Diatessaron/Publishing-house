package ru.otus.publishinghouseapplication.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import ru.otus.publishinghouseapplication.domain.Book;
import ru.otus.publishinghouseapplication.domain.BookOrder;
import ru.otus.publishinghouseapplication.service.impl.ConverterServiceImpl;
import ru.otus.publishinghouseapplication.service.impl.PublishingServiceImpl;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@SpringBootTest
class IntegrationConfigTest {
    @Autowired
    private ApplicationContext context;

    @MockBean
    private ConverterServiceImpl converterService;

    @MockBean
    private PublishingServiceImpl publishingService;

    @Test
    void flowShouldBeCorrectlyWorkedOut() {
        final BookOrder bookOrder = new BookOrder("title", "author");
        final Book book = new Book(bookOrder);

        when(converterService.convertBookOrderToBook(bookOrder)).thenReturn(book);
        doNothing().when(publishingService).publishBook(book);

        final PublishingGateway gateway = context.getBean(PublishingGateway.class);
        gateway.process(bookOrder);

        verify(converterService, times(1)).convertBookOrderToBook(bookOrder);
        verify(publishingService, times(1)).publishBook(book);
    }

    @Test
    void shouldFilterIncorrectMessage() {
        final BookOrder bookOrder = new BookOrder("Sic et Non", "Abelard");
        final Book book = new Book(bookOrder);

        when(converterService.convertBookOrderToBook(bookOrder)).thenReturn(book);
        doNothing().when(publishingService).publishBook(book);

        final PublishingGateway gateway = context.getBean(PublishingGateway.class);
        gateway.process(bookOrder);

        verifyNoInteractions(converterService, publishingService);
    }
}
