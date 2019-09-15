package com.test.spacex.ui.base

import android.util.Log
import androidx.lifecycle.ViewModel
import com.test.spacex.data.DataManager
import com.test.spacex.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

abstract class BaseViewModel(
    val dataManager: DataManager,
    val schedulerProvider: SchedulerProvider,
    val compositeDisposable: CompositeDisposable
) : ViewModel() {


    fun getError(throwable: Throwable): String {
        return when (throwable) {
            is HttpException -> {
                val errorBody = throwable.response()?.errorBody()?.string()
                val jsonError = JSONObject(errorBody)
                Log.e("BaseViewModel", "errorBody ===>$errorBody")
                if (jsonError.has("status_message")) {
                    jsonError.getString("status_message")
                } else {
                    "Please try again."
                }
            }
            is SocketTimeoutException -> "Request timeout error."
            is IOException -> "Check your network connection."
            else -> "Unknown error occurred."
        }
    }


    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

}