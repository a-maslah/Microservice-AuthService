package com.amine.cusomerms;

import com.amine.cusomerms.entites.Product;
import com.amine.cusomerms.respositories.ProductRespository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class ProductMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductMsApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ProductRespository productRespository, RepositoryRestConfiguration restConfi){
		return args -> {
			restConfi.exposeIdsFor(Product.class);
			productRespository.save(new Product("Hp PC",1000,10));
			productRespository.save(new Product("Phone",8000,2));
			productRespository.save(new Product("imprimante",500,1));

			productRespository.findAll().forEach(p->p.toString());
		};
	}

}
