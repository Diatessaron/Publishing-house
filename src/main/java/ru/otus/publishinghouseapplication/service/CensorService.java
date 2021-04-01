package ru.otus.publishinghouseapplication.service;

import ru.otus.publishinghouseapplication.domain.BookOrder;

public interface CensorService {
    boolean checkForProhibitedAuthors(BookOrder bookOrder);
}
