package com.com.cleanarch.core.usecase

import com.com.cleanarch.core.data.Note
import com.com.cleanarch.core.repository.NoteRepository

class RemoveNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note: Note) = noteRepository.removeNote(note)
}