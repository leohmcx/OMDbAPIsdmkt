package br.edu.ifsp.scl.omdbapisdmkt.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.edu.ifsp.scl.omdbapisdmkt.R
import br.edu.ifsp.scl.omdbapisdmkt.data.OMDb
import br.edu.ifsp.scl.omdbapisdmkt.fragment.ModoApp.codigosMensagen.RESPOSTA_BUSCA
import br.edu.ifsp.scl.omdbapisdmkt.utils.OmdbItem
import com.squareup.picasso.Picasso

class ModoItemFragment : ModoApp() {

    private var item: OMDb? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        itemHandler = BuscaHandler()
        val omdbItem = OmdbItem(this)
        omdbItem.buscar("tt4154756")
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_omdb_detalhe, container, false)

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
        var tvTitle: TextView? = view?.findViewById(R.id.tv_title)
        var tvYear: TextView? = view?.findViewById(R.id.tv_year_value)
        var tvRated: TextView? = view?.findViewById(R.id.tv_rated_value)
        var tvReleased: TextView? = view?.findViewById(R.id.tv_released_value)
        var tvGenre: TextView? = view?.findViewById(R.id.tv_genre_value)
        var tvDirector: TextView? = view?.findViewById(R.id.tv_director_value)
        var tvWriter: TextView? = view?.findViewById(R.id.tv_writer_value)
        var tvActors: TextView? = view?.findViewById(R.id.tv_actors_value)
        var tvPlot: TextView? = view?.findViewById(R.id.tv_plot_value)
        var tvLanguage: TextView? = view?.findViewById(R.id.tv_language_value)
        var tvCountry: TextView? = view?.findViewById(R.id.tv_country_value)
        var ivPoster: ImageView? = view?.findViewById(R.id.iv_poster_value)
        var tvSource1: TextView? = view?.findViewById(R.id.tv_source1)
        var tvSource1Value: TextView? = view?.findViewById(R.id.tv_source1_value)
        var tvSource2: TextView? = view?.findViewById(R.id.tv_source2)
        var tvSource2Value: TextView? = view?.findViewById(R.id.tv_source2_value)
        var tvSource3: TextView? = view?.findViewById(R.id.tv_source3)
        var tvSource3Value: TextView? = view?.findViewById(R.id.tv_source3_value)
        var tvAwards: TextView? = view?.findViewById(R.id.tv_awards_value)
        var tvMetascore: TextView? = view?.findViewById(R.id.tv_metascore_value)
        var tvImdbRating: TextView? = view?.findViewById(R.id.tv_imdbrating_value)
        var tvImdbVotes: TextView? = view?.findViewById(R.id.tv_imdbvotes_value)
        var tvType: TextView? = view?.findViewById(R.id.tv_type_value)
        var tvDvd: TextView? = view?.findViewById(R.id.tv_dvd_value)
        var tvBoxOffice: TextView? = view?.findViewById(R.id.tv_boxoffice_value)
        var tvProduction: TextView? = view?.findViewById(R.id.tv_production_value)
        var tvWebsite: TextView? = view?.findViewById(R.id.tv_website_value)

        tvTitle?.text = item?.Title
        tvYear?.text = item?.Year
        tvRated?.text = item?.Rated
        tvReleased?.text = item?.Released
        tvGenre?.text = item?.Genre
        tvDirector?.text = item?.Director
        tvWriter?.text = item?.Writer
        tvActors?.text = item?.Actors
        tvPlot?.text = item?.Plot
        tvLanguage?.text = item?.Language
        tvCountry?.text = item?.Country
        tvSource1?.text = item?.Ratings?.get(0)?.Source
        tvSource1Value?.text = item?.Ratings?.get(0)?.Value
        tvSource2?.text = item?.Ratings?.get(1)?.Source
        tvSource2Value?.text = item?.Ratings?.get(1)?.Value
        tvSource3?.text = item?.Ratings?.get(2)?.Source
        tvSource3Value?.text = item?.Ratings?.get(2)?.Value
        tvAwards?.text = item?.Awards
        tvMetascore?.text = item?.Metascore
        tvImdbRating?.text = item?.imdbRating
        tvImdbVotes?.text = item?.imdbVotes
        tvType?.text = item?.Type
        tvDvd?.text = item?.DVD
        tvBoxOffice?.text = item?.BoxOffice
        tvProduction?.text = item?.Production
        tvWebsite?.text = item?.Website
        Picasso.get().load(item?.Poster).into(ivPoster)
    }
}