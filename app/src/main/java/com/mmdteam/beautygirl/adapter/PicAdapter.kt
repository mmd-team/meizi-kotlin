package com.mmdteam.beautygirl.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.mmdteam.beautygirl.R
import com.mmdteam.beautygirl.mvp.model.bean.HomeBean
import com.squareup.picasso.Picasso

/**
 * Created by brain on 2017/8/28.
 * Home RecycleView Adapter
 */
class PicAdapter(context: Context, list: ArrayList<HomeBean.PicBean>) : RecyclerView.Adapter<PicAdapter.HomeViewHolder>() {


    var context: Context? = null
    private var list: ArrayList<HomeBean.PicBean>? = null
    private var inflater: LayoutInflater? = null
    var mCallback: CallBack? = null

    fun setCallBack(callBack: CallBack) {
        this.mCallback = callBack
    }

    init {
        this.context = context
        this.list = list
        this.inflater = LayoutInflater.from(context)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        list?.let { holder.update(it, position) }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(inflater?.inflate(R.layout.item_home, parent, false)!!, context!!)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }


    inner class HomeViewHolder(itemView: View, context: Context) : RecyclerView.ViewHolder(itemView) {

        private var itemText: TextView
        private var itemImage: ImageView
        var context: Context

        init {
            itemText = itemView.findViewById(R.id.itemText)
            itemImage = itemView.findViewById(R.id.itemImage)
            this.context = context
        }


        fun update(list: MutableList<HomeBean.PicBean>, position: Int) {
            val bean = list[position]
            itemText.text = bean.title
            Picasso.get().load(bean.thumb_url).into(itemImage)
            itemView.setOnClickListener {
                mCallback!!.onThumbPictureClick(itemImage, bean.image_url!!)
            }
        }

    }

    interface CallBack {
        fun onThumbPictureClick(imageView: ImageView, url: String)
    }
}