package com.com.cleanarch.cleanarchitecture.framework.di

import com.com.cleanarch.cleanarchitecture.framework.db.UseCases
import com.com.cleanarch.core.repository.NoteRepository
import com.com.cleanarch.core.usecase.*
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {
    @Provides
    fun getUseCases(repository: NoteRepository) = UseCases(
        AddNote(repository),
        GetAllNotes(repository),
        GetNote(repository),
        RemoveNote(repository),
        GetWordCount()
    )
}