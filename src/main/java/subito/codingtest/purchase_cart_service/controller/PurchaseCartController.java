package subito.codingtest.purchase_cart_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PurchaseCartController {

    @GetMapping
    public String isAlive(){
        return "Hello from Purchase cart service : status Up and Running!";
    }
}
