package com.pss.jonjung.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.pss.jonjung.R
import com.pss.jonjung.data.db.entity.Post
import com.pss.jonjung.data.db.entity.VideoPost
import com.pss.jonjung.view.VideoCustiomDialog
import com.pss.jonjung.viewmodel.PostViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class NoticeBoardAdapter(postList: ArrayList<Post>, fragment : Fragment, viewmodel : PostViewModel) : RecyclerView.Adapter<NoticeBoardAdapter.PagerViewHolder>() {

    var item = postList
    var fragment = fragment
    var viewmodel = viewmodel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PagerViewHolder((parent))

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.title.text = item[position].title
        holder.date.text = item[position].date
        viewmodel.getPhoto("파이팅")

        holder.content.setOnClickListener {

                    viewmodel.setListAndPageAndPicture(item[position].title,item[position].content,item[position].date,item[position].photoIs)

                    fragment.requireView().findNavController().navigate(R.id.action_mainFragment_to_noticeBoardViewFragment)

        }


    }

    inner class PagerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.video_item, parent, false)){

        val title = itemView.findViewById<TextView>(R.id.video_title_textview)
        val content = itemView.findViewById<ConstraintLayout>(R.id.video_content)
        val date = itemView.findViewById<TextView>(R.id.textView8)
    }
}