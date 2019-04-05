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

    object codigosMensagen {
        // Constante usada para envio de mensagens ao Handler
        val RESPOSTA_BUSCA = 0
    }

    object Constantes {
        // Constantes usadas para acesso ao WS de tradução
        val URL_BASE = "http://www.omdbapi.com/?"//?s=Avengers&apikey=e0d85fb6"
        val APP_KEY_FIELD = "apikey"
        val APP_KEY_VALUE = "e0d85fb6" // Preeencher com seu app_key
    }

    private var mNicolasCageMovies = listOf<Search>()/*listOf(
        OMDb("Raising Arizona", "1987", "1", "filme", "poster1"),
        OMDb("Vampire's Kiss", "1988", "2", "filme", "poster2"),
        OMDb("Con Air", "1997", "3", "filme", "poster3"),
        OMDb("Gone in 60 Seconds", "1997", "4", "filme", "poster4"),
        OMDb("National Treasure", "2004", "5", "filme", "poster5"),
        OMDb("The Wicker Man", "2006", "6", "filme", "poster6"),
        OMDb("Ghost Rider", "2007", "7", "filme", "poster7"),
        OMDb("Knowing", "2009", "8", "filme", "poster8")
    )*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tradutoHandler = TradutoHandler()
        val omdbMovieSearch = OmdbMovieSearch(this) // Instancia um tradutor para fazer a chamada ao WS
        omdbMovieSearch.buscar("Avengers")
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_omdb, container, false)

    companion object {
        fun newInstance(): ModoListaFragment =
            ModoListaFragment()
    }

    lateinit var tradutoHandler: TradutoHandler // Handler da thread de UI

    inner class TradutoHandler : Handler() {
        override fun handleMessage(msg: Message?) {
            if (msg?.what == RESPOSTA_BUSCA) {
                mNicolasCageMovies = msg.obj as List<Search>//traduzidoTv.text = msg.obj.toString() // Alterar o conteúdo do TextView
                list_recycler_view.apply {
                    layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    adapter = ListAdapter(mNicolasCageMovies)
                }
            }
        }
    }
}