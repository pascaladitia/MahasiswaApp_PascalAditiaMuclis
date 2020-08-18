package com.pascal.mahasiswaapp_pascaladitiamuclis.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pascal.mahasiswaapp_pascaladitiamuclis.R
import com.pascal.mahasiswaapp_pascaladitiamuclis.model.getData.DataItem
import kotlinx.android.synthetic.main.item_list.view.*

class AdapterMain(val data: List<DataItem>?,
                  val itemClick: OnClickListener) : RecyclerView.Adapter<AdapterMain.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterMain.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data?.size ?: 0

    override fun onBindViewHolder(holder: AdapterMain.ViewHolder, position: Int) {
        val item = data?.get(position)

        holder.nama.text = item?.mahasiswaNama
        holder.nohp.text = item?.mahasiswaNohp
        holder.alamat.text = item?.mahasiswaAlamat

        holder.itemView.setOnClickListener {
            itemClick.detail(item)
        }

        holder.btnDelete.setOnClickListener {
            itemClick.delete(item)
        }
    }

    inner class ViewHolder(view: View) :RecyclerView.ViewHolder(view) {
        val nama = view.item_nama
        val nohp = view.item_nohp
        val alamat = view.item_alamat
        val btnDelete = view.btn_delete
    }

    interface OnClickListener {
        fun detail(item: DataItem?)
        fun delete(item: DataItem?)
    }
}