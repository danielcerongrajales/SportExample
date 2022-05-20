package com.example.sportexample.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportexample.core.Respuesta
import com.example.sportexample.domain.GetMovieByIdUseCase
import com.example.sportexample.data.model.Team
import com.example.sportexample.data.model.last5Events
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class DetailsViewModel @Inject constructor(@Named("movieId") private val id: Team, val getMovieByIdUseCase: GetMovieByIdUseCase): ViewModel() {

    val movieById=MutableLiveData<Respuesta>()



    fun onCreate(){
        viewModelScope.launch(Dispatchers.IO){
            movieById.postValue(Respuesta.Loading())
           getMovieByIdUseCase(id.idTeam!!.toInt()).let {current->
               when(current){
                   is Respuesta.Success ->{
                       movieById.postValue(Respuesta.Success(current.popularMovies))
                   }
                   is Respuesta.HttpErrors.ResourceForbidden -> movieById.postValue(Respuesta.Failure(current.exception))
                   is Respuesta.HttpErrors.ResourceNotFound -> movieById.postValue(Respuesta.Failure(current.exception))
                   is Respuesta.HttpErrors.InternalServerError ->  movieById.postValue(
                       Respuesta.Failure(current.exception))
                   is Respuesta.HttpErrors.BadGateWay ->  movieById.postValue(Respuesta.Failure(current.exception))
                   is Respuesta.HttpErrors.ResourceRemoved -> movieById.postValue(Respuesta.Failure(current.exception))
                   is Respuesta.HttpErrors.RemovedResourceFound ->  movieById.postValue(
                       Respuesta.Failure(current.exception))
                   is Respuesta.Failure -> movieById.postValue( Respuesta.Failure(current.error))
                   is Respuesta.NetworkException ->  movieById.postValue( Respuesta.Failure(current.error))
               }

           }



        }
    }
}