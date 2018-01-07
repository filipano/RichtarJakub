package com.pepcox.richtar.richtarjakub.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "beer_history")
class BeerHistory(
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        @ColumnInfo
        var name: String,
        @ColumnInfo
        var image: String,
        @ColumnInfo
        var time: String
)
