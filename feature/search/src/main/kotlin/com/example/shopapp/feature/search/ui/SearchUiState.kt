package com.example.shopapp.feature.search.ui

import com.example.shopapp.core.domain.model.Product

data class SearchUiState(
    val query: String = "",
    val suggestions: List<Product> = emptyList()
)