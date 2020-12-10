package com.e.e_commerce.ui.main.home

import Data
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.e.e_commerce.R
import com.e.e_commerce.base.BaseRecyclerViewAdapter
import com.e.e_commerce.ui.main.product.ProductDetailsActivity
import kotlinx.android.synthetic.main.item_product.view.*

class ProductAdapter (private  val  activity: FragmentActivity?) : BaseRecyclerViewAdapter<Data>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ProductViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_product, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var chapterHolder = holder as? ProductViewHolder
        chapterHolder!!.bindItem(getItem(position),position)


    }

    inner class ProductViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(item: Data?, position: Int) {
           itemView.tvName.text=item?.name
            if(item?.variants?.isNotEmpty()!!)
           itemView.tvPrice.text="Rs."+item?.variants?.get(0)?.retail_price.toString()
            itemView.setOnClickListener{
                Log.e("Size 1",item?.variants.size.toString())

                val intent = Intent(activity,ProductDetailsActivity::class.java)
                intent.putExtra("PRODUCT_NAME",item?.name)
                intent.putParcelableArrayListExtra("PRODUCT_DETAILS",item?.variants)
                activity?.startActivity(intent)
            }
        }
    }
}