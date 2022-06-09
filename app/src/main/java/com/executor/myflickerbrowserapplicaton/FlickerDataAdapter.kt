package com.executor.myflickerbrowserapplicaton

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.executor.myflickerbrowserapplicaton.Model.FlickerDataModel

class FlickerDataAdapter(
    private val context: Context,
    private val flickerDataModelList: ArrayList<FlickerDataModel>,
    private val moProductClickListener: OnItemClickListener? = null,
) : RecyclerView.Adapter<FlickerDataAdapter.DataViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_data, parent, false)
        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val myData = flickerDataModelList[position]
        holder.title.text = myData.title
        Glide.with(context).load(myData.media?.m).into(holder.thumbnail)

        holder.llProductData.setOnClickListener {
            moProductClickListener?.onItemClick(holder.adapterPosition)
        }
    }


    override fun getItemCount(): Int {
        return flickerDataModelList.size
    }

    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
        {
        var title: TextView = itemView.findViewById(R.id.title)
        var thumbnail: ImageView = itemView.findViewById(R.id.thumbnail)
        val llProductData: LinearLayout = itemView.findViewById(R.id.llProductData)

//        init {
//            itemView.setOnClickListener(this)
//        }

//        override fun onClick(v: View?) {
//            val position = adapterPosition
//            if (position != RecyclerView.NO_POSITION)
//                moProductClickListener?.onItemClick(position)
//        }
    }

    interface OnItemClickListener {
        fun onItemClick(fiPosition: Int)
    }
}