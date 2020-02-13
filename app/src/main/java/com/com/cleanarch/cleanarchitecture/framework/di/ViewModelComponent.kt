package com.com.cleanarch.cleanarchitecture.framework.di

import com.com.cleanarch.cleanarchitecture.framework.ListViewModel
import com.com.cleanarch.cleanarchitecture.framework.NoteViewModel
import dagger.Component

@Component(modules = [AppModule::class, RepositoryModule::class, UseCasesModule::class])
interface ViewModelComponent {
    fun inject(noteViewModel: NoteViewModel)
    fun inject(listViewModel: ListViewModel)
}