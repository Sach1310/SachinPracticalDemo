package com.example.sachinpracticle.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.example.sachinpracticle.R
import com.example.sachinpracticle.databinding.RowImagesBinding
import com.example.sachinpracticle.model.DataModel

class ImagesAdapter(
    private val context: Context,
    private val modelList: ArrayList<DataModel>,
    private val listener:OnClickListener
) : RecyclerView.Adapter<ImagesAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_images, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.binding?.apply {
            Glide.with(context)
                .load(modelList.get(position).url)
                .priority(Priority.IMMEDIATE)
                .placeholder(R.drawable.image)
                .into(ivImages)
        }

        holder.itemView.setOnClickListener {
            listener.onClick(holder.adapterPosition)
        }

    }

    override fun getItemCount(): Int {
        return modelList.size
    }

    inner class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        var binding: RowImagesBinding? = DataBindingUtil.bind(itemView!!)
    }

    interface OnClickListener{
        fun onClick(position: Int)
    }
}