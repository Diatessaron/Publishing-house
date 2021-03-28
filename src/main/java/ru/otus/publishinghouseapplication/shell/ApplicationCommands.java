package ru.otus.publishinghouseapplication.shell;

import org.springframework.context.ApplicationContext;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.publishinghouseapplication.config.PublishingGateway;
import ru.otus.publishinghouseapplication.domain.BookOrder;

import java.util.List;
import java.util.Random;

@ShellComponent
public class ApplicationCommands {
    private final ApplicationContext context;
    private static final List<String> authors = List.of("Thomas Aquinas", "Ovid", "Abelard", "Aristotle",
            "Galileo Galilei", "Plato", "William of Ockham", "Avicenna", "Sappho", "Augustine of Hippo");
    private static final List<String> titles = List.of("Summa Theologica", "Amores", "Sic et Non", "Metaphysics",
            "Dialogue Concerning the Two Chief World Systems", "Republic",
            "Tractatus de quantitate", "Remarks and Admonitions", "Ode to Aphrodite", "Confessions");
    private static final Random random = new Random();

    public ApplicationCommands(ApplicationContext context) {
        this.context = context;
    }

    @ShellMethod(key = {"pb", "publishBooks"}, value = "Publish books. Arguments: quantity of books to publish.")
    public String publishBooks(@ShellOption("Quantity") int bookQuantity) throws InterruptedException {
        final PublishingGateway gateway = context.getBean(PublishingGateway.class);

        for (int i = 0; i < bookQuantity; i++) {
            Thread.sleep(1000);

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
