package com.fanilo.videolist.feature

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fanilo.videolist.R
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.util.MimeTypes

class VideoListAdapter(private val context: Context) : RecyclerView.Adapter<VideoListAdapter.ViewHolder>() {

    private var player: SimpleExoPlayer? = null

    private val videoItems: MutableList<VideoViewState> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.video_item_layout, parent, false)

        player = SimpleExoPlayer.Builder(context).build()

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = videoItems[position]
        holder.bindTo(item)
    }

    fun addAll(items: List<VideoViewState>) {
        videoItems.clear()
        videoItems.addAll(items)
        notifyDataSetChanged()
    }

    override fun onViewRecycled(holder: ViewHolder) {
        //player?.release()
        super.onViewRecycled(holder)
    }

    fun stopPlayer() {
        player?.let {
            player!!.stop()
        }
    }

    fun playVideo(currentPosition: Int, reyclerView: RecyclerView) {
        val holder = reyclerView.findViewHolderForLayoutPosition(currentPosition)
        (holder as ViewHolder).bindPlayer()
        player!!.prepare()
    }

    private fun initializePlayer(url: String) {
        val mediaItem: MediaItem = MediaItem.Builder()
            .setUri(url)
            .setMimeType(MimeTypes.APPLICATION_MP4)
            .build()
        player!!.setMediaItem(mediaItem)
    }

    override fun getItemCount(): Int = videoItems.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.videoTitle)
        val playerView = itemView.findViewById<PlayerView>(R.id.videoPayer)

        fun bindTo(item: VideoViewState) {
            title.text = item.title

            initializePlayer(item.url)
        }

        fun bindPlayer() {
            playerView.player = player
        }
    }
}
