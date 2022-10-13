package com.example.memesapp.adapters

import com.example.memesapp.model.Meme

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.memesapp.R
import com.squareup.picasso.Picasso


class ViewPagerAdapter(val context: Context, val memes : List<Meme>): RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    class ViewPagerViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        val imageview : ImageView = itemView.findViewById<ImageView>(R.id.memeimage)
        val title : TextView = itemView.findViewById(R.id.memeName)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.memes_item,parent,false)
        return ViewPagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val curMeme = memes[position]
        Picasso.with(context).load(curMeme.url).into(holder.imageview)
        holder.title.text = curMeme.name
    }

    override fun getItemCount(): Int {
        return memes.size
    }
}



