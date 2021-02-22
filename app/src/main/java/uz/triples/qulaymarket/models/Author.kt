package uz.triples.qulaymarket.models

import com.google.gson.annotations.SerializedName

data class Author(

    @field:SerializedName("number")
    val number: String? = null,

    @field:SerializedName("last_seen")
    val lastSeen: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("location")
    val location: String? = null,

    @field:SerializedName("id")
    val id: Int? = null
)