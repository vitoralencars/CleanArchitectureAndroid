package br.com.daycoval.cleanarchitecture.framework.di

import br.com.daycoval.cleanarchitecture.framework.db.UseCases
import br.com.daycoval.core.repository.NoteRepository
import br.com.daycoval.core.usecase.*
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