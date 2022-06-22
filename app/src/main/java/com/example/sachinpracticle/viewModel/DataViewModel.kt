package com.example.sachinpracticle.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.sachinpracticle.model.DataModel
import com.example.sachinpracticle.retrofit.ApiServiceHelper
import com.example.sachinpracticle.retrofit.Resource
import kotlinx.coroutines.launch


class DataViewModel(context: Application) : BaseViewModel(context) {
    private val getAllData = MutableLiveData<Resource<ArrayList<DataModel>>>()


    fun getDataCall() {
        viewModelScope.launch {
            try {
                getAllData.postValue(Resource.loading(null))
                val usersFromApi = ApiServiceHelper.getData()
                if (usersFromApi?.isSuccessful == true) {
                    getAllData.postValue(Resource.success(usersFromApi.body()))
                }else {
                    getAllData.postValue(
                        Resource.error(
                            super.errorHandler(usersFromApi?.code()!!, usersFromApi.errorBody()!!),
                            null
                        )
                    )

                }


            } catch (e: Exception) {
                e.message?.let { Log.e("Error", it) }
            }
        }
    }


    fun getData(): LiveData<Resource<ArrayList<DataModel>>> = getAllData

}
