package com.pascal.mahasiswaapp_pascaladitiamuclis.config

import com.pascal.mahasiswaapp_pascaladitiamuclis.model.action.ResponseAction
import com.pascal.mahasiswaapp_pascaladitiamuclis.model.getData.ResponseGetData
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    //getData
    @GET("getData.php")
    fun getData(): Call<ResponseGetData>

    //getDataById
    @GET("getData.php")
    fun getDataById(@Query("mahasiswa_id") id: String) : Call<ResponseGetData>

    //insert
    @FormUrlEncoded
    @POST("insert.php")
    fun insertData( @Field("mahasiswa_nama") nama: String,
                    @Field("mahasiswa_nohp") nohp: String,
                    @Field("mahasiswa_alamat") alamat: String) : Call<ResponseAction>

    //update
    @FormUrlEncoded
    @POST("update.php")
    fun updateData( @Field("id_mahasiswa") id: String,
                    @Field("mahasiswa_nama") nama: String,
                    @Field("mahasiswa_nohp") nohp: String,
                    @Field("mahasiswa_alamat") alamat: String) : Call<ResponseAction>

    @FormUrlEncoded
    @POST("delete.php")
    fun deleteData(@Field("id_mahasiswa") id: String) : Call<ResponseAction>
}