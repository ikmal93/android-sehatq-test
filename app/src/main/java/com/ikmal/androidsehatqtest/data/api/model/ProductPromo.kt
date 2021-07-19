package com.ikmal.androidsehatqtest.features.home.data.api.data

import androidx.room.Entity
import java.io.Serializable

@Entity(tableName = "product_promo")
data class ProductPromo(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    @ColumnInfo(name = "loved")
    val loved: Int,
    @ColumnInfo(name = "price")
    val price: String,
    val title: String
) : Serializable