package com.amine.billingservice;

import com.amine.billingservice.entites.Bill;
import com.amine.billingservice.entites.ProductItem;
import com.amine.billingservice.feign.CustomerRestService;
import com.amine.billingservice.feign.ProductRestClient;
import com.amine.billingservice.model.Customer;
import com.amine.billingservice.model.Product;
import com.amine.billingservice.repositories.BillRepository;
import com.amine.billingservice.repositories.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }


    @Bean
    CommandLineRunner start(BillRepository billRepository,
                            ProductItemRepository productItemRepository,
                            CustomerRestService customerRestService,
                            ProductRestClient productRestClient){

        return args -> {
            Customer customer = customerRestService.getCustomerById(1L);
            Bill bill =billRepository.save(new Bill(null,new Date(),null,customer.getId(),null));
            System.out.println("----------------------------------------");
            PagedModel<Product> productPagedModel = productRestClient.pageProducts(0,10);
            productPagedModel.forEach(p->{
              ProductItem productItem=new ProductItem();
              productItem.setPrice(p.getPrice());
              productItem.setQuantity(1+new Random().nextInt(100));
              productItem.setProductId(p.getId());
              productItem.setBill(bill);
              productItemRepository.save(productItem);
            });
        };
    }
}
