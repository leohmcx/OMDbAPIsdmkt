package br.edu.ifsp.scl.omdbapisdmkt.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.edu.ifsp.scl.omdbapisdmkt.R
import br.edu.ifsp.scl.omdbapisdmkt.data.OMDb

class ListAdapter(private val list: List<OMDb>)
    : RecyclerView.Adapter<MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater, parent)
    }
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie: OMDb = list[position]
        holder.bind(movie)
    }
    override fun getItemCount(): Int = list.size
}

class MovieViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item, parent, false)) {
    private var mTitleView: TextView? = null
    private var mYearView: TextView? = null
    private var mOmdbIDView: TextView? = null
    private var mTypeView: TextView? = null
    private var mImageView: ImageView? = null

    init {
        mTitleView = itemView.findViewById(R.id.list_title)
        mYearView = itemView.findViewById(R.id.list_year)
        mOmdbIDView = itemView.findViewById(R.id.list_omdbid)
        mTypeView = itemView.findViewById(R.id.list_type)
        mImageView = itemView.findViewById(R.id.list_poster)
    }
    fun bind(omdb: OMDb) {
        mTitleView?.text = omdb.title
        mYearView?.text = omdb.year
        mOmdbIDView?.text = omdb.omdbid
        mTypeView?.text = omdb.type
        mImageView?.setImageResource(R.drawable.app_icon)
    }
}