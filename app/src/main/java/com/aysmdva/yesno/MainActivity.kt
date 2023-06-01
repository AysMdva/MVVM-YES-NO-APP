package com.aysmdva.yesno

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import com.aysmdva.yesno.databinding.ActivityMainBinding
import com.aysmdva.yesno.viewmodel.MainViewmodel
import com.aysmdva.yesno.viewmodel.State
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewmodel
    lateinit var bar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        bar = binding.bar

        setContentView(binding.root)


        viewModel = ViewModelProvider(this)[MainViewmodel::class.java]


        binding.button.setOnClickListener {
            viewModel.operate(binding.editText, this)
            bar.visibility = View.VISIBLE

        }

        viewModel.observeState().observe(this, { state ->
            when (state) {
                State.SUCCESS -> {
                    Glide.with(this).load(viewModel.data.value?.image)
                        .into(binding.gif)
                    bar.visibility = View.GONE
                }

                State.ERROR -> binding.tvInfo.setText("YOU LOST!")
            }
        })

    }
}