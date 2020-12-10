package com.e.e_commerce.ui.main.product

import Variants
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.e_commerce.R
import com.e.e_commerce.base.BaseActivity
import com.e.e_commerce.interfaces.CommonInterface
import kotlinx.android.synthetic.main.activity_product.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class ProductDetailsActivity : BaseActivity(),CommonInterface{
    lateinit var adapter: ListAdapter


    override val layoutId: Int
        get() = R.layout.activity_product
    override val setToolbar: Boolean
        get() = false
    override val hideStatusBar: Boolean
        get() = false

    override fun onFailure() {
    }

    override fun onShowProgress() {
        showProgress()
    }

    override fun onDismissProgress() {
        dismissProgress()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tvToolbarTitle.text = "Product Details"

        val variants = intent.getParcelableArrayListExtra<Variants>("PRODUCT_DETAILS")
        Log.e("Size",variants.size.toString())
        tvName.text = intent.getStringExtra("PRODUCT_NAME")
        tvVariants.text = variants.size.toString()
        var totPrice : Int = 0
        for(i in variants.indices)
            totPrice += variants[i].retail_price
        tvTotPrice.text = variants.size.toString()+" items Rs."+totPrice


        rv_list.layoutManager = LinearLayoutManager(this)
        adapter = ListAdapter(this)
        rv_list.adapter = adapter
        adapter.addItems(variants)
    }

}