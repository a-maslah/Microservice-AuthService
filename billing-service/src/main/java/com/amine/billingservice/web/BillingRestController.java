package com.amine.billingservice.web;


import com.amine.billingservice.entites.Bill;
import com.amine.billingservice.feign.CustomerRestService;
import com.amine.billingservice.feign.ProductRestClient;
import com.amine.billingservice.model.Customer;
import com.amine.billingservice.model.Product;
import com.amine.billingservice.repositories.BillRepository;
import com.amine.billingservice.repositories.ProductItemRepository;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillingRestController {

    private BillRepository billRepository;
    private ProductItemRepository productItemRepository;
    private CustomerRestService customerRestService;
    private ProductRestClient productRestClient;

    public BillingRestController(BillRepository billRepository, ProductItemRepository productItemRepository, CustomerRestService customerRestService, ProductRestClient productRestClient) {
        this.billRepository = billRepository;
        this.productItemRepository = productItemRepository;
        this.customerRestService = customerRestService;
        this.productRestClient = productRestClient;
    }

    @GetMapping(path = "/fullBill/{id}")
    public Bill getBill(@PathVariable(name = "id") Long id){
           Bill bill = billRepository.findById(id).get();
           Customer customer= customerRestService.getCustomerById(bill.getCustomerId());
            bill.setCustomer(customer);
            bill.getProductItems().forEach(p->{
                Product product = productRestClient.getProductById(p.getId());
                p.setProduct(product);
            });
        return bill;
    }
}
