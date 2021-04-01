package ru.otus.publishinghouseapplication.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.publishinghouseapplication.domain.Book;
import ru.otus.publishinghouseapplication.service.PublishingService;

import java.time.format.DateTimeFormatter;

@Service("PublishingServiceImpl")
public class PublishingServiceImpl implements PublishingService {
    @Override
    public void publishBook(Book book) {
        System.out.printf("Publishing %s by %s in %s%n", book.getBookOrder().getTitle(),
                book.getBookOrder().getAuthor(), book.getDate().format(DateTimeFormatter.ofPattern("yyyy MM dd")));
    }
}
