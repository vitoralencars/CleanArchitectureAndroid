package br.com.daycoval.core.usecase

import br.com.daycoval.core.data.Note
import br.com.daycoval.core.repository.NoteRepository

class RemoveNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note: Note) = noteRepository.removeNote(note)
}