package uz.triples.qulaymarket.network.pojo_objects

import com.google.gson.annotations.SerializedName

data class GetTagsResponse(

	@field:SerializedName("result")
	val result: List<ResultItem?>? = null,

	@field:SerializedName("ok")
	val ok: Boolean? = null
)

data class ResultItem(

	@field:SerializedName("value_uz")
	val valueUz: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("value")
	val value: String? = null,

	@field:SerializedName("items")
	val items: List<ItemsItem?>? = null
)

data class ItemsItem(

	@field:SerializedName("value_uz")
	val valueUz: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("value")
	val value: String? = null,

	@field:SerializedName("items")
	val items: List<Any?>? = null
)
