package br.edu.ifsp.scl.omdbapisdmkt.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
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

    init {
        mTitleView = itemView.findViewById(R.id.list_title)
        mYearView = itemView.findViewById(R.id.list_description)
    }
    fun bind(movie: OMDb) {
        mTitleView?.text = movie.title
        mYearView?.text = movie.year.toString()
    }
}