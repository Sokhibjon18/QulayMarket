package uz.triples.qulaymarket.models

import com.google.gson.annotations.SerializedName

data class Goods(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("author")
	val author: Author? = null,

	@field:SerializedName("currency")
	val currency: String? = null,

	@field:SerializedName("location")
	val location: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("views")
	val views: Int? = null
)


