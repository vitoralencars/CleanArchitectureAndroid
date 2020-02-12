package br.com.daycoval.cleanarchitecture.framework

import android.content.Context
import br.com.daycoval.cleanarchitecture.framework.db.DatabaseService
import br.com.daycoval.cleanarchitecture.framework.db.NoteEntity
import br.com.daycoval.core.data.Note
import br.com.daycoval.core.repository.NoteDataSource

class RoomNoteDataSource(context: Context): NoteDataSource{
    private val noteDao = DatabaseService.getInstance(context).noteDao()

    override suspend fun add(note: Note) = noteDao.addNoteEntity(NoteEntity.fromNote(note))

    override suspend fun get(id: Long) = noteDao.getNoteEntity(id)?.toNote()

    override suspend fun getAll() = noteDao.getAllNoteEntities().map { it.toNote() }

    override suspend fun remove(note: Note) = noteDao.deleteNoteEntity(NoteEntity.fromNote(note))
}