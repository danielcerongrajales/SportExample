package com.example.sportexample.domain

import com.example.sportexample.core.Respuesta
import com.example.sportexample.data.MovieRepository
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(private val repository: MovieRepository){

    suspend operator fun invoke(valor:String): Respuesta {
        return repository.getPopularMovies(valor)
    }


}