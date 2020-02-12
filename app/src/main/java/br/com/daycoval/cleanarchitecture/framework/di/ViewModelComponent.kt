package br.com.daycoval.cleanarchitecture.framework.di

import br.com.daycoval.cleanarchitecture.framework.ListViewModel
import br.com.daycoval.cleanarchitecture.framework.NoteViewModel
import dagger.Component

@Component(modules = [AppModule::class, RepositoryModule::class, UseCasesModule::class])
interface ViewModelComponent {
    fun inject(noteViewModel: NoteViewModel)
    fun inject(listViewModel: ListViewModel)
}