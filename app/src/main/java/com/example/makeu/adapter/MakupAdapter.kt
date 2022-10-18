package com.example.makeu.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.makeu.R
import com.example.makeu.model.MakeupItem

class MakupAdapter  ()  : RecyclerView.Adapter<MakupAdapter.ViewHolder>() {

    private var listData: List<MakeupItem>? = null
    fun setListData(listData: List<MakeupItem>?) {
        this.listData = listData
    }

    var position: Int = 0

    class ViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView)  {
        var textName: TextView = itemView.findViewById(R.id.txt_name)
        var textBrand: TextView = itemView.findViewById(R.id.txt_brand)
        var textDescription: TextView =itemView.findViewById(R.id.txt_description)
        var iv_image = itemView.findViewById<ImageView>(R.id.iv_makeup)
        @SuppressLint("SuspiciousIndentation", "SetTextI18n")
        fun bindData(makList: MakeupItem) {
            textName.text = "Name:-  ${makList!!.name}"
            textBrand.text = "Brand:-  ${makList!!.brand}"
            textDescription.text="Description:-   ${makList!!.description}"
            Glide.with(itemView).load(makList.image_link)
                .apply(RequestOptions().centerCrop())
                .into(iv_image)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bindData(makeipList, position)
        holder.bindData(listData?.get(position)!!)
    }

    override fun getItemCount(): Int {
        if(listData == null )return 0
        return listData?.size!!
    }

}