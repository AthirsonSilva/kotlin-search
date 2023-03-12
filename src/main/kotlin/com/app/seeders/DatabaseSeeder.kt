package com.app.seeders

import com.app.entity.Product
import com.app.repository.ProductRepository
import com.github.javafaker.Faker
import lombok.RequiredArgsConstructor
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import kotlin.random.Random

@Component
@RequiredArgsConstructor
class DatabaseSeeder(private val productRepository: ProductRepository) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        if (productRepository.count() > 20) {
            return
        }

        for (i in 1..20) {
            val product = Product()
            val faker = Faker()

            product.name = faker.food().dish()
            product.description = faker.food().ingredient()
            product.price = Random.nextDouble(100.0, 1000.0)
            product.image = faker.internet().image(200, 200, true, null)

            productRepository.save(product)
        }
    }
}