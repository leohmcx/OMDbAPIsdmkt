package br.edu.ifsp.scl.omdbapisdmkt

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import br.edu.ifsp.scl.omdbapisdmkt.utils.ConfigSingleton.Modos.MODO_TESTE
import br.edu.ifsp.scl.omdbapisdmkt.fragment.ModoTesteFragment.Companion.newInstance
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getString(R.string.app_name)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val abreFechaToogle = ActionBarDrawerToggle(this, menuLateralDrawerLayout, toolbar, R.string.menu_aberto, R.string.menu_fechado)
        menuLateralDrawerLayout.addDrawerListener(abreFechaToogle)
        abreFechaToogle.syncState()
        menuNavigationView.setNavigationItemSelectedListener { onNavigationItemSelected(it) }
        substituiFragment(MODO_TESTE)
    }

    private fun substituiFragment(modo: String) {
        var modoJogoFragment = newInstance()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentJogoFl, modoJogoFragment, modo)
        fragmentTransaction.commit()
    }

    fun onNavigationItemSelected(item: MenuItem): Boolean {
        var retorno = false
        when (item.itemId) {
            R.id.testeRecyclerViewMenuItem -> { substituiFragment(MODO_TESTE)}
            R.id.sairMenuItem -> { finish() }
        }
        menuLateralDrawerLayout.closeDrawer(GravityCompat.START)
        return if(item.itemId != null) true else false
    }

    override fun onBackPressed() {
        if (menuLateralDrawerLayout.isDrawerOpen(GravityCompat.START)) { menuLateralDrawerLayout.closeDrawer(GravityCompat.START) }
        else { super.onBackPressed() }
    }
}