package com.e.e_commerce.ui.main.product

import Variants
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.e.e_commerce.R
import com.e.e_commerce.base.BaseRecyclerViewAdapter
import kotlinx.android.synthetic.main.item_list.view.*

class ListAdapter(private val activity: FragmentActivity?) : BaseRecyclerViewAdapter<Variants>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(parent?.context).inflate(R.layout.item_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var productHolder = holder as? ProductViewHolder
        getItem(position)?.let { productHolder!!.bindItem(it, position) }


    }

    inner class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(item: Variants, position: Int) {
            itemView.tvId.text = item.sku
            if (item.attributes?.get(0)?.attribute?.name.equals("color"))
                itemView.tv_color.text = "Color: "+item.attributes?.get(0)?.value
            else if (item.attributes?.get(0)?.attribute?.name.equals("size"))
                itemView.tv_size.text ="Size: "+ item.attributes?.get(0)?.value

                itemView.tv_prize.text = "Price: "+item.retail_price.toString()

        }
    }
}