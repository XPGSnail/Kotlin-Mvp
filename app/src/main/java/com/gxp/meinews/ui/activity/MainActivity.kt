package com.gxp.meinews.ui.activity

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.gxp.meinews.R
import com.gxp.meinews.ui.fragment.AndroidFragment
import com.gxp.meinews.ui.fragment.GirlFragment
import com.gxp.meinews.ui.fragment.IOSFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var mAndroidFragment: Fragment
    lateinit var mIOSFragment: Fragment
    lateinit var mMeiZiFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initFragments()
        addFragment(mAndroidFragment)
    }

    private fun initView() {
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    private fun initFragments() {
        mAndroidFragment = AndroidFragment.newInstanct()
        mIOSFragment = IOSFragment.newInstanct()
        mMeiZiFragment = GirlFragment.newInstanct()
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fl_container, fragment).commit()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                Snackbar.make(fab, "hi,what are you wishing for？", Snackbar.LENGTH_SHORT)
                        .setAction("1", null).show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_android -> {
                Snackbar.make(fab, "android 新闻查看", Snackbar.LENGTH_SHORT)
                        .setAction("1", null).show()
                addFragment(mAndroidFragment)
            }
            R.id.item_ios -> {
                Snackbar.make(fab, "ios 新闻查看", Snackbar.LENGTH_SHORT)
                        .setAction("1", null).show()
                addFragment(mIOSFragment)
            }
            R.id.item_meizi -> {
                Snackbar.make(fab, "meizi 福利？", Snackbar.LENGTH_SHORT)
                        .setAction("2", null).show()
                addFragment(mMeiZiFragment)
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
