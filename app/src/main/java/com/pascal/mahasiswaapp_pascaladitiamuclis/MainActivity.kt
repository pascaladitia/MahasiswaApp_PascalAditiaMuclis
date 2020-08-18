package com.pascal.mahasiswaapp_pascaladitiamuclis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.pascal.mahasiswaapp_pascaladitiamuclis.adapter.AdapterMain
import com.pascal.mahasiswaapp_pascaladitiamuclis.config.NetworkModule
import com.pascal.mahasiswaapp_pascaladitiamuclis.model.action.ResponseAction
import com.pascal.mahasiswaapp_pascaladitiamuclis.model.getData.DataItem
import com.pascal.mahasiswaapp_pascaladitiamuclis.model.getData.ResponseGetData
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_floating.setOnClickListener {
            val intent = Intent(this@MainActivity, InputActivity::class.java)
            startActivity(intent)
        }

        showData()
    }

    private fun showData() {
        val listAnggota = NetworkModule.service().getData()
        listAnggota.enqueue(object : Callback<ResponseGetData> {
            override fun onFailure(call: Call<ResponseGetData>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<ResponseGetData>,
                response: Response<ResponseGetData>
            ) {
                if (response.isSuccessful) {
                    val item = response.body()?.data
                    val adapter = AdapterMain(item, object : AdapterMain.OnClickListener {
                        override fun detail(item: DataItem?) {
                            val intent = Intent(applicationContext, InputActivity::class.java)
                            intent.putExtra("data", item)
                            startActivity(intent)
                        }

                        override fun delete(item: DataItem?) {
                            AlertDialog.Builder(this@MainActivity).apply {
                                setTitle("Delete Data")
                                setMessage("apakah anda yakin ingin menghapus?")
                                setPositiveButton("Delete") {dialogInterface, i ->
                                    hapusData(item?.idMahasiswa)
                                    dialogInterface.dismiss()
                                }
                                setNegativeButton("Cancel") {dialogInterface, i ->
                                    dialogInterface.dismiss()
                                }
                            }.show()
                        }

                    })

                    recyclerview_main.adapter = adapter
                }
            }

        })
    }

    private fun hapusData(id: String?) {
        val hapus = NetworkModule.service().deleteData(id ?: "")
        hapus.enqueue(object : Callback<ResponseAction> {
            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<ResponseAction>,
                response: Response<ResponseAction>
            ) {
                Toast.makeText(applicationContext, "Data berhasil dihapus", Toast.LENGTH_SHORT).show()
                showData()
            }

        })
    }

    override fun onResume() {
        super.onResume()
        showData()
    }
}