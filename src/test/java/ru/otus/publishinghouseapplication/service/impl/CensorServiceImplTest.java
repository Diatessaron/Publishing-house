package ru.otus.publishinghouseapplication.service.impl;

import org.junit.jupiter.api.Test;
import ru.otus.publishinghouseapplication.domain.BookOrder;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

class CensorServiceImplTest {
    private final CensorServiceImpl censorService = new CensorServiceImpl();

    @Test
    void analyze() {
        final BookOrder abelard = new BookOrder("Sic et Non", "Abelard");
        final BookOrder aristotle = new BookOrder("Metaphysics", "Aristotle");

        assertFalse(censorService.checkForProhibitedAuthors(abelard));
        assertTrue(censorService.checkForProhibitedAuthors(aristotle));
    }
}
