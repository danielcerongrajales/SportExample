package com.example.sportexample.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportexample.core.Respuesta
import com.example.sportexample.data.model.AllTeams
import com.example.sportexample.data.model.Spiner
import com.example.sportexample.data.model.Team

import com.example.sportexample.domain.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.system.measureTimeMillis

@HiltViewModel
class MovieViewModel @Inject constructor(val getPopularMoviesUseCase: GetPopularMoviesUseCase): ViewModel() {
    val popularMovies=MutableLiveData<Respuesta>()
    val vali:MutableList<Team> = ArrayList()
    val valor:MutableLiveData<MutableList<Boolean>> = MutableLiveData(mutableListOf(false, false, false))

    val listVOs: MutableLiveData<ArrayList<Spiner>> =  MutableLiveData(ArrayList())
    val select_qualification = arrayOf(
        "Selecciona ligas","Scottish Premier League", "English Premier League", "Spanish La Liga"

    )

    init{
        viewModelScope.launch(Dispatchers.IO){
//            val result=getKeywordsMovieByIdUseCase("385103")
//            movieModel.postValue(result)
//            is Respuesta.Loading->{
//            Log.d("tag", "loading")
            popularMovies.postValue(Respuesta.Loading())
//        }
            getPopularMoviesUseCase("Spanish La Liga").let {current->

                when(current){
                    is Respuesta.Success ->{
                        Log.d("tag", "succes")
                        popularMovies.postValue(Respuesta.Success(current.popularMovies))
                    }
                    is Respuesta.HttpErrors.ResourceForbidden -> popularMovies.postValue(Respuesta.Failure(current.exception))
                    is Respuesta.HttpErrors.ResourceNotFound -> popularMovies.postValue(Respuesta.Failure(current.exception))
                    is Respuesta.HttpErrors.InternalServerError ->  popularMovies.postValue(
                        Respuesta.Failure(current.exception))
                    is Respuesta.HttpErrors.BadGateWay ->  popularMovies.postValue(Respuesta.Failure(current.exception))
                    is Respuesta.HttpErrors.ResourceRemoved -> popularMovies.postValue(Respuesta.Failure(current.exception))
                    is Respuesta.HttpErrors.RemovedResourceFound ->  popularMovies.postValue(
                        Respuesta.Failure(current.exception))

                    is Respuesta.Failure -> popularMovies.postValue( Respuesta.Failure(current.error))
                    is Respuesta.NetworkException ->  popularMovies.postValue( Respuesta.Failure(current.error))
                }

//
//                when(current){
//
//                    is Respuesta.HttpErrors -> {
//                        popularMovies.postValue(Respuesta.HttpErrors(current.))
//                    }
//                    is Respuesta.Success->{
//                        Log.d("tag", "succes")
//                        popularMovies.postValue(Respuesta.Success(current.popularMovies))
//                    }
//                    else -> {}
//                }
//                when (it.){
//                    is Result.Success ->{}
//                }
//                if(it){
//                    popularMovies.value=Respuesta.Success(result)
//
//                }else{
//                    popularMovies.value=Respuesta.Failure(IllegalStateException("Movie Id not found in the state handle"))
//                }
            }


//            popularMovies.postValue(result)
        }
        select_qualification.forEachIndexed { index, _ ->
            val stado:Spiner = if(index==3) {
                Spiner(title = select_qualification[index], isSelected = true)
            }else{
                Spiner(title = select_qualification[index], isSelected = false)

            }
            listVOs.value!!.add(stado)
        }

    }

    fun filter(valor:MutableList<Spiner>){

          vali.clear()
        viewModelScope.launch(Dispatchers.IO){
            popularMovies.postValue(Respuesta.Loading())

            valor.forEachIndexed {index, element ->
                if(element.isSelected)
                {
                    val result1: Deferred<Respuesta> = async {
                        getPopularMoviesUseCase(element.title)
                    }

                    result1.await().let {Res->
                        when(Res){
                            is Respuesta.Success ->{
                                Res.popularMovies as AllTeams
                                Res.popularMovies.teams?.let { vali.addAll(it) }

                            }
                            else -> {}
                        }
                    }
                }
                    else
                {
//                    Log.d("tag", Respuesta.Loading().toString())
                }
                }

//Log.d("tag","${vali[0].idTeam}")
//            if(valor.size>0) {
//                if(valor[0]){
                val help = AllTeams(vali)
                popularMovies.postValue(Respuesta.Success(help))
//            }
//            }
//
//                val result2: Deferred<Respuesta> = async {
//                    getPopularMoviesUseCase(valorCheckbox[1])
//
//                }
//
//            result2.await().let {
//                when(it){
//                    is Respuesta.Success ->{
//                        it.popularMovies as AllTeams
//                        Log.d("tag","${it.popularMovies.teams!!.size}")
//                    }
//                }
//            }


//            valor.forEach {
//                if(it)
//                {
//                    Log.d("tag","alv")
//                    getPopularMoviesUseCase("Spanish La Liga")
//                }
//                    else
//                {
////                    Log.d("tag", Respuesta.Loading().toString())
//                }
//                }
            }
////        }
//            getPopularMoviesUseCase("Spanish La Liga").let {current->
//
//                when(current){
//                    is Respuesta.Success ->{
//                        Log.d("tag", "succes")
//                        popularMovies.postValue(Respuesta.Success(current.popularMovies))
//                    }
//                    is Respuesta.HttpErrors.ResourceForbidden -> popularMovies.postValue(Respuesta.Failure(current.exception))
//                    is Respuesta.HttpErrors.ResourceNotFound -> popularMovies.postValue(Respuesta.Failure(current.exception))
//                    is Respuesta.HttpErrors.InternalServerError ->  popularMovies.postValue(
//                        Respuesta.Failure(current.exception))
//                    is Respuesta.HttpErrors.BadGateWay ->  popularMovies.postValue(Respuesta.Failure(current.exception))
//                    is Respuesta.HttpErrors.ResourceRemoved -> popularMovies.postValue(Respuesta.Failure(current.exception))
//                    is Respuesta.HttpErrors.RemovedResourceFound ->  popularMovies.postValue(
//                        Respuesta.Failure(current.exception))
//
//                    is Respuesta.Failure -> popularMovies.postValue( Respuesta.Failure(current.error))
//                    is Respuesta.NetworkException ->  popularMovies.postValue( Respuesta.Failure(current.error))
//                }
//
////
////                when(current){
////
////                    is Respuesta.HttpErrors -> {
////                        popularMovies.postValue(Respuesta.HttpErrors(current.))
////                    }
////                    is Respuesta.Success->{
////                        Log.d("tag", "succes")
////                        popularMovies.postValue(Respuesta.Success(current.popularMovies))
////                    }
////                    else -> {}
////                }
////                when (it.){
////                    is Result.Success ->{}
////                }
////                if(it){
////                    popularMovies.value=Respuesta.Success(result)
////
////                }else{
////                    popularMovies.value=Respuesta.Failure(IllegalStateException("Movie Id not found in the state handle"))
////                }
//            }


//            popularMovies.postValue(result)
        }
    }

