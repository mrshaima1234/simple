package com.e.e_commerce.ui.main.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.e_commerce.R
import com.e.e_commerce.base.BaseFragment
import com.e.e_commerce.interfaces.CommonInterface
import com.e.e_commerce.ui.main.ViewPagerAdapter
import com.e.e_commerce.ui.main.viewmodel.CommonViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_recycler_view.*

class HomeFragment : BaseFragment(),CommonInterface {
    lateinit var adapter: ProductAdapter
    override val layoutId: Int
        get() = R.layout.fragment_recycler_view
    companion object {
        fun newInstance() = HomeFragment()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpProductList()
    }

    private fun setUpProductList() {
        val commonViewModel = CommonViewModel(this)
        commonViewModel.getProductList(5,1)
            .observe(this, Observer {
                if (it.status) {
                    rvProducts.layoutManager = GridLayoutManager(activity,2)
                    adapter = ProductAdapter(activity)
                    rvProducts.adapter = adapter
                    adapter.addItems(it.data)
                } else {
                    Toast.makeText(activity, it.error, Toast.LENGTH_SHORT).show()
                }
            })
    }

    override fun onFailure() {
        Toast.makeText(activity, "Something went wrong", Toast.LENGTH_SHORT).show()

    }

    override fun onShowProgress() {
        showProgress()

    }

    override fun onDismissProgress() {
        dismissProgress()
    }

}