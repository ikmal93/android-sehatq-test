package com.ikmal.androidsehatqtest.data.local.model.entity.history

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "history_entity")
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "trxId")
    var trxId: Int = 0,
    @ColumnInfo(name = "id")
    var id: String = "",
    @ColumnInfo(name = "description")
    var description: String = "",
    @ColumnInfo(name = "image_url")
    var imageUrl: String = "",
    @ColumnInfo(name = "loved")
    var loved: Int = 0,
    @ColumnInfo(name = "price")
    var price: String = "",
    @ColumnInfo(name = "title")
    var title: String = ""
) : Serializable