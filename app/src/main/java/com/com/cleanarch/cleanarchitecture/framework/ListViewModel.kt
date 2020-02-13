package com.com.cleanarch.cleanarchitecture.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.com.cleanarch.cleanarchitecture.framework.db.UseCases
import com.com.cleanarch.cleanarchitecture.framework.di.AppModule
import com.com.cleanarch.cleanarchitecture.framework.di.DaggerViewModelComponent
import com.com.cleanarch.core.data.Note
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