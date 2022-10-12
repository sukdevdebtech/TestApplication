package com.testapplication.view

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.testapplication.R
import com.testapplication.model.ListResponseItem
import java.text.SimpleDateFormat
import java.util.*

class ListAdapter(
    var activity: Activity?,
    var mList: MutableList<ListResponseItem>,

    ) : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvTitle.text=mList[position].author.toString()

        Glide.with(activity!!).load(mList.get(position).downloadUrl).placeholder(R.drawable.ic_launcher_background).into(holder.image)

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image: AppCompatImageView = view.findViewById(R.id.ImageView)
        var tvTitle: AppCompatTextView = view.findViewById(R.id.tvTilte)


    }



}