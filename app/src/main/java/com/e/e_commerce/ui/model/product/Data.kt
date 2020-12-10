import com.google.gson.annotations.SerializedName



data class Data (

	@SerializedName("id") val id : Int,
	@SerializedName("name") val name : String,
	@SerializedName("images") val images : List<String>,
	@SerializedName("created") val created : String,
	@SerializedName("brand") val brand : Brand,
	@SerializedName("brand_type") val brand_type : Brand,
	@SerializedName("category") val category : Category,
	@SerializedName("stock") val stock : List<String>,
	@SerializedName("variants") val variants : ArrayList<Variants>
)