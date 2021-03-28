package ru.otus.publishinghouseapplication.shell;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.shell.Shell;
import ru.otus.publishinghouseapplication.config.PublishingGateway;
import ru.otus.publishinghouseapplication.domain.BookOrder;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
class ApplicationCommandsTest {
    @MockBean
    private ApplicationContext context;

    @MockBean
    private PublishingGateway gateway;

    @Autowired
    private Shell shell;

    @Test
    void publishBooks() {
        when(context.getBean(PublishingGateway.class)).thenReturn(gateway);
        doNothing().when(gateway).process(new BookOrder("title", "author"));

        final String actual = shell.evaluate(() -> "pb 5").toString();

        assertEquals("Books have been published", actual);
    }
}
