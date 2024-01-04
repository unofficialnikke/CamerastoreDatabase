package com.unofficialnikke.camerastorejava;

import com.unofficialnikke.camerastorejava.entities.CategoryEntity;
import com.unofficialnikke.camerastorejava.entities.ProductEntity;
import com.unofficialnikke.camerastorejava.entities.SupplierEntity;
import com.unofficialnikke.camerastorejava.repositories.CategoryRepository;
import com.unofficialnikke.camerastorejava.repositories.ProductRepository;
import com.unofficialnikke.camerastorejava.repositories.SupplierRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
        SupplierEntity supplierEntity1 = new SupplierEntity("Kari", "kari@oy.fi", "403539545");
        SupplierEntity supplierEntity2 = new SupplierEntity("Jori", "Jori@supplies.com", "53403455");
        srepository.saveAll(Arrays.asList(supplierEntity1, supplierEntity2));

        CategoryEntity categoryEntity1 = new CategoryEntity("Cameras");
        CategoryEntity categoryEntity2 = new CategoryEntity("Lenses");
        CategoryEntity categoryEntity3 = new CategoryEntity("Tripods");
        crepository.saveAll(Arrays.asList(categoryEntity1, categoryEntity2, categoryEntity3));

        prepository.save(new ProductEntity("Nikon Z6 II", "great camera",
                "Nikon", 2000, 10, categoryEntity1, supplierEntity1));
        prepository.save(new ProductEntity("Canon R3", "ok camera",
                "Canon", 2400, 8, categoryEntity1, supplierEntity1));
        prepository.save(new ProductEntity("Sony A9 III", "wow camera",
                "Sony", 5500, 5, categoryEntity1, supplierEntity2));
        prepository.save(new ProductEntity("MANFROTTO 161MK2B", "expensive tripod",
                "Manfrotto", 5500, 5, categoryEntity3, supplierEntity2));

        for (ProductEntity productEntity : prepository.findAll()) {
            logger.info("title: {}, brand: {}",
                    productEntity.getBrand(), productEntity.getTitle()
            );
        }
    }
}
