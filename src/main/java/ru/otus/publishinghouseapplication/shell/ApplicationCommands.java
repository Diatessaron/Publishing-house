package ru.otus.publishinghouseapplication.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.publishinghouseapplication.service.ProcessingService;

@ShellComponent
public class ApplicationCommands {
    private final ProcessingService processingService;

    public ApplicationCommands(ProcessingService processingService) {
        this.processingService = processingService;
    }

    @ShellMethod(key = {"pb", "publishBooks"}, value = "Publish books. Arguments: quantity of books to publish.")
    public String publishBooks(@ShellOption("Quantity") int bookQuantity) {
        return processingService.processPublishing(bookQuantity);
    }
}
