package com.app.payload

class PaginatedResponse (
    val data: List<Any>,
    val total: Int,
    val perPage: Int,
    val currentPage: Int,
    val lastPage: Int
) {}