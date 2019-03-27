package com.example.recyclertask.vo

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "images")
data class ImageInfo(
    @SerializedName("format")
    val format : String,
    @SerializedName("width")
    val width : Int,
    @SerializedName("height")
    val height : Int,
    @SerializedName("filename")
    val name : String,
    @SerializedName("id")
    val id : Int,
    @SerializedName("author")
    val author : String,
    @SerializedName("author_url")
    val author_url : String,
    @SerializedName("post_url")
    val post_url : String)