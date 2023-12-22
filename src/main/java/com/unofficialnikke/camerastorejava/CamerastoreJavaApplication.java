package com.unofficialnikke.camerastorejava;

import com.unofficialnikke.camerastorejava.entities.Category;
import com.unofficialnikke.camerastorejava.entities.Product;
import com.unofficialnikke.camerastorejava.entities.Supplier;
import com.unofficialnikke.camerastorejava.repositories.CategoryRepository;
import com.unofficialnikke.camerastorejava.repositories.ProductRepository;
import com.unofficialnikke.camerastorejava.repositories.SupplierRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Arrays;

@SpringBootApplication
public class CamerastoreJavaApplication implements CommandLineRunner {
    private static final Logger logger =
            LoggerFactory.getLogger(
                    CamerastoreJavaApplication.class
            );
    private final ProductRepository prepository;
    private final CategoryRepository crepository;
    private final SupplierRepository srepository;

    public CamerastoreJavaApplication(
            ProductRepository prepository, CategoryRepository crepository, SupplierRepository srepository) {
        this.prepository = prepository;
        this.crepository = crepository;
        this.srepository = srepository;
    }

    public static void main(String[] args) {
        SpringApplication.run
                (CamerastoreJavaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Supplier supplier1 = new Supplier("Kari", "kari@oy.fi", "403539545");
        Supplier supplier2 = new Supplier("Jori", "Jori@supplies.com", "53403455");
        srepository.saveAll(Arrays.asList(supplier1, supplier2));

        Category category1 = new Category("Cameras");
        Category category2 = new Category("Lenses");
        Category category3 = new Category("Tripods");
        crepository.saveAll(Arrays.asList(category1, category2, category3));

        prepository.save(new Product("Nikon Z6 II", "great camera",
                "Nikon", 2000, 10, category1, supplier1));
        prepository.save(new Product("Canon R3", "ok camera",
                "Canon", 2400, 8, category1, supplier1));
        prepository.save(new Product("Sony A9 III", "wow camera",
                "Sony", 5500, 5, category1, supplier2));
        prepository.save(new Product("MANFROTTO 161MK2B", "expensive tripod",
                "Manfrotto", 5500, 5, category3, supplier2));

        for (Product product : prepository.findAll()) {
            logger.info("title: {}, brand: {}",
                    product.getBrand(), product.getTitle()
            );
        }
    }
}
