package br.com.daycoval.cleanarchitecture.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.com.daycoval.cleanarchitecture.framework.db.UseCases
import br.com.daycoval.cleanarchitecture.framework.di.AppModule
import br.com.daycoval.cleanarchitecture.framework.di.DaggerViewModelComponent
import br.com.daycoval.core.data.Note
import br.com.daycoval.core.repository.NoteRepository
import br.com.daycoval.core.usecase.AddNote
import br.com.daycoval.core.usecase.GetAllNotes
import br.com.daycoval.core.usecase.GetNote
import br.com.daycoval.core.usecase.RemoveNote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListViewModel(application: Application): AndroidViewModel(application){

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    @Inject
    lateinit var useCases: UseCases

    init {
        DaggerViewModelComponent.builder()
            .appModule(AppModule(getApplication()))
            .build()
            .inject(this)
    }


    val notes = MutableLiveData<List<Note>>()

    fun listNotes(){
        coroutineScope.launch {
            val notesList = useCases.getAllNotes()
            notesList.forEach {
                it.wordCount = useCases.getWordCount.invoke(it)
            }
            notes.postValue(notesList)
        }
    }

}