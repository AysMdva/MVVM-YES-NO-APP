package com.aysmdva.yesno.viewmodel

import android.content.Context
import android.text.Editable
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aysmdva.yesno.model.Data
import com.aysmdva.yesno.network.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

enum class State {
    SUCCESS, ERROR
}

class MainViewmodel : ViewModel() {

    var data = MutableLiveData<Data>()

    val state = MutableLiveData<State>()

    fun operate(txtVal: EditText, context: Context) {
        APIClient.client?.getAPI()?.setAnim()?.enqueue(object : Callback<Data> {
            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                val data = response.body() as Data
                this@MainViewmodel.data.postValue(data)
                if (txtVal.text.toString() == data.answer) {
                    this@MainViewmodel.state.postValue(State.SUCCESS)
                } else this@MainViewmodel.state.postValue(State.ERROR)
            }


            override fun onFailure(call: Call<Data>, t: Throwable) {
                Toast.makeText(context, "An error occured", Toast.LENGTH_LONG).show()
            }
        })

    }


    fun observeData(): LiveData<Data> {
        return data
    }

    fun observeState(): LiveData<State> {
        return state
    }
}