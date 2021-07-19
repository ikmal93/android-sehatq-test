package com.ikmal.androidsehatqtest.data.api.model

import java.io.Serializable

data class ProductPromo(
    val id: String,
    val description: String,
    val imageUrl: String,
    val loved: Int,
    val price: String,
    val title: String
) : Serializable