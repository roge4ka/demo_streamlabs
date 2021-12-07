package com.streamlabs.feature_demo.feed.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
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
        val playerView = ExoPlayer.Builder(parent.context)
            .build()

        return ViewHolder(binding, playerView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.run {
            descriptionText.text = items[position].videoDescription
            userNameText.text = items[position].userName
            commentsNumber.text = items[position].videoNumberComments.toString()
            likesNumber.text = items[position].videoNumberLikes.toString()
            videoView.player = holder.playerView

            val mediaItem: MediaItem = MediaItem.fromUri(items[position].videoPath!!)
            holder.playerView.setMediaItem(mediaItem)
            holder.playerView.prepare()
            holder.playerView.play()
        }
    }

    override fun getItemCount() = items.size


    class ViewHolder(
        val binding: FeedItemBinding,
        val playerView: ExoPlayer
    ) : RecyclerView.ViewHolder(binding.root) {
    }
}