package com.pss.jonjung.view.adapter

import android.graphics.Color
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.pss.jonjung.R
import com.pss.jonjung.data.db.entity.Post
import com.pss.jonjung.viewmodel.PostViewModel

class MainViewPagerAdapter(postList: ArrayList<Post>, fragment : Fragment, mainViewModel : PostViewModel ) : RecyclerView.Adapter<MainViewPagerAdapter.PagerViewHolder>() {

    var item = postList
    var fragment = fragment
    var mainViewModel = mainViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PagerViewHolder((parent))

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.title.setText(item.get(position).title)
        holder.content.setText(item.get(position).content)

            if(item.get(position).photoIs){
                mainViewModel.getPhoto(mainViewModel.postList.get(position).title)

                Glide.with(fragment)
                    .load(mainViewModel.photoUri.value)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(40)))
                    .into(holder.img)

                holder.img.setBackgroundColor(Color.WHITE)
        }
    }

    inner class PagerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.board_item, parent, false)){

        val title = itemView.findViewById<TextView>(R.id.title_textview)
        val img = itemView.findViewById<ImageView>(R.id.card_imageview)
        val content = itemView.findViewById<TextView>(R.id.content_textview)
    }
}