package subito.codingtest.purchase_cart_service.integrationtests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import subito.codingtest.purchase_cart_service.api.dtos.CreateOrderResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class OrderControllerTest {


    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    @Test
    void createOrderShouldReturnCreatedWhenValidRequest() {

        // GIVEN
        String jsonRequest = """
                {
                    "order": {
                        "items": [
                            {
                                "product_id": 1,
                                "quantity": 1
                            },
                            {
                                "product_id": 2,
                                "quantity": 5
                            },
                            {
                                "product_id": 3,
                                "quantity": 1
                            }
                        ]
                    }
                }
                """;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<String> request = new HttpEntity<>(jsonRequest, headers);

        // WHEN
        ResponseEntity<CreateOrderResponse> response = this.testRestTemplate.postForEntity("http://localhost:" + port + "/order", request,
                CreateOrderResponse.class);

        // THEN
        assertEquals(HttpStatus.CREATED, response.getStatusCode());


    }

    @Test
    void createOrderShouldReturnInternalServerErrorWhenNotValidRequest() {

        // GIVEN
        String jsonRequest = """
                {
                    "order": {
        
                    }
                }
                """;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<String> request = new HttpEntity<>(jsonRequest, headers);

        // WHEN
        ResponseEntity<CreateOrderResponse> response = this.testRestTemplate.postForEntity("http://localhost:" + port + "/order", request,
                CreateOrderResponse.class);

        // THEN
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());


    }
}
