package com.example.sachinpracticle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            tvDate.setText(dataModel.date)
            tvTitle.setText(dataModel.title)
            tvDescription.setText(dataModel.explanation)

        }
    }
}