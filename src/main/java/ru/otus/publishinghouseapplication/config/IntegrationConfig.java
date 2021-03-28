package ru.otus.publishinghouseapplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.GenericSelector;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import ru.otus.publishinghouseapplication.domain.BookOrder;

import java.util.List;

@Configuration
@IntegrationComponentScan
@ComponentScan
@EnableIntegration
public class IntegrationConfig {
    @Bean
    public IntegrationFlow publishingFlow() {
        return IntegrationFlows.from("inputChannel")
                .filter(censorService())
                .handle("ConverterServiceImpl", "convertBookOrderToBook")
                .handle("PublishingServiceImpl", "publishBook")
                .channel("outputChannel")
                .get();
    }

    @Bean
    public DirectChannel outputChannel() {
        return new DirectChannel();
    }

    @Bean
    public DirectChannel inputChannel() {
        return new DirectChannel();
    }

    @Bean
    public GenericSelector<BookOrder> censorService() {
        final List<String> prohibitedAuthors = List.of("Abelard", "Ovid", "Sappho", "Galileo Galilei");

        return bookOrder -> {
            if (prohibitedAuthors.stream().anyMatch(a -> bookOrder.getAuthor().equalsIgnoreCase(a))){
                System.out.println(bookOrder.getAuthor() + " is prohibited from publishing");
                return false;
            }

            return true;
        };
    }
}
