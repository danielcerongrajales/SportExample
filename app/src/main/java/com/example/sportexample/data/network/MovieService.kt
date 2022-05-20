package com.example.sportexample.data.network

//import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import javax.inject.Inject
import com.example.sportexample.ui.ext.convert
import com.example.sportexample.core.Respuesta
import com.example.sportexample.core.bad
import com.example.sportexample.data.model.AllTeams
import com.example.sportexample.data.model.last5Events

class MovieService @Inject constructor(private val retrofit: ApiClient, private var pop:AllTeams){



    suspend fun getPopularMovies(valor:String): Respuesta {
       var a:Respuesta=Respuesta.Loading()
//return try{


   var respu= withContext(Dispatchers.IO) {
            retrofit.getMoviePopular(valor)
        }
    if (respu.isSuccessful){
        a= respu.body()?.let { Respuesta.Success(it) }!!

    }else{
        val jObjError = JSONObject(respu.errorBody()?.string() ?:"" )
        val user: bad =jObjError.convert()

        when(respu.code()){
            401 -> Respuesta.HttpErrors.ResourceForbidden(user.status_message!!)
            404 -> Respuesta.HttpErrors.ResourceNotFound(respu.message())
            500 -> Respuesta.HttpErrors.InternalServerError(respu.message())
            502 -> Respuesta.HttpErrors.BadGateWay(respu.message())
            301 -> Respuesta.HttpErrors.ResourceRemoved(respu.message())
            302 -> Respuesta.HttpErrors.RemovedResourceFound(respu.message())
            else -> Respuesta.Failure(respu.message())
//
    }
    }
//}catch (error : IOException){
//    Respuesta.Error(error.message!!)
//}
        return    a

    }
    suspend fun getMovieById(id:Int): Respuesta {
        var a:Respuesta=Respuesta.Loading()

        var respu= withContext(Dispatchers.IO) {
            retrofit.getMovieById(id)

        }
        if (respu.isSuccessful){
            a= respu.body()?.let { Respuesta.Success(it) }!!

        }else {
            val jObjError = JSONObject(respu.errorBody()?.string() ?: "")
            val user: bad = jObjError.convert()

            when (respu.code()) {
                401 -> Respuesta.HttpErrors.ResourceForbidden(user.status_message!!)
                404 -> Respuesta.HttpErrors.ResourceNotFound(respu.message())
                500 -> Respuesta.HttpErrors.InternalServerError(respu.message())
                502 -> Respuesta.HttpErrors.BadGateWay(respu.message())
                301 -> Respuesta.HttpErrors.ResourceRemoved(respu.message())
                302 -> Respuesta.HttpErrors.RemovedResourceFound(respu.message())
                else -> Respuesta.Failure(respu.message())
//
            }
        }
        return a

    }





}