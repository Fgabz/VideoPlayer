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
import timber.log.Timber

class VideoListAdapter(private val context: Context) : RecyclerView.Adapter<VideoListAdapter.ViewHolder>() {

    private var exoPlayer: SimpleExoPlayer? = null

    private val videoItems: MutableList<VideoViewState> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.video_item_layout, parent, false)

        buildPlayer()

        return ViewHolder(view)
    }

    private fun buildPlayer() {
        if (exoPlayer == null) {
            exoPlayer = SimpleExoPlayer.Builder(context).build()
        }
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
        Timber.d("[TOTO]  recycling")
        holder.mediaItem = null
        holder.playerView.player = null
        super.onViewRecycled(holder)
    }

    fun stopPlayer(position: Int, recyclerView: RecyclerView) {
        val holder = recyclerView.findViewHolderForLayoutPosition(position)
        (holder as ViewHolder).playerView.player = null
        exoPlayer?.let {
            exoPlayer!!.stop()

        }
    }

    fun playVideo(currentPosition: Int, reyclerView: RecyclerView) {
        val holder = reyclerView.findViewHolderForLayoutPosition(currentPosition)
        (holder as ViewHolder).bindPlayer()
        exoPlayer!!.prepare()
    }

    private fun initializeMedia(url: String) = MediaItem.Builder()
        .setUri(url)
        .setMimeType(MimeTypes.APPLICATION_MP4)
        .build()

    override fun getItemCount(): Int = videoItems.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.videoTitle)
        val playerView = itemView.findViewById<PlayerView>(R.id.videoPayer)
        var mediaItem : MediaItem? = null

        fun bindTo(item: VideoViewState) {
            Timber.d("[TOTO] bindto")
            title.text = item.title
            mediaItem = initializeMedia(item.url)
        }

        fun bindPlayer() {
            playerView.player = exoPlayer
            exoPlayer!!.setMediaItem(mediaItem!!)
        }
    }
}
