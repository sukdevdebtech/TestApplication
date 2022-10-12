package com.testapplication.model

import com.google.gson.annotations.SerializedName

data class ListResponse(

	@field:SerializedName("ListResponse")
	val listResponse: MutableList<ListResponseItem>? = null
)

data class ListResponseItem(

	@field:SerializedName("author")
	val author: String? = null,

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("download_url")
	val downloadUrl: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("height")
	val height: Int? = null
)
