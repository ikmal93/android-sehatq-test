package com.ikmal.androidsehatqtest.data.api.model

import java.io.Serializable

data class Category(
    val id: Int,
    val imageUrl: String,
    val name: String
) : Serializable