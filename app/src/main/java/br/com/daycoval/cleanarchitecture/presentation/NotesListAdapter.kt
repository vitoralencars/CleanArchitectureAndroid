package br.com.daycoval.cleanarchitecture.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.daycoval.cleanarchitecture.R
import br.com.daycoval.core.data.Note
import kotlinx.android.synthetic.main.item_note.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class NotesListAdapter(val clickListener: ListOnClickListener): RecyclerView.Adapter<NotesListAdapter.ViewHolder>() {

    private val notes = ArrayList<Note>()

    fun updateNotes(notes: List<Note>){
        this.notes.clear()
        this.notes.addAll(notes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
    )

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(notes[position])

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        fun bind(note: Note){
            with(itemView){
                tv_title.text = note.title
                tv_content.text = note.content

                val sdf = SimpleDateFormat("dd/MMM, HH:mm:ss")
                val resultDate = Date(note.updateTime)
                tv_last_update.text = "Last updated: ${sdf.format(resultDate)}"

                tv_word_count.text = "Word: ${note.wordCount}"

                setOnClickListener {
                    clickListener.onClick(note.id)
                }
            }
        }
    }
}