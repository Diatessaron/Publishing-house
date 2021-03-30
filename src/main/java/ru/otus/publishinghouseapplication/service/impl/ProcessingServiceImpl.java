package ru.otus.publishinghouseapplication.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.publishinghouseapplication.config.PublishingGateway;
import ru.otus.publishinghouseapplication.domain.BookOrder;
import ru.otus.publishinghouseapplication.service.ProcessingService;

import java.util.List;
import java.util.Random;

@Service
public class ProcessingServiceImpl implements ProcessingService {
    private final PublishingGateway gateway;
    private static final List<String> authors = List.of("Thomas Aquinas", "Ovid", "Abelard", "Aristotle",
            "Galileo Galilei", "Plato", "William of Ockham", "Avicenna", "Sappho", "Augustine of Hippo");
    private static final List<String> titles = List.of("Summa Theologica", "Amores", "Sic et Non", "Metaphysics",
            "Dialogue Concerning the Two Chief World Systems", "Republic",
            "Tractatus de quantitate", "Remarks and Admonitions", "Ode to Aphrodite", "Confessions");
    private static final Random random = new Random();

    public ProcessingServiceImpl(PublishingGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public String processPublishing(int bookQuantity) {
        for (int i = 0; i < bookQuantity; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return e.getMessage() + " has occurred";
            }

            final BookOrder bookOrder = generateBookOrder();
            gateway.process(bookOrder);
        }

        return "Books have been published";
    }

    private static BookOrder generateBookOrder() {
        final int index = random.nextInt(authors.size());
        return new BookOrder(titles.get(index), authors.get(index));
    }
}
