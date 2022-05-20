package com.example.sportexample.di

import androidx.lifecycle.SavedStateHandle
import com.example.sportexample.data.model.AllTeams
import com.example.sportexample.data.model.Team
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
object DetailsIdModule {
        @Provides
        @Named("movieId")
        fun movieIdProvider(stateHandle: SavedStateHandle): Team =
            stateHandle.get<Team>("peli")
                ?: throw IllegalStateException("Movie Id not found in the state handle")

}