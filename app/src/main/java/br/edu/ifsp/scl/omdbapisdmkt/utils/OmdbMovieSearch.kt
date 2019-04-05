package br.edu.ifsp.scl.omdbapisdmkt.utils

import android.util.Log
import br.edu.ifsp.scl.omdbapisdmkt.data.Resposta
import br.edu.ifsp.scl.omdbapisdmkt.data.Search
import br.edu.ifsp.scl.omdbapisdmkt.fragment.ModoListaFragment
import br.edu.ifsp.scl.omdbapisdmkt.fragment.ModoListaFragment.Constantes.APP_KEY_FIELD
import br.edu.ifsp.scl.omdbapisdmkt.fragment.ModoListaFragment.Constantes.APP_KEY_VALUE
import br.edu.ifsp.scl.omdbapisdmkt.fragment.ModoListaFragment.Constantes.URL_BASE
import br.edu.ifsp.scl.omdbapisdmkt.fragment.ModoListaFragment.codigosMensagen.RESPOSTA_BUSCA
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_omdb.*
import org.jetbrains.anko.design.snackbar
import org.json.JSONException
import org.json.JSONObject

class OmdbMovieSearch(val modoListaFragment: ModoListaFragment) {
    fun buscar(titulo: String) {
        val urlSb = StringBuilder(URL_BASE)
        with(urlSb) {append("s=${titulo}&${APP_KEY_FIELD}=${APP_KEY_VALUE}")}
        val url = urlSb.toString()
        val filaRequisicaoTraducao: RequestQueue = Volley.newRequestQueue(modoListaFragment.context)
        var buscaJORequest: JsonObjectRequest = object : JsonObjectRequest(Request.Method.GET,url,null,RespostaListener(),ErroListener()){}
        filaRequisicaoTraducao.add(buscaJORequest)
    }

    inner class RespostaListener : Response.Listener<JSONObject> {
        override fun onResponse(response: JSONObject?) {
            try {
                val gson = Gson()
                val omdbs: Resposta = gson.fromJson(response.toString(), Resposta::class.java)
                var listOmdb: MutableList<Search> = mutableListOf()
                omdbs.Search?.forEach {
                    if (it != null) {
                        listOmdb.add(it)
                    }
                }
                modoListaFragment.buscaHandler.obtainMessage(RESPOSTA_BUSCA, listOmdb).sendToTarget()
            } catch (jse: JSONException) {
                modoListaFragment.list_recycler_view.snackbar("Erro na conversão JSON")
            }
        }
    }
    inner class ErroListener : Response.ErrorListener {
        override fun onErrorResponse(error: VolleyError?) {
            Log.d("teste","Erro na requisição: ${error.toString()}")
            modoListaFragment.list_recycler_view.snackbar("Erro na requisição: ${error.toString()}")
        }
    }
}