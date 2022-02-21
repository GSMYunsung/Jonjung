package com.pss.jonjung.view.adapter

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.pss.jonjung.R
import com.pss.jonjung.data.db.entity.Post
import com.pss.jonjung.data.db.entity.TodayPost
import com.pss.jonjung.view.CustomDialog
import com.pss.jonjung.viewmodel.PostViewModel
import org.w3c.dom.Text

class ToDayRecordAdapter(postList: ArrayList<TodayPost>, fragment : Fragment) : RecyclerView.Adapter<ToDayRecordAdapter.PagerViewHolder>() {

    var item = postList
    var fragment = fragment

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PagerViewHolder((parent))

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.title.text = item[position].title

        holder.star.text = item[position].star

        holder.starContent.setOnClickListener {
            CustomDialog(fragment.requireContext()).showDialog(item[position].title,item[position].content)
        }

    }

    inner class PagerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.to_day_record_item, parent, false)){

        val starContent = itemView.findViewById<ConstraintLayout>(R.id.star_content)
        val title = itemView.findViewById<TextView>(R.id.title_textview1)
        val star = itemView.findViewById<TextView>(R.id.star_textview)
    }
}