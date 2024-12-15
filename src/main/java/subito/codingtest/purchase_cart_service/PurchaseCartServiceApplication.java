package subito.codingtest.purchase_cart_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import subito.codingtest.purchase_cart_service.product.entities.Product;
import subito.codingtest.purchase_cart_service.product.repositories.ProductRepository;

import java.math.BigDecimal;

@SpringBootApplication
public class PurchaseCartServiceApplication {

    private final Logger log = LoggerFactory.getLogger(PurchaseCartServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(PurchaseCartServiceApplication.class, args);
    }

    /**
     * Based on the response shown in the document, </br>
     * I assume that for each item, the price and the vat includes the quantity. </br></br>
     * <p>
     * Pricing data:</br>
     * <p>
     * product_id 1 -> unit price 2.00 -> unit VAT 0.20 </br>
     * product_id 2 -> unit price 1.50 -> unit VAT 0.15 </br>
     * product_id 3 -> unit price 3.00 -> unit VAT 0.30 </br>
     */
    @Bean
    public CommandLineRunner initData(ProductRepository productRepository) {
        return args -> {
            productRepository.save(new Product(BigDecimal.valueOf(2), BigDecimal.valueOf(0.20)));
            productRepository.save(new Product(BigDecimal.valueOf(1.5), BigDecimal.valueOf(0.15)));
            productRepository.save(new Product(BigDecimal.valueOf(3.0), BigDecimal.valueOf(0.30)));

            log.info("Products loaded:");
            log.info("-------------------------------");
            productRepository.findAll().forEach(product -> log.info(product.toString()));
            log.info("-------------------------------");
        };
    }

}
