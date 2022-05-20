package com.example.sportexample.data

import com.example.sportexample.core.Respuesta
import com.example.sportexample.data.model.last5Events
import com.example.sportexample.data.network.MovieService
import javax.inject.Inject

class MovieRepository @Inject constructor(private val api : MovieService){

    suspend fun getPopularMovies(valor:String): Respuesta = api.getPopularMovies(valor)

    suspend fun getMovieById(id:Int): Respuesta = api.getMovieById(id)


}