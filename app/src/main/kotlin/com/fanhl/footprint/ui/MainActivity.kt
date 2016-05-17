package com.fanhl.footprint.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.fanhl.footprint.R
import com.fanhl.footprint.ui.adapter.FootprintAdapter
import com.fanhl.footprint.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainActivity : BaseActivity() {
    val TAG = MainActivity::class.java.simpleName

    private val adapter by lazy { FootprintAdapter(this, recycler_view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        assignViews()
        initData()
        refreshData()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun assignViews() {
        fab.setOnClickListener { RecordActivity.launch(this@MainActivity) }

        swipe_refresh_layout.setOnRefreshListener { refreshData() }
        recycler_view.adapter = adapter
    }

    private fun initData() {

    }

    private fun refreshData() {
        if (!swipe_refresh_layout.isRefreshing) swipe_refresh_layout.isRefreshing = true
        app.client.fileService.getRecords()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    adapter.replaceItems(it)
                }, {
                    swipe_refresh_layout.isRefreshing = false
                    Log.e(TAG, "refreshData success", it)
                }, {
                    swipe_refresh_layout.isRefreshing = false
                    Log.d(TAG, "refreshData success")
                })
    }
}

