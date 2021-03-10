package uz.triples.qulaymarket.network.pojo_objects

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Announcement(

	@field:SerializedName("owner")
	val owner: Owner? = null,

	@field:SerializedName("datetime")
	val datetime: Int? = null,

	@field:SerializedName("comments")
	val comments: Comments? = null,

	@field:SerializedName("price")
	val price: Double? = null,

	@field:SerializedName("images_count")
	val imagesCount: Int? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("location")
	val location: Location? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("tag")
	val tag: Tag? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("views")
	val views: Int? = null
) :Parcelable{
	constructor(parcel: Parcel) : this() {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {

	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Announcement> {
		override fun createFromParcel(parcel: Parcel): Announcement {
			return Announcement(parcel)
		}

		override fun newArray(size: Int): Array<Announcement?> {
			return arrayOfNulls(size)
		}
	}
}

data class Owner(

	@field:SerializedName("birthdate")
	val birthdate: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("email")
	val email: String? = null
)

data class From(

	@field:SerializedName("birthdate")
	val birthdate: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("email")
	val email: String? = null
)

data class Location(

	@field:SerializedName("value_uz")
	val valueUz: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("value")
	val value: String? = null,

	@field:SerializedName("items")
	val items: List<Any?>? = null
)

data class Tag(

	@field:SerializedName("value_uz")
	val valueUz: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("value")
	val value: String? = null,

	@field:SerializedName("items")
	val items: List<Any?>? = null
)

data class Replies(

	@field:SerializedName("per_page")
	val perPage: Int? = null,

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("current_page")
	val currentPage: List<CurrentPageItem?>? = null
)

data class CurrentPageItem(

	@field:SerializedName("datetime")
	val datetime: Double? = null,

	@field:SerializedName("replies")
	val replies: Replies? = null,

	@field:SerializedName("from")
	val from: From? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("text")
	val text: String? = null
)

data class Comments(

	@field:SerializedName("per_page")
	val perPage: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("current_page")
	val currentPage: List<CurrentPageItem?>? = null
)
