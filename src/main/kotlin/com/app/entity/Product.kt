package com.app.entity

import jakarta.persistence.*
import lombok.Data

@Data
@Entity
@Table(name = "products")
class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column(name = "name", length = 100)
    var name: String? = null

    @Column(name = "description", length = 100)
    var description: String? = null

    @Column(name = "price")
    var price: Double? = null

    @Column(name = "image", length = 100)
    var image: String? = null
}