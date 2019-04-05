package br.edu.ifsp.scl.omdbapisdmkt.fragment

import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.edu.ifsp.scl.omdbapisdmkt.adapter.ListAdapter
import br.edu.ifsp.scl.omdbapisdmkt.data.OMDb
import br.edu.ifsp.scl.omdbapisdmkt.R
import kotlinx.android.synthetic.main.fragment_omdb.*

class ModoListaFragment : ModoApp() {

    private val mNicolasCageMovies = listOf(
        OMDb("Raising Arizona", "1987", "1", "filme", "poster1"),
        OMDb("Vampire's Kiss", "1988", "2", "filme", "poster2"),
        OMDb("Con Air", "1997", "3", "filme", "poster3"),
        OMDb("Gone in 60 Seconds", "1997", "4", "filme", "poster4"),
        OMDb("National Treasure", "2004", "5", "filme", "poster5"),
        OMDb("The Wicker Man", "2006", "6", "filme", "poster6"),
        OMDb("Ghost Rider", "2007", "7", "filme", "poster7"),
        OMDb("Knowing", "2009", "8", "filme", "poster8")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_omdb, container, false)

    // populate the views now that the layout has been inflated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // RecyclerView node initialized here
        list_recycler_view.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            // set the custom adapter to the RecyclerView
            adapter = ListAdapter(mNicolasCageMovies)
        }
    }

    companion object {
        fun newInstance(): ModoListaFragment =
            ModoListaFragment()
    }
}