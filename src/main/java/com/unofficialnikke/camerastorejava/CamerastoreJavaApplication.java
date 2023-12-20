package com.unofficialnikke.camerastorejava;

import com.unofficialnikke.camerastorejava.entities.Product;
import com.unofficialnikke.camerastorejava.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CamerastoreJavaApplication implements CommandLineRunner {

	private static final Logger logger =
			LoggerFactory.getLogger(
					CamerastoreJavaApplication.class
			);

	private final ProductRepository repository;

    public CamerastoreJavaApplication(ProductRepository repository) {
        this.repository = repository;
    }

    public static void main(String[] args) {
		SpringApplication.run
				(CamerastoreJavaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repository.save(new Product("Nikon Z6 II", "great camera", "Nikon", 2000, 10));
		repository.save(new Product("Canon R3", "ok camera", "Canon", 2400, 8));
		repository.save(new Product("Sony A9 III", "wow camera", "Sony", 5500, 5));

		for(Product product : repository.findAll()) {
			logger.info("title: {}, brand: {}",
					product.getBrand(), product.getTitle()
					);
		}
	}
}
