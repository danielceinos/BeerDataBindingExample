package com.danielsanchezc.databindingexample

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.danielsanchezc.databindingexample.databinding.ActivityImageBinding

class ImageActivity : AppCompatActivity() {

    companion object {
        val BEER_URL = "URL"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityImageBinding = DataBindingUtil.setContentView(this, R.layout.activity_image)

        supportPostponeEnterTransition()
        binding.imageUrl = intent.getStringExtra(BEER_URL)
        binding.handler = {
            supportStartPostponedEnterTransition()
        }
    }
}