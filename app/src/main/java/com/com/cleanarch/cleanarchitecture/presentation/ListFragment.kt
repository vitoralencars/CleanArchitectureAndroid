package com.com.cleanarch.cleanarchitecture.presentation


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation

import com.com.cleanarch.cleanarchitecture.R
import com.com.cleanarch.cleanarchitecture.framework.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * A simple [Fragment] subclass.
 */
class ListFragment : Fragment(), ListOnClickListener {

    private lateinit var viewModel: ListViewModel

    private val adapter = NotesListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)

        rv_notes.adapter = adapter
        fab_add_note.setOnClickListener { gotToNoteDetails() }

        observeViewModel()
        viewModel.listNotes()
    }

    private fun gotToNoteDetails(id: Long = 0L){
        val action = ListFragmentDirections.actionListFragmentToNoteFragment(id)
        Navigation.findNavController(rv_notes).navigate(action)
    }

    private fun observeViewModel(){
        viewModel.notes.observe(viewLifecycleOwner, Observer {
            pb_load.visibility = View.GONE
            adapter.updateNotes(it.sortedByDescending { it.updateTime })
        })
    }

    override fun onClick(id: Long) {
        gotToNoteDetails(id)
    }

}
