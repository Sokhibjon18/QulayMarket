package uz.triples.qulaymarket.network.pojo_objects

import com.google.gson.annotations.SerializedName

data class LoginWithEmailOrPhoneResponse(

	@field:SerializedName("result", alternate = ["err_str"])
	val result: String? = null,

	@field:SerializedName("ok")
	val ok: Boolean? = null
)
