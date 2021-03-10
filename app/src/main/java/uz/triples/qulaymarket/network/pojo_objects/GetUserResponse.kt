package uz.triples.qulaymarket.network.pojo_objects

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class GetUserResponse(
	@field:SerializedName("result")
	val result: User? = null,

	@field:SerializedName("ok")
	val ok: Boolean? = null
)

class User(

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
) : Parcelable{
	constructor(parcel: Parcel) : this() {
	}

	companion object CREATOR : Parcelable.Creator<User> {
		override fun createFromParcel(parcel: Parcel): User {
			return User(parcel)
		}

		override fun newArray(size: Int): Array<User?> {
			return arrayOfNulls(size)
		}
	}

	override fun describeContents(): Int {

		return 0
	}

	override fun writeToParcel(dest: Parcel?, flags: Int) {

	}
}
