package ru.otus.publishinghouseapplication.config;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.publishinghouseapplication.domain.BookOrder;

@MessagingGateway
public interface PublishingGateway {
    @Gateway(requestChannel = "inputChannel", replyChannel = "outputChannel")
    void process(BookOrder bookOrder);
}
