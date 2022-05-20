package com.example.sportexample.domain

import com.example.sportexample.core.Respuesta
import com.example.sportexample.data.MovieRepository
import com.example.sportexample.data.model.last5Events
import javax.inject.Inject

class GetMovieByIdUseCase @Inject constructor (val repository: MovieRepository) {


    suspend operator fun invoke(id:Int): Respuesta =repository.getMovieById(id)

}