package br.edu.ifsp.scl.omdbapisdmkt.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.edu.ifsp.scl.omdbapisdmkt.adapter.ListAdapter
import br.edu.ifsp.scl.omdbapisdmkt.R
import br.edu.ifsp.scl.omdbapisdmkt.data.Search
import br.edu.ifsp.scl.omdbapisdmkt.fragment.ModoListaFragment.codigosMensagen.RESPOSTA_BUSCA
import br.edu.ifsp.scl.omdbapisdmkt.utils.OmdbMovieSearch
import kotlinx.android.synthetic.main.fragment_omdb.*

class ModoListaFragment : ModoApp() {

    object codigosMensagen { val RESPOSTA_BUSCA = 0}

    object Constantes {
        val URL_BASE = "http://www.omdbapi.com/?"
        val APP_KEY_FIELD = "apikey"
        val APP_KEY_VALUE = "e0d85fb6" // Preeencher com seu app_key
    }

    private var mNicolasCageMovies = listOf<Search>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buscaHandler = BuscaHandler()
        val omdbMovieSearch = OmdbMovieSearch(this)
        omdbMovieSearch.buscar("Avengers")
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_omdb, container, false)

    companion object {
        fun newInstance(): ModoListaFragment = ModoListaFragment()
    }

    lateinit var buscaHandler: BuscaHandler // Handler da thread de UI

    inner class BuscaHandler : Handler() {
        override fun handleMessage(msg: Message?) {
            if (msg?.what == RESPOSTA_BUSCA) {
                mNicolasCageMovies = msg.obj as List<Search>
                list_recycler_view.apply {
                    layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    adapter = ListAdapter(mNicolasCageMovies)
                }
            }
        }
    }
}