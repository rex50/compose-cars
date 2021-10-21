package com.rex50.composecars.data

import com.rex50.composecars.data.model.Car
import retrofit2.http.GET

interface CarsApi {

    @GET("/randomCar")
    suspend fun getRandomRabbit(): Car

    companion object {
        const val BASE_URL = "http://<local-ip>:8080"
    }

}