package br.edu.ifsp.scl.omdbapisdmkt.data

data class Resposta(
    val totalResults: Int,
    val Response: Boolean,
    var Search: List<Search?>? = listOf()
)

data class Search(val Title: String
                , val Year: String
                , val imdbID: String
                , val Type: String
                , val Poster: String)