package edu.vanderbilt.owdaa.photoapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.view.Menu
import android.view.MenuItem
import edu.vanderbilt.owdaa.photoapp.api.PhotoRetriever
import edu.vanderbilt.owdaa.photoapp.model.Photo
import edu.vanderbilt.owdaa.photoapp.model.PhotoList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var photos : List<Photo>? =null
    var mianAdapter : MainAdapter? = null
    lateinit var recyclerView : RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?

        var retriever = PhotoRetriever()

        var callback = object : Callback<PhotoList> {

            override fun onResponse(call: Call<PhotoList>?, response: Response<PhotoList>?) {
                response?.isSuccessful.let {
                    this@MainActivity.photos = response?.body()?.hits
                    mianAdapter = MainAdapter(this@MainActivity.photos !!,
                            this@MainActivity)

                    recyclerView.adapter = mianAdapter
                }
            }

            override fun onFailure(call: Call<PhotoList>?, t: Throwable?) {
                Log.e("MainActivity", "Problems Calling the API", t)
            }

        }

        retriever.getPhotos(callback)
    }

    override fun onClick(view: View?) {
        val intent = Intent(this, DetailActivity::class.java)
        //check out this line
        val holder = view?.tag as MainAdapter.PhotoHolder
        intent.putExtra(DetailActivity.PHOTO,
                mianAdapter?.getPhoto(holder.adapterPosition))
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }
}
