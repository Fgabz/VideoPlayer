package com.fanilo.videolist.feature

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_DRAGGING
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE
import com.fanilo.videolist.R
import com.fanilo.videolist.di.IDaggerFactoryViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject

class HomeActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: IDaggerFactoryViewModel

    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: VideoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)

        setUpObserver()

        viewModel.onCreate()

        //Setup list
        adapter = VideoListAdapter(this)

        PagerSnapHelper().attachToRecyclerView(videoRecyclerview)
        videoRecyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                Timber.d("Scroll State $newState")
                if (newState == SCROLL_STATE_DRAGGING) {
                    val position = (recyclerView.layoutManager as LinearLayoutManager)
                        .findFirstVisibleItemPosition()
                    Timber.d("[TOTO] STOP")
                    adapter.stopPlayer(position, recyclerView)
                }

                if (newState == SCROLL_STATE_IDLE) {
                    val position = (recyclerView.layoutManager as LinearLayoutManager)
                        .findFirstVisibleItemPosition()

                    Timber.d("[TOTO] PLAY $position")

                    adapter.playVideo(position, recyclerView)
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dx == dy) {
                    Timber.d("[TOTO] PLAY GO ")
                    val position = (recyclerView.layoutManager as LinearLayoutManager)
                        .findFirstCompletelyVisibleItemPosition()
                    adapter.playVideo(position, recyclerView)
                }
            }
        })

        videoRecyclerview.adapter = adapter
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        videoRecyclerview.layoutManager = linearLayoutManager


        viewModel.onViewReady()
    }

    private fun setUpObserver() {
        viewModel.mutableVideoList.observe(this, Observer { listRepos ->
            adapter.addAll(listRepos)
        })
    }
}