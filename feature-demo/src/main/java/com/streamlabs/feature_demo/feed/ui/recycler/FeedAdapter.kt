package com.streamlabs.feature_demo.feed.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.streamlabs.feature_demo.databinding.FeedItemBinding
import com.streamlabs.feature_demo.feed.ui.model.VideoUi

class FeedAdapter : RecyclerView.Adapter<FeedAdapter.ViewHolder>() {

    private var items: List<VideoUi> = emptyList()

    fun setItems(items: List<VideoUi>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FeedItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.run {
            descriptionText.text = items[position].videoDescription
            userNameText.text = items[position].userName
            commentsNumber.text = items[position].videoNumberComments.toString()
            likesNumber.text = items[position].videoNumberLikes.toString()
            videoView.setVideoPath(items[position].videoPath)
            videoView.start();
        }
    }

    override fun getItemCount() = items.size


    class ViewHolder(val binding: FeedItemBinding) : RecyclerView.ViewHolder(binding.root) {
    }
}