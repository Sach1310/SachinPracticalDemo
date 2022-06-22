package com.example.sachinpracticle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.example.sachinpracticle.databinding.ActivityDetailsBinding
import com.example.sachinpracticle.databinding.ActivityMainBinding
import com.example.sachinpracticle.model.DataModel

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private var dataModel=DataModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        dataModel=intent.getSerializableExtra("data") as DataModel
        showData()
    }

    private fun showData() {
        binding.apply {
            Glide.with(this@DetailsActivity)
                .load(dataModel.url)
                .priority(Priority.IMMEDIATE)
                .placeholder(R.drawable.image)
                .into(ivImage)
            tvDate.text = getString(R.string.date)+dataModel.date
            tvTitle.text = getString(R.string.title)+dataModel.title
            tvDescription.text = getString(R.string.des)+dataModel.explanation
        }
    }
}