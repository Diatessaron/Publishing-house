package ru.otus.publishinghouseapplication.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.publishinghouseapplication.domain.BookOrder;
import ru.otus.publishinghouseapplication.service.CensorService;

import java.util.List;

@Service
public class CensorServiceImpl implements CensorService {
    final List<String> prohibitedAuthors = List.of("Abelard", "Ovid", "Sappho", "Galileo Galilei");

    @Override
    public boolean checkForProhibitedAuthors(BookOrder bookOrder) {
        if (prohibitedAuthors.stream().anyMatch(a -> bookOrder.getAuthor().equalsIgnoreCase(a))) {
            System.out.println(bookOrder.getAuthor() + " is prohibited from publishing");
            return false;
        } else
            return true;
    }
}
