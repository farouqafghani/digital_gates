package com.example.mazintask.ui.screen.main

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mazintask.R
import com.example.mazintask.data.adapter.ProductHireAdapter
import com.example.mazintask.data.base.BaseActivity
import com.example.mazintask.data.models.response.ProductNamesResponse
import com.example.mazintask.data.models.response.SearchResponse
import com.example.mazintask.data.network.web_services.WebServiceManager
import com.example.mazintask.util.ItemPaddingDecoration
import com.example.mazintask.util.getMyText
import com.example.mazintask.util.setOnClickListener2
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executors


class MainActivity : BaseActivity() {

    companion object{
        val namesList = ArrayList<String>()
    }

    private val adapter: ProductHireAdapter by lazy {
        ProductHireAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListeners()
        setupRecyclerView()
        requestProductsName()
    }

    private fun initListeners() {
        btnSearch?.setOnClickListener2 {
            requestSearch(autoCompleteTV.getMyText())
        }
    }

    private fun requestSearch(key: String) {
        showLoadingDialog()
        WebServiceManager.getMyWebServices().searchByKey(key)
            .enqueue(object : Callback<SearchResponse> {
                override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                    hideLoadingDialog()
                    if (response.isSuccessful) {
                        adapter.updateList(response.body()?.products ?: ArrayList())
                    } else {
                        handleErrorResponse(response)
                    }
                }

                override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                    handleOnFailure(t)
                }
            })
    }

    private fun setupRecyclerView() {
        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.addItemDecoration(ItemPaddingDecoration(10, 3))
        recyclerView?.adapter = adapter
    }

    private fun requestProductsName() {
        if (!namesList.isNullOrEmpty()) {
            updateList(namesList)
            return
        }

        showLoadingDialog()
        WebServiceManager.getMyWebServices()
            .getSearchAutoCompleteProducts()
            .enqueue(object : Callback<ProductNamesResponse> {
                override fun onResponse(
                    call: Call<ProductNamesResponse>,
                    response: Response<ProductNamesResponse>
                ) {
                    hideLoadingDialog()
                    if (response.isSuccessful) {
                        Executors.newSingleThreadExecutor().execute {
                            namesList.clear()
                            response.body()?.productNameList?.forEach {
                                namesList.add(it.categoryName ?: "")
                            }
                            runOnUiThread { updateList(namesList) }
                        }
                    } else {
                        handleErrorResponse(response)
                    }
                }

                override fun onFailure(call: Call<ProductNamesResponse>, t: Throwable) {
                    handleOnFailure(t)
                }
            })
    }

    private fun updateList(list: ArrayList<String>) {
        val adapter: ArrayAdapter<String> = ArrayAdapter(
            this@MainActivity,
            android.R.layout.simple_dropdown_item_1line, list
        )
        autoCompleteTV.setAdapter(adapter)
    }
}
