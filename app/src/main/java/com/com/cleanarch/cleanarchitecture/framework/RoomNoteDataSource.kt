package com.com.cleanarch.cleanarchitecture.framework

import android.content.Context
import com.com.cleanarch.cleanarchitecture.framework.db.DatabaseService
import com.com.cleanarch.cleanarchitecture.framework.db.NoteEntity
import com.com.cleanarch.core.data.Note
import com.com.cleanarch.core.repository.NoteDataSource

class RoomNoteDataSource(context: Context): NoteDataSource{
    private val noteDao = DatabaseService.getInstance(context).noteDao()

    override suspend fun add(note: Note) = noteDao.addNoteEntity(NoteEntity.fromNote(note))

    override suspend fun get(id: Long) = noteDao.getNoteEntity(id)?.toNote()

    override suspend fun getAll() = noteDao.getAllNoteEntities().map { it.toNote() }

    override suspend fun remove(note: Note) = noteDao.deleteNoteEntity(NoteEntity.fromNote(note))
}