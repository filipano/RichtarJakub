package com.pepcox.richtar.richtarjakub.activites

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.pepcox.richtar.richtarjakub.BeerAdapter
import com.pepcox.richtar.richtarjakub.R
import com.pepcox.richtar.richtarjakub.RichtarJakupApp
import com.pepcox.richtar.richtarjakub.data.Beer
import com.pepcox.richtar.richtarjakub.managers.BeerManager
import com.pepcox.richtar.richtarjakub.presenters.BeerContract
import com.pepcox.richtar.richtarjakub.presenters.BeerPresenter
import kotlinx.android.synthetic.main.activity_beers_list.*
import javax.inject.Inject

class BeersListActivity : AppCompatActivity(), BeerContract.View {

    @Inject
    lateinit var beerManager: BeerManager
    private lateinit var beerPresenter: BeerContract.Presenter

    override fun showBeerDetail(beer: Beer) {
        val bundle = Bundle()
        bundle.putSerializable(BeerDetailActivity.BEER_ARG, beer)

        val intent = Intent(this, BeerDetailActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun showListView(beers: List<Beer>) {
        loading_image.clearAnimation()
        view_switcher.displayedChild = 1

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = BeerAdapter(beers, this, beerPresenter)
    }

    override fun showErrorView(error: String) {
        Toast.makeText(this, "Ups, something went wrong", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beers_list)

        val bottomNavigationView: BottomNavigationView = bottom_navigation

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_history -> {

                }
                R.id.action_current -> {

                }
                R.id.action_favorites -> {

                }
            }
            true
        }

        RichtarJakupApp.beersComponent.inject(this)

        loading_image.startAnimation(AnimationUtils.loadAnimation(this, R.anim.loading))

        beerPresenter = BeerPresenter(this, beerManager)
        beerPresenter.loadBeerList()
    }
}
