package com.pascal.mahasiswaapp_pascaladitiamuclis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.pascal.mahasiswaapp_pascaladitiamuclis.config.NetworkModule
import com.pascal.mahasiswaapp_pascaladitiamuclis.model.action.ResponseAction
import com.pascal.mahasiswaapp_pascaladitiamuclis.model.getData.DataItem
import kotlinx.android.synthetic.main.activity_input.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InputActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        val getData = intent.getParcelableExtra<DataItem>("data")
        if (getData != null) {
            edt_nama.setText(getData.mahasiswaNama)
            edt_nohp.setText(getData.mahasiswaNohp)
            edt_alamat.setText(getData.mahasiswaAlamat)

            btn_simpan.text = "Update"
        }

        when (btn_simpan.text) {
            "Update" -> {
                btn_simpan.setOnClickListener {
                    updateData(
                        getData?.idMahasiswa,
                        edt_nama.text.toString(),
                        edt_nohp.text.toString(),
                        edt_alamat.text.toString()
                    )
                }
            }
            else -> {
                btn_simpan.setOnClickListener {
                    inputData(
                        edt_nama.text.toString(),
                        edt_nohp.text.toString(),
                        edt_alamat.text.toString()
                    )
                }
            }
        }

        btn_cancel.setOnClickListener {
            finish()
        }
    }

    private fun inputData(nama: String?, nohp: String?, alamat: String?) {
        val input = NetworkModule.service().insertData(nama ?: "", nohp ?: "", alamat ?: "")
        input.enqueue(object : Callback<ResponseAction> {
            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<ResponseAction>,
                response: Response<ResponseAction>
            ) {
                Toast.makeText(applicationContext, "Data berhasil disimpan", Toast.LENGTH_SHORT)
                    .show()
                finish()
            }

        })
    }

    private fun updateData(id: String?, nama: String?, nohp: String?, alamat: String?) {
        val input =
            NetworkModule.service().updateData(id ?: "", nama ?: "", nohp ?: "", alamat ?: "")
        input.enqueue(object : Callback<ResponseAction> {
            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<ResponseAction>,
                response: Response<ResponseAction>
            ) {
                Toast.makeText(applicationContext, "Data berhasil diupdate", Toast.LENGTH_SHORT)
                    .show()
                finish()
            }

        })
    }
}