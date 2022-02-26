package com.pss.jonjung.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.pss.jonjung.R
import com.pss.jonjung.data.db.entity.TodayPost
import com.pss.jonjung.data.db.entity.VideoPost
import com.pss.jonjung.view.VideoCustiomDialog
import com.pss.jonjung.viewmodel.PostViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.w3c.dom.Text

class CheeringAdapter(postList: ArrayList<VideoPost>, fragment : Fragment, viewmodel : PostViewModel) : RecyclerView.Adapter<CheeringAdapter.PagerViewHolder>() {

    var item = postList
    var fragment = fragment
    var viewmodel = viewmodel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PagerViewHolder((parent))

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.title.text = item[position].title

        holder.content.setOnClickListener {
            viewmodel.getVideo(viewmodel.videoPostList[position].title)
            viewmodel.videoName(viewmodel.videoPostList[position].title)
            viewmodel.videoPostPosition(viewmodel.videoPostList[position].title)

                CoroutineScope(Dispatchers.Main).launch { delay(1300)
                VideoCustiomDialog(fragment.requireContext()).showDialog(item[position].title,fragment.requireContext(), viewmodel.videoUri.value!!,viewmodel,fragment)
                }
        }

    }

    inner class PagerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.video_item, parent, false)){

        val title = itemView.findViewById<TextView>(R.id.video_title_textview)
        val content = itemView.findViewById<ConstraintLayout>(R.id.video_content)
    }
}