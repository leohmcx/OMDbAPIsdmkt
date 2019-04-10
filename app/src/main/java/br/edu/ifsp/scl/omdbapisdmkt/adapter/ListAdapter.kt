package br.edu.ifsp.scl.omdbapisdmkt.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.edu.ifsp.scl.omdbapisdmkt.R
import br.edu.ifsp.scl.omdbapisdmkt.data.Search
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*

class ListAdapter(private val list: List<Search>, val listener: (Int) -> Unit)
    : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], position, listener)
    }
    override fun getItemCount(): Int = list.size
}

class ViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item, parent, false)) {
    private var tvTitle: TextView? = null
    private var tvYear: TextView? = null
    private var tvOmdbID: TextView? = null
    private var tvType: TextView? = null
    private var ivPoster: ImageView? = null

    init {
        tvTitle = itemView.findViewById(R.id.list_title)
        tvYear = itemView.findViewById(R.id.list_year)
        tvOmdbID = itemView.findViewById(R.id.list_omdbid)
        tvType = itemView.findViewById(R.id.list_type)
        ivPoster = itemView.findViewById(R.id.list_poster)
    }
    fun bind(omdb: Search, pos: Int, listener: (Int) -> Unit) = with(itemView) {
        tvTitle?.text = omdb.Title
        tvYear?.text = omdb.Year
        tvOmdbID?.text = omdb.imdbID
        tvType?.text = omdb.Type
        Picasso.get().load(omdb.Poster).into(ivPoster)
        layout_item.setOnClickListener{
            listener(pos)
        }
    }
}