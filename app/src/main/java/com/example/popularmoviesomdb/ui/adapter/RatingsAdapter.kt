package com.example.popularmoviesomdb.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.popularmoviesomdb.R
import com.example.popularmoviesomdb.ui.adapter.RatingsAdapter.MyViewHolder
import com.example.popularmoviesomdb.model.Ratings
import kotlinx.android.synthetic.main.rv_item.view.*

class RatingsAdapter(private val ratingsList: List<Ratings>) :
    RecyclerView.Adapter<MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var source = view.tv_source
        var value = view.tv_value
        var cardView = view.card_view_review

        init {
            cardView.radius = 30f
            cardView.cardElevation = 10f
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val ratings = ratingsList[position]
        holder.source.text = ratings.source
        holder.value.text = ratings.value
    }

    override fun getItemCount(): Int {
        return ratingsList.size
    }

}