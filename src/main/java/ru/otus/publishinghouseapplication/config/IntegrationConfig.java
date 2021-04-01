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
import ru.otus.publishinghouseapplication.service.CensorService;
import ru.otus.publishinghouseapplication.service.ConverterService;
import ru.otus.publishinghouseapplication.service.PublishingService;

@Configuration
@IntegrationComponentScan
@ComponentScan
@EnableIntegration
public class IntegrationConfig {
    private final CensorService censorService;
    private final ConverterService converterService;
    private final PublishingService publishingService;

    public IntegrationConfig(CensorService censorService, ConverterService converterService,
                             PublishingService publishingService) {
        this.censorService = censorService;
        this.converterService = converterService;
        this.publishingService = publishingService;
    }

    @Bean
    public IntegrationFlow publishingFlow() {
        return IntegrationFlows.from("inputChannel")
                .filter(censorFilter())
                .handle(converterService, "convertBookOrderToBook")
                .handle(publishingService, "publishBook")
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
    public GenericSelector<BookOrder> censorFilter() {
        return censorService::checkForProhibitedAuthors;
    }
}
