package com.app.repository

import com.app.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ProductRepository : JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))" +
            " OR LOWER(p.description) LIKE LOWER(CONCAT('%', :name, '%'))")
    fun findByName(name: String): List<Product>
}