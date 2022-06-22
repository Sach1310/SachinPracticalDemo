package com.example.sachinpracticle.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import okhttp3.ResponseBody

open class BaseViewModel(private val context: Application) :
    AndroidViewModel(context) {

    protected fun errorHandler(usersFromApi: Int, errorBody: ResponseBody): String {
        when (usersFromApi) {
            500 -> {
                return "Something went wrong"
            }
            401 -> {
                return "Something went wrong"
            }
            else -> {
                 return "Something went wrong"
            }
        }
    }

}

