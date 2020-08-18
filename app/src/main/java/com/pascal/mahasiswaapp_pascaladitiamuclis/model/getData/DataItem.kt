package com.pascal.mahasiswaapp_pascaladitiamuclis.model.getData

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataItem(

    @field:SerializedName("id_mahasiswa")
    val idMahasiswa: String? = null,

    @field:SerializedName("mahasiswa_nama")
    val mahasiswaNama: String? = null,

    @field:SerializedName("mahasiswa_nohp")
    val mahasiswaNohp: String? = null,

    @field:SerializedName("mahasiswa_alamat")
    val mahasiswaAlamat: String? = null
) : Parcelable