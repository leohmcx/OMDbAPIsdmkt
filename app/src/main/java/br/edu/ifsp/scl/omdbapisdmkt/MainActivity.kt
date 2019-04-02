package br.edu.ifsp.scl.omdbapisdmkt

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import br.edu.ifsp.scl.omdbapisdmkt.ConfigSingleton.Modos.MODO_BUSCA
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val abreFechaToogle: ActionBarDrawerToggle =
            ActionBarDrawerToggle(this, menuLateralDrawerLayout, toolbar, R.string.menu_aberto, R.string.menu_fechado)
        menuLateralDrawerLayout.addDrawerListener(abreFechaToogle)
        abreFechaToogle.syncState() // mantem o ícone do botão confome status do menu.
        menuNavigationView.setNavigationItemSelectedListener { onNavigationItemSelected(it) }
        substituiFragment(MODO_BUSCA) // coloca o modo grafico como modo padrão para abertura do jogo.
    }

    fun onNavigationItemSelected(item: MenuItem): Boolean {
        var retorno: Boolean = false
        when (item.itemId) {
            R.id.modoGraficoMenuItem -> {
                substituiFragment(MODO_BUSCA)
                retorno = true
            }
            R.id.sairMenuItem -> {
                finish()
                retorno = true
            }
        }
        menuLateralDrawerLayout.closeDrawer(GravityCompat.START)
        return retorno
    }

    private fun substituiFragment(modo: String) {
        val modoJogoFragment = if (modo == MODO_BUSCA) ModoBuscaFragment() else ModoBuscaFragment()
        // Transação para substituição de Fragment
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentOMDb, modoJogoFragment, modo)
        fragmentTransaction.commit()
    }
}
