package com.example.mazintask.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mazintask.R
import com.example.mazintask.data.models.parts.Product
import com.example.mazintask.util.GlideImageUtils
import com.example.mazintask.util.setMyText
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.row_product_hire.view.*

class ProductHireAdapter : RecyclerView.Adapter<ProductHireVH>() {

    private val productHireList: MutableList<Product> = ArrayList()

    fun updateList(productHireList: List<Product>) {
        this.productHireList.clear()
        this.productHireList.addAll(productHireList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHireVH {
        return ProductHireVH(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_product_hire, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductHireVH, position: Int) {
        holder.setData(productHireList[position])
    }

    override fun getItemCount(): Int {
        return productHireList.size
    }
}

class ProductHireVH(itemView: View) : RecyclerView.ViewHolder(itemView), LayoutContainer {

    override val containerView: View?
        get() = itemView

    private var product: Product? = null

    fun setData(product: Product?) {
        this.product = product

        GlideImageUtils.setImage(itemView.context, product?.imageUrl, "", itemView.ivProduct)
        itemView.tvProductName.setMyText(product?.name)
        itemView.tvDefaultRate.setMyText(product?.defaultRateFullText)
        itemView.tvCode.setMyText(product?.productCode)
    }
}