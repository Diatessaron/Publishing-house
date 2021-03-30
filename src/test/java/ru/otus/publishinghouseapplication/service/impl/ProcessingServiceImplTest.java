package ru.otus.publishinghouseapplication.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.publishinghouseapplication.config.PublishingGateway;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@SpringBootTest
class ProcessingServiceImplTest {
    @Autowired
    private ProcessingServiceImpl processingService;
    @MockBean
    private PublishingGateway gateway;

    @Test
    void processPublishing() {
        doNothing().when(gateway).process(any());

        final String actual = processingService.processPublishing(5);

        assertEquals("Books have been published", actual);
    }
}
