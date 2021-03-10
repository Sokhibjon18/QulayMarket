package uz.triples.qulaymarket.network.pojo_objects

import com.google.gson.annotations.SerializedName

data class GetAllAnnouncements(

    @field:SerializedName("result", alternate = ["err_str"])
    val result: List<Announcement>? = null,

    @field:SerializedName("ok")
    val ok: Boolean? = null
)