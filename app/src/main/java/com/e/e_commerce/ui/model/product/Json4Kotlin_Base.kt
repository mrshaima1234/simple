import com.google.gson.annotations.SerializedName



data class Json4Kotlin_Base (

	@SerializedName("data") val data : List<Data>,
	@SerializedName("links") val links : Links,
	@SerializedName("meta") val meta : Meta,
	@SerializedName("status") val status : Boolean,
	@SerializedName("code") val code : Int,
	@SerializedName("error") val error : String
)