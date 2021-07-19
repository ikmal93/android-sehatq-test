package com.ikmal.androidsehatqtest.data.local.model.entity.productpromo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ikmal.androidsehatqtest.data.api.model.ProductPromo
import java.io.Serializable

@Entity(tableName = "product_promo_entity")
data class ProductPromoEntity(
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
    @ColumnInfo(name = "title")
    val title: String
) : Serializable {
    companion object {
        fun toProductPromo(productPromoEntity: ProductPromoEntity): ProductPromo {
            return ProductPromo(
                id = productPromoEntity.id,
                description = productPromoEntity.description,
                imageUrl = productPromoEntity.imageUrl,
                loved = productPromoEntity.loved,
                price = productPromoEntity.price,
                title = productPromoEntity.title
            )
        }
    }
}