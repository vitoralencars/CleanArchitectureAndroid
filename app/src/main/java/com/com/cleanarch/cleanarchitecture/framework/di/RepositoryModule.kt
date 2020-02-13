package com.com.cleanarch.cleanarchitecture.framework.di

import android.app.Application
import com.com.cleanarch.cleanarchitecture.framework.RoomNoteDataSource
import com.com.cleanarch.core.repository.NoteRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideRepository(app: Application) = NoteRepository(RoomNoteDataSource(app))

}