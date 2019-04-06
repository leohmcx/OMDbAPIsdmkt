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
import br.edu.ifsp.scl.omdbapisdmkt.fragment.ModoApp.codigosMensagen.RESPOSTA_BUSCA
import br.edu.ifsp.scl.omdbapisdmkt.utils.OmdbSearch
import kotlinx.android.synthetic.main.fragment_omdb.*
import org.jetbrains.anko.design.snackbar

class ModoListaFragment : ModoApp() {

    private var itens = listOf<Search>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buscaHandler = BuscaHandler()
        val omdbMovieSearch = OmdbSearch(this)
        omdbMovieSearch.buscar("Avengers")
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_omdb, container, false)

    companion object { fun newInstance(): ModoListaFragment = ModoListaFragment() }

    lateinit var buscaHandler: BuscaHandler // Handler da thread de UI

    inner class BuscaHandler : Handler() {
        override fun handleMessage(msg: Message?) {
            if (msg?.what == RESPOSTA_BUSCA) {
                itens = msg.obj as List<Search>
                list_recycler_view.apply {
                    layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    adapter = ListAdapter(itens) {
                        list_recycler_view.snackbar("clicou no item ${itens[it].Title}")
                        val fragment = ModoItemFragment()
                        val bundle = Bundle()
                        bundle.putString("imdbID", itens[it].imdbID)
                        fragment.arguments = bundle
                        fragmentManager?.beginTransaction()?.replace(R.id.fragmentJogoFl, fragment)?.addToBackStack(null)?.commit()
                    }
                }
            }
        }
    }
}