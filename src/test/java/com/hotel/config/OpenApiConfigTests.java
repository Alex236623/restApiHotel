package com.hotel.config;
import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class OpenApiConfigTests {

    @Test
    public void testUsersMicroserviceOpenAPI() {
        ApplicationContext applicationContext = Mockito.mock(ApplicationContext.class);
        OpenApiConfig openApiConfig = new OpenApiConfig();

        OpenAPI openAPI = openApiConfig.usersMicroserviceOpenAPI();

        assertEquals("Hotel Rest API", openAPI.getInfo().getTitle());
        assertEquals("REST API", openAPI.getInfo().getDescription());
        assertEquals("1.0", openAPI.getInfo().getVersion());

        verifyNoInteractions(applicationContext);
    }
}