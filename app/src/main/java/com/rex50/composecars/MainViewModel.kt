package com.rex50.composecars

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rex50.composecars.data.CarsApi
import com.rex50.composecars.data.model.Car
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val carsApi: CarsApi
): ViewModel() {

    private val _state = mutableStateOf<CarState>(CarState())
    val state: State<CarState> = _state

    init {
        getRandomCar()
    }

    fun getRandomCar() {
        viewModelScope.launch {
            try {
                _state.value = state.value.copy(isLoading = true)
                _state.value = state.value.copy(
                    car = carsApi.getRandomRabbit(),
                    isLoading = false
                )

            } catch (e: Exception) {
                Log.e("MainViewModel", "getRandomCar: ", e)
                _state.value = state.value.copy(isLoading = false)
            }
        }
    }

    data class CarState(
        val car: Car? = null,
        val isLoading: Boolean = false
    )

}














