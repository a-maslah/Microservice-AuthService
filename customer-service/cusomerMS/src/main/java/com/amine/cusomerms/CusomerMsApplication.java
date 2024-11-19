package com.amine.cusomerms;

import com.amine.cusomerms.entites.Customer;
import com.amine.cusomerms.respositories.CustomerRespository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CusomerMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CusomerMsApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerRespository customerRespository, RepositoryRestConfiguration restConfig){
		return args -> {
			restConfig.exposeIdsFor(Customer.class);
		   customerRespository.save(new Customer("amine","amine@gmail.com"));
			customerRespository.save(new Customer("test","test@gmail.com"));
			customerRespository.save(new Customer("karima","karima@gmail.com"));

			customerRespository.findAll().forEach(c->{
				System.out.println(c.getName());
			});
		};
	}

}
