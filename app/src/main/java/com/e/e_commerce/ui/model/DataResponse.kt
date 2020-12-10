import com.google.gson.annotations.SerializedName


data class DataResponse (

	@SerializedName("id") val id : Int,
	@SerializedName("name") val name : String,
	@SerializedName("slug") val slug : String,
	@SerializedName("brand") val brand : List<Brand>
)