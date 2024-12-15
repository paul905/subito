package subito.codingtest.purchase_cart_service.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/isAlive")
    public String isAlive(){
        return "Purchase cart service : status Up and Running!";
    }
}
