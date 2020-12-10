import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName



data class Variants(

	@SerializedName("id") val id : Int,
	@SerializedName("sku") val sku : String?,
	@SerializedName("images") val images : List<String>?,
	@SerializedName("wholesale_price") val wholesale_price : Int,
	@SerializedName("retail_price") val retail_price : Int,
	@SerializedName("attributes") val attributes : List<Attributes>?,
	@SerializedName("alert") val alert : Int,
	@SerializedName("created_at") val created_at : String?
): Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readInt(),
		parcel.readString(),
		parcel.createStringArrayList(),
		parcel.readInt(),
		parcel.readInt(),
		parcel.createTypedArrayList(Attributes),
		parcel.readInt(),
		parcel.readString()
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeInt(id)
		parcel.writeString(sku)
		parcel.writeStringList(images)
		parcel.writeInt(wholesale_price)
		parcel.writeInt(retail_price)
		parcel.writeTypedList(attributes)
		parcel.writeInt(alert)
		parcel.writeString(created_at)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Variants> {
		override fun createFromParcel(parcel: Parcel): Variants {
			return Variants(parcel)
		}

		override fun newArray(size: Int): Array<Variants?> {
			return arrayOfNulls(size)
		}
	}
}