package com.codeing;

import com.codeing.model.Product;
import com.codeing.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReactiveDemoApplication implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;


    public static void main(String[] args) {
        SpringApplication.run(ReactiveDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Product product= new Product("P001","TV",24,1200L);

//        productRepository.save(product);

    }
}
