package com.example.popularmoviesomdb.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.popularmoviesomdb.ui.adapter.MoviesAdapter
import com.example.popularmoviesomdb.R
import com.example.popularmoviesomdb.utils.RecyclerItemClickListener
import com.example.popularmoviesomdb.model.Movie
import com.example.popularmoviesomdb.viewmodel.MoviesListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var searchView: SearchView? = null
    var myActionMenuItem: MenuItem? = null
    var movies: List<Movie>? = null
    var moviesListViewModel: MoviesListViewModel? = null
    var movieNameSearch: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        moviesListViewModel = ViewModelProviders.of(this).get(
            MoviesListViewModel::class.java
        )
        moviesListViewModel?.init()
        val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(this, 2)
        movies_recycler_view.layoutManager = mLayoutManager
        movies_recycler_view.addOnItemTouchListener(
            RecyclerItemClickListener(
                applicationContext,
                movies_recycler_view,
                object :
                    RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View?, position: Int) {
                        val imdbId = movies!![position].imdbID
                        val intent = Intent(
                            this@MainActivity,
                            MovieDetails::class.java
                        )
                        intent.putExtra("imdbId", imdbId)
                        startActivity(intent)
                    }

                    override fun onLongItemClick(view: View?, position: Int) {
                    }
                })
        )
    }

    override fun onCreateOptionsMenu(menu:Menu):Boolean {
        getMenuInflater().inflate(R.menu.search_menu, menu)
        myActionMenuItem = menu.findItem(R.id.action_search)
        searchView = myActionMenuItem?.getActionView() as SearchView
        searchView?.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query:String):Boolean {
                movieNameSearch = query
                makeAPICall(movieNameSearch)
                if (!searchView!!.isIconified())
                {
                    searchView?.setIconified(true)
                }
                myActionMenuItem?.collapseActionView()
                return false
            }
            override fun onQueryTextChange(s:String):Boolean {
                return false
            }
        })
        return true
    }

    fun makeAPICall(movieName: String?) {
        moviesListViewModel!!.getMoviesListRepository(movieName)?.observe(
            this,
            Observer { movieList: List<Movie>? ->
                movies = movieList
                if (movies != null) movies_recycler_view.adapter =
                    MoviesAdapter(
                        movies!!,
                        R.layout.rv_item_layout,
                        applicationContext
                    ) else Toast.makeText(
                    applicationContext,
                    "No results found",
                    Toast.LENGTH_LONG
                ).show()
            }
        )
    }

    public override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putString("movieName", movieNameSearch)
    }

    public override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        movieNameSearch = savedInstanceState.getString("movieName")
        if (movieNameSearch != null) makeAPICall(movieNameSearch)
    }
}