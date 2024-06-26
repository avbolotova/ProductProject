package com.example.testvkproject.ui.main.adapter

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testvkproject.R
import com.example.testvkproject.domain.model.Product
import com.example.testvkproject.ui.details.DetailsFragment

class SearchAdapter(private val fragmentManager: FragmentManager): RecyclerView.Adapter<SearchAdapter.MyViewHolder>() {


    private var listProducts = emptyList<Product>()

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tvName = view.findViewById<TextView>(R.id.tvName)
        val tvDescriptionItem = view.findViewById<TextView>(R.id.tvDescriptionItem)
        val buttonNext = view.findViewById<AppCompatButton>(R.id.buttonNext)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product_search, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        Glide.with(holder.itemView.context)
            .load(listProducts[position].thumbnail)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.itemView.findViewById(R.id.imageItem))

        holder.tvName.apply {
            text = listProducts[position].title
            ellipsize = TextUtils.TruncateAt.END
            maxLines = 1
        }

        holder.tvDescriptionItem.apply {
            text = listProducts[position].description
            ellipsize = TextUtils.TruncateAt.END
            maxLines = 2
        }
        holder.buttonNext.setOnClickListener {
            val bundle = Bundle().apply {
                putSerializable("productSearch", listProducts[position])
            }
            val detailsFragment = DetailsFragment().apply {
                arguments = bundle
            }
            fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, detailsFragment)
                .addToBackStack(null)
                .commit()
        }

    }

    override fun getItemCount(): Int {
        return listProducts.size
    }

    fun submitList(list: List<Product>) {
        listProducts = list
        notifyDataSetChanged()
    }
}