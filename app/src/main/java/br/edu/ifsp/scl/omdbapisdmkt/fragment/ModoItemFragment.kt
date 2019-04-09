package br.edu.ifsp.scl.omdbapisdmkt.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.edu.ifsp.scl.omdbapisdmkt.R
import br.edu.ifsp.scl.omdbapisdmkt.data.OMDb
import br.edu.ifsp.scl.omdbapisdmkt.fragment.ModoApp.codigosMensagen.RESPOSTA_BUSCA
import br.edu.ifsp.scl.omdbapisdmkt.utils.OmdbItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_omdb_detalhe.*

class ModoItemFragment : ModoApp() {

    private var item: OMDb? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = if(view != null) view else
    inflater.inflate(R.layout.fragment_omdb_detalhe, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(savedInstanceState != null) {
            item = savedInstanceState.getParcelable("item")
            bind()
        } else {
            itemHandler = BuscaHandler()
            OmdbItem(this).buscar(arguments!!.getString("imdbID"))
            retainInstance = true
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("item", item)
    }

    companion object { fun newInstance(): ModoItemFragment = ModoItemFragment() }

    lateinit var itemHandler: BuscaHandler // Handler da thread de UI

    inner class BuscaHandler : Handler() {
        override fun handleMessage(msg: Message?) {
            if (msg?.what == RESPOSTA_BUSCA) {
                item = msg.obj as OMDb
                bind()
            }
        }
    }

    fun bind(){
        if(item?.Type == "movie") {
            tv_dvd_value.text = item?.DVD
            tv_boxoffice_value.text = item?.BoxOffice
            tv_production_value.text = item?.Production
            tv_website_value.text = item?.Website
        } else {
            tv_source2.visibility = View.GONE
            tv_source2_value.visibility = View.GONE
            tv_source3.visibility = View.GONE
            tv_source3_value.visibility = View.GONE
            tv_dvd.visibility = View.GONE
            tv_dvd_value.visibility = View.GONE
            tv_boxoffice.visibility = View.GONE
            tv_boxoffice_value.visibility = View.GONE
            tv_production.visibility = View.GONE
            tv_production_value.visibility = View.GONE
            tv_website.visibility = View.GONE
            tv_website_value.visibility = View.GONE
            tv_writer.visibility = View.GONE
            tv_writer_value.visibility = View.GONE
        }
        tv_title.text = item?.Title
        tv_year_value.text = item?.Year
        tv_rated_value.text = item?.Rated
        tv_released_value.text = item?.Released
        tv_runtime_value.text = item?.Runtime
        tv_genre_value.text = item?.Genre
        tv_director_value.text = item?.Director
        tv_writer_value.text = item?.Writer
        tv_actors_value.text = item?.Actors
        tv_plot_value.text = item?.Plot
        tv_language_value.text = item?.Language
        tv_country_value.text = item?.Country
        tv_awards_value.text = item?.Awards
        tv_metascore_value.text = item?.Metascore
        tv_imdbrating_value.text = item?.imdbRating
        tv_imdbvotes_value.text = item?.imdbVotes
        tv_type_value.text = item?.Type
        Picasso.get().load(item?.Poster).into(iv_poster_value)

        val tvSource = listOf(tv_source1, tv_source2, tv_source3)
        val tvSourceValue = listOf(tv_source1_value, tv_source2_value, tv_source3_value)

        for (i in 0 until (item?.Ratings?.size!!)-1) {
            tvSource[i]?.text = item?.Ratings?.get(i)?.Source
            tvSourceValue[i]?.text = item?.Ratings?.get(i)?.Value
        }
    }
}