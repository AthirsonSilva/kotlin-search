package com.app.bootstrap

import com.app.entity.Product
import com.app.repository.ProductRepository
import lombok.RequiredArgsConstructor
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
@RequiredArgsConstructor
class BootstrapData(private val productRepository: ProductRepository) : CommandLineRunner {
    override fun run(vararg args: String?) {
        if (productRepository.count() > 0) {
            return
        }

        val product1 = Product()

        product1.name = "Gaming PC"
        product1.description = "Gaming PC with 16GB RAM, 1TB HDD, 256GB SSD, GTX 1080Ti"
        product1.price = 100.0

        productRepository.save(product1)
    }
}