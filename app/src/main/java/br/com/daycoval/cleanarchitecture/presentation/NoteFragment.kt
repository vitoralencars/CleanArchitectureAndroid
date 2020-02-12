package br.com.daycoval.cleanarchitecture.presentation


import android.app.AlertDialog
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation

import br.com.daycoval.cleanarchitecture.R
import br.com.daycoval.cleanarchitecture.framework.NoteViewModel
import br.com.daycoval.core.data.Note
import kotlinx.android.synthetic.main.fragment_note.*

/**
 * A simple [Fragment] subclass.
 */
class NoteFragment : Fragment() {

    private lateinit var viewModel: NoteViewModel
    private var currentNote = Note("", "", 0L, 0L)
    private var noteId = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)

        arguments?.let {
            noteId = NoteFragmentArgs.fromBundle(it).noteId
        }

        if(noteId != 0L){
            viewModel.getNote(noteId)
        }

        fab_check.setOnClickListener {
            if(et_title.text.toString() != "" || et_content.text.toString() != ""){
                val time = System.currentTimeMillis()
                currentNote.apply {
                    title = et_title.text.toString()
                    content = et_content.text.toString()
                    updateTime = time
                    if(id == 0L){
                        creationTime = time
                    }
                }

                viewModel.saveNote(currentNote)
            }else {
                Navigation.findNavController(it).popBackStack()
            }
        }

        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.saved.observe(viewLifecycleOwner, Observer {
            if(it){
                Toast.makeText(context, "Done!", Toast.LENGTH_LONG).show()
                hideKeyboard()
                Navigation.findNavController(et_title).popBackStack()
            }else{
                Toast.makeText(context, "Wrong! Try again!", Toast.LENGTH_LONG).show()
            }
        })

        viewModel.currentNote.observe(viewLifecycleOwner, Observer {
            it?.let {
                currentNote = it
                et_title.setText(currentNote.title)
                et_content.setText(currentNote.content)
            }
        })
    }

    private fun hideKeyboard(){
        val imm = context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(et_title.windowToken, 0)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete_note ->{
                if(context != null && noteId != 0L){
                    AlertDialog.Builder(context)
                        .setTitle("Delete Note")
                        .setMessage("Are you sure?")
                        .setPositiveButton("Yes"){dialogInterface, i ->
                            viewModel.deleteNote(currentNote)
                        }
                        .setNegativeButton("Cancel"){dialogInterface, i -> }
                        .create()
                        .show()
                }
            }
        }

        return true
    }

}
