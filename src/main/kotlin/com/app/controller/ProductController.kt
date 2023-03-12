package com.app.controller

import com.app.entity.Product
import com.app.repository.ProductRepository
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
class ProductController(private val productRepository: ProductRepository) {

    @GetMapping
    fun findAll(): List<Product> = ResponseEntity.ok(productRepository.findAll()).body!!

    @GetMapping("/search")
    fun search(@RequestParam("name", defaultValue = "") name: String): List<Product> = ResponseEntity.ok(productRepository.findByName(name)).body!!
}