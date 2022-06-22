package com.example.sachinpracticle

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sachinpracticle.adapter.ImagesAdapter
import com.example.sachinpracticle.databinding.ActivityMainBinding
import com.example.sachinpracticle.model.DataModel
import com.example.sachinpracticle.retrofit.Resource
import com.example.sachinpracticle.viewModel.DataViewModel
import com.example.sachinpracticle.viewModel.ViewModelFactory


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: DataViewModel by viewModels {
        ViewModelFactory(application)
    }

    private var dataList = ArrayList<DataModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setObserver()
        viewModel.getDataCall()
    }

    private fun setRecyclerView(dataList: ArrayList<DataModel>) {
        binding.rvImages.layoutManager = GridLayoutManager(this, 2)
        binding.rvImages.adapter = ImagesAdapter(this, dataList,object :ImagesAdapter.OnClickListener{
            override fun onClick(position: Int) {

                startActivity(Intent(this@MainActivity,DetailsActivity::class.java)
                    .putExtra("data",dataList[position]))
            }
        })

    }

    private fun setObserver() {

        viewModel.getData().observe(this) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.pvProgress.visibility=View.GONE
                    it.data?.let { it1 -> dataList=it1 }
                    setRecyclerView(dataList)
                }
                Resource.Status.LOADING -> {
                    binding.pvProgress.visibility= View.VISIBLE
                }
                Resource.Status.ERROR -> {
                    it.let {
                        val builder = AlertDialog.Builder(this)
                        builder.setTitle(getString(R.string.alert))
                        builder.setMessage(getString(R.string.some_wrong))

                        builder.setPositiveButton("Ok") { dialogInterface, which ->
                            dialogInterface.dismiss()
                        }
                        builder.setNegativeButton("") { dialogInterface, which ->

                        }
                        val alertDialog: AlertDialog = builder.create()
                        alertDialog.setCancelable(true)
                        alertDialog.show()

                    }
                }
            }
        }
    }
}