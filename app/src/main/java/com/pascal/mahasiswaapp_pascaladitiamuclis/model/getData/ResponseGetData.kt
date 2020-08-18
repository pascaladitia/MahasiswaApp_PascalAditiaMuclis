package com.pascal.mahasiswaapp_pascaladitiamuclis.model.getData

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.pascal.mahasiswaapp_pascaladitiamuclis.model.getData.DataItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseGetData(

	@field:SerializedName("data")
	val data: List<DataItem>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("isSuccess")
	val isSuccess: Boolean? = null
) : Parcelable
