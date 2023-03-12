package com.app.controller

import com.app.entity.Product
import com.app.payload.PaginatedResponse
import com.app.repository.ProductRepository
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
class ProductController(private val productRepository: ProductRepository) {

    @GetMapping
    fun findAll(): List<Product> = ResponseEntity.ok(productRepository.findAll()).body!!

    @GetMapping("/search")
    fun search(
            @RequestParam("name", defaultValue = "") name: String,
            @RequestParam("sort", defaultValue = "") sort: String,
            @RequestParam("page", defaultValue = "1") page: Int,
            @RequestParam("per_page", defaultValue = "9") perPage: Int
    ): PaginatedResponse {
        val direction: Sort

        if (sort == "asc") {
            direction = Sort.by(Sort.Direction.ASC, "price")
        } else if (sort == "desc") {
            direction = Sort.by(Sort.Direction.DESC, "price")
        } else {
            direction = Sort.by(Sort.Direction.ASC, "price")
        }

        return ResponseEntity.ok(
                PaginatedResponse(
                        data = productRepository.search(name, PageRequest.of(page - 1, perPage, direction)),
                        total = productRepository.searchCount(name, direction).size,
                        perPage = perPage,
                        currentPage = page,
                        lastPage = productRepository.count().toInt() / perPage
                )
        ).body!!
    }
}