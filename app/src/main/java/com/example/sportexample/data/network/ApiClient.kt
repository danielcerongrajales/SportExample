package com.example.sportexample.data.network

import com.example.sportexample.data.model.AllTeams
import com.example.sportexample.data.model.last5Events
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiClient {
    @GET("api/v1/json/2/search_all_teams.php")
    suspend fun getMoviePopular(@Query ("l") query:String): Response<AllTeams>

    @GET("api/v1/json/2/eventslast.php")
    suspend fun getMovieById(@Query ("id") query:Int): Response<last5Events>

}