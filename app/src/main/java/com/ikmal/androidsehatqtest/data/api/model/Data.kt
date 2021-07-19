package com.ikmal.androidsehatqtest.data.api.model

import java.io.Serializable

data class Data(
    val category: List<Category>,
    val productPromo: List<ProductPromo>
) : Serializable