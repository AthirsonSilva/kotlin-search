package com.app.repository

import com.app.entity.Product
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ProductRepository : JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))" +
            " OR LOWER(p.description) LIKE LOWER(CONCAT('%', :name, '%'))")
    fun search(name: String, pageable: Pageable): List<Product>

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))" +
            " OR LOWER(p.description) LIKE LOWER(CONCAT('%', :name, '%'))", countQuery = "SELECT count(p) FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))" +
            " OR LOWER(p.description) LIKE LOWER(CONCAT('%', :name, '%'))")
    fun searchCount(name: String, sort: Sort): List<Product>
}