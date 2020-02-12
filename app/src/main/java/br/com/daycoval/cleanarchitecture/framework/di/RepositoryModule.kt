package br.com.daycoval.cleanarchitecture.framework.di

import android.app.Application
import br.com.daycoval.cleanarchitecture.framework.RoomNoteDataSource
import br.com.daycoval.core.repository.NoteRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideRepository(app: Application) = NoteRepository(RoomNoteDataSource(app))

}