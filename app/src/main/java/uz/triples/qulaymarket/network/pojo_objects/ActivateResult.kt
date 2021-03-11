package uz.triples.qulaymarket.network.pojo_objects

import com.google.gson.annotations.SerializedName

data class ActivateResult(

	@field:SerializedName("result")
	val result: Boolean? = null,

	@field:SerializedName("ok")
	val ok: Boolean? = null
)
