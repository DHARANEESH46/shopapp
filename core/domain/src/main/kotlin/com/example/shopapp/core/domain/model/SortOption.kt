package com.example.shopapp.core.domain.model

enum class SortOption(val sortBy: String, val order: String) {
    NAME_A_Z("title", "asc"),
    NAME_Z_A("title", "desc"),
    PRICE_HIGH_LOW("price", "desc"),
    PRICE_LOW_HIGH("price", "asc")
}