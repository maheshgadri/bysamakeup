package com.example.makeu

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.makeu.adapter.MakupAdapter
import com.example.makeu.model.MakeupItem
import com.example.makeu.utilities.NetworkUtils
import com.example.makeu.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var recyclerViewAdapter: MakupAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewModel()
        initMainViewModel()
    }

    private fun initViewModel() {
        recyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)

            val decoration  =  DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            recyclerViewAdapter = MakupAdapter()
            adapter =recyclerViewAdapter
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initMainViewModel() {
        if(!NetworkUtils.isConnected(this)){
            Toast.makeText(this,resources.getString(R.string.internet_unavailable), Toast.LENGTH_SHORT).show()

        }
        val viewModel  = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getAllItem().observe(this, Observer<List<MakeupItem>>{
            recyclerViewAdapter.setListData(it)
            recyclerViewAdapter.notifyDataSetChanged()
        })


        viewModel.makeApiCall()
    }
}