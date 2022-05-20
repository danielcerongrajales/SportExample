package com.example.sportexample.core

import com.example.sportexample.data.model.AllTeams

sealed class Respuesta {
    class Loading: Respuesta()
    data class Success(val popularMovies: Any) : Respuesta()
    data class Failure(val error : String) : Respuesta()




    data class NetworkException(val error : String) : Respuesta()
    sealed class HttpErrors : Respuesta() {
        data class ResourceForbidden(val exception: String) : HttpErrors()
        data class ResourceNotFound(val exception: String) : HttpErrors()
        data class InternalServerError(val exception: String) : HttpErrors()
        data class BadGateWay(val exception: String) : HttpErrors()
        data class ResourceRemoved(val exception: String) : HttpErrors()
        data class RemovedResourceFound(val exception: String) : HttpErrors()
    }
}
